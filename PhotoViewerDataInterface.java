package com.codingexercide.newscorp.interfaces;

import com.codingexercide.newscorp.models.Photos;

import java.util.List;

import retrofit.http.GET;
import retrofit.Callback;

/**
 * Created by veswanaranha on 2/10/17.
 */

public interface PhotoViewerDataInterface {
    @GET("/photos")
    void getPhoto(Callback<List<Photos>> response);

}
