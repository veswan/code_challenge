package com.codingexercide.newscorp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.codingexercide.newscorp.R;
import com.codingexercide.newscorp.adapters.PhotoViewerListAdapter;
import com.codingexercide.newscorp.handlers.RFErrorHandler;
import com.codingexercide.newscorp.interfaces.PhotoViewerDataInterface;
import com.codingexercide.newscorp.listeners.PhotoItemClickListener;
import com.codingexercide.newscorp.models.ListHeader;
import com.codingexercide.newscorp.models.PhotoListItem;
import com.codingexercide.newscorp.models.Photos;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    //JSON base url for the data to be retrieved
    private String BASE_URL = "http://jsonplaceholder.typicode.com";
    //Number of items displayed per row
    public static final int NUMBER_OF_ITEMS_PER_ROW = 2;
    public static final String PHOTO_DETAILS = "photoDetails";
    PhotoViewerListAdapter pAdapter;
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initUi();
        getPhotoAlbum();

    }

    private void initUi() {
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });


    }

    void refreshItems() {
        // Load items
        getPhotoAlbum();
        // Load complete
        onItemsLoadComplete();
    }

    void onItemsLoadComplete() {

        // Stop refresh animation
        mSwipeRefreshLayout.setRefreshing(false);
    }
    /**
     * Fetching photos from url using Retrofit API
     */
    private void getPhotoAlbum() {

        // Show progress bar while loading
        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.show();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL).setErrorHandler(new RFErrorHandler(this))
                .build();

        PhotoViewerDataInterface api = adapter.create(PhotoViewerDataInterface.class);
        api.getPhoto(new Callback<List<Photos>>() {
            @Override
            public void success(final List<Photos> photos, Response response) {
                TreeMap<Integer, List<Photos>> photoslistMap = new TreeMap<Integer, List<Photos>>();
                for (int i = 0; i < photos.size(); i++) {
                    if (!photoslistMap.containsKey(photos.get(i).getAlbumId())) {
                        List<Photos> ph = new ArrayList<>();
                        ph.add(photos.get(i));
                        photoslistMap.put(photos.get(i).getAlbumId(), ph);

                    } else {
                        List<Photos> mapPhotos = photoslistMap.get(photos.get(i).getAlbumId());
                        mapPhotos.add(photos.get(i));

                    }
                }

                photoAdapter(photoslistMap);
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());

                RFErrorHandler.webErrorHandler(MainActivity.this, error);
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }
        });
    }


    /**
     *  Adapter with photos list result, attach Adapter to RecyclerView and handle item click.
     *
     * @param photoslistMap
     */
    private void photoAdapter(TreeMap<Integer, List<Photos>> photoslistMap) {

        List<PhotoListItem> mItems = new ArrayList<>();

        for (Integer albumId : photoslistMap.keySet()) {
            ListHeader header = new ListHeader();
            header.setAlbumId(albumId);
            mItems.add(header);
            for (Photos photo : photoslistMap.get(albumId)) {
                mItems.add(photo);
            }


            pAdapter = new PhotoViewerListAdapter(MainActivity.this, mItems);
            mRecyclerView.setAdapter(pAdapter);
            //  set 2 photos per row if List item type --> header , else fill row with header.
            GridLayoutManager layoutManager = new GridLayoutManager(this, NUMBER_OF_ITEMS_PER_ROW);
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (mRecyclerView.getAdapter().getItemViewType(position) == PhotoListItem.HEADER_TYPE)
                        // return the number of columns so the group header takes a whole row
                        return NUMBER_OF_ITEMS_PER_ROW;
                    // normal child item takes up 1 cell
                    return 1;
                }
            });
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.addOnItemTouchListener(new PhotoItemClickListener(MainActivity.this,
                    new PhotoItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            if (pAdapter.getItemViewType(position) == PhotoListItem.HEADER_TYPE) return;

                            Photos photo = pAdapter.getItem(position);
                            Intent intent = new Intent(MainActivity.this, DetailViewActivity.class);
                            intent.putExtra(PHOTO_DETAILS, photo);
                            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                    MainActivity.this,
                                    /** For animation display while transfering from one activity to another ,For each shared element, add a new pair item to this method,
                                     * which contains the reference of the view we are transitioning from, and the value of the transitionName attribute **/
                                    new Pair<>(view.findViewById(R.id.photoItem),
                                            getString(R.string.transition_name_photo))
                            );
                            ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
                        }
                    }));
        }
    }
}
