package com.codingexercide.newscorp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codingexercide.newscorp.R;
import com.codingexercide.newscorp.models.Photos;

/**
 * Created by veswanaranha on 2/10/17.
 */

public class DetailViewActivity extends Activity {
    ImageView imgPhoto;
    TextView lblTitle;
    TextView lblAlbumId;
    TextView lblPhotoId;
    Photos photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_details);
        initUi();
        // get selected photo from intent.
        Intent intent = getIntent();
        photo = (Photos) intent.getSerializableExtra(MainActivity.PHOTO_DETAILS);
        // Display details
        initData();
    }

    private void initUi() {
        imgPhoto = (ImageView) findViewById(R.id.photo);
        lblTitle = (TextView) findViewById(R.id.lblTitle);
        lblAlbumId = (TextView) findViewById(R.id.lblAlbumId_details);
        lblPhotoId = (TextView) findViewById(R.id.lblPhotoId_details);
    }

    private void initData() {
        // Load Image Url with Glide framework
        Glide.with(this).load(photo.getUrl()).thumbnail(0.1f).into(imgPhoto);

        lblTitle.setText(photo.getTitle());
        lblAlbumId.setText(Integer.toString(photo.getAlbumId()));
        lblPhotoId.setText(Integer.toString(photo.getId()));


    }

}
