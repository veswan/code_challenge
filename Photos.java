package com.codingexercide.newscorp.models;

import java.io.Serializable;

/**
 * Created by veswanaranha on 2/10/17.
 */

public class Photos  extends PhotoListItem implements Serializable {

    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;


    public Photos(int _albumId, int _id, String _title, String _url, String _thumbnailUrl) {
        this.setAlbumId(_albumId);
        this.setId(_id);
        this.setTitle(_title);
        this.setUrl(_url);
        this.setThumbnailUrl(_thumbnailUrl);
    }


    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public int getType() {
        return PhotoListItem.PHOTO_TYPE;
    }
}
