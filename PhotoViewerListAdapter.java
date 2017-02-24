package com.codingexercide.newscorp.adapters;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.codingexercide.newscorp.R;
import com.codingexercide.newscorp.models.PhotoListItem;
import com.codingexercide.newscorp.models.ListHeader;
import com.codingexercide.newscorp.models.Photos;
import java.util.ArrayList;


/**
 * Created by veswanaranha on 2/10/17.
 */

public class PhotoViewerListAdapter extends RecyclerView.Adapter {
    private static final String TAG = PhotoViewerListAdapter.class.getSimpleName();
    Context context;
    List<PhotoListItem> listItems;
    List<PhotoListItem> filterListItem = null;


    public PhotoViewerListAdapter(Context context, List<PhotoListItem> _listItems) {
        this.context = context;
        this.filterListItem = _listItems;
        this.listItems = new ArrayList<>();
        listItems.addAll(filterListItem);
    }


    @Override
    public int getItemViewType(int position) {
        return filterListItem.get(position).getType();
    }

    /**
     * Create Item view from from XML file depending on Item type (Header/Photo)
     *
     * @param parent
     * @param viewType
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == PhotoListItem.HEADER_TYPE) {
            RecyclerView.ViewHolder viewHolder;
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent, false);
            viewHolder = new HeaderHolder(v);
            return viewHolder;

        } else {
            RecyclerView.ViewHolder viewHolder;
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
            viewHolder = new PhotoHolder(v);
            return viewHolder;
        }


    }


    /**
     * Called per each ItemList item to fill view either with Header / Photo
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        // if the item type header then use  HeaderHolder and fill with Album Id
        if (type == ListHeader.HEADER_TYPE) {
            ListHeader header = (ListHeader) filterListItem.get(position);
            HeaderHolder headerHolder = (HeaderHolder) holder;
            headerHolder.header.setText("Album Id : "+header.getAlbumId() + "");
        } else {

            // if the item type Photo then use PhotoHolder and fill with photo
            Photos event = (Photos) filterListItem.get(position);
            PhotoHolder photoHolder = (PhotoHolder) holder;
            Log.i(TAG, "" + position);
            // Load Thumbnail Url
            Glide.with(context).load((event).getThumbnailUrl())
                    .thumbnail(0.5f)
                    .override(200, 100)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into((photoHolder).photoItem);
        }
    }


    /**
     * get photo list size
     */
    @Override
    public int getItemCount() {
        return filterListItem.size();
    }


    public static class PhotoHolder extends RecyclerView.ViewHolder {
        ImageView photoItem;

        public PhotoHolder(View itemView) {
            super(itemView);
            photoItem = (ImageView) itemView.findViewById(R.id.photoItem);
        }
    }

    public static class HeaderHolder extends RecyclerView.ViewHolder {
        TextView header;

        public HeaderHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView.findViewById(R.id.header);
        }
    }


    public Photos getItem(int position) {
        return (Photos) filterListItem.get(position);
    }

}
