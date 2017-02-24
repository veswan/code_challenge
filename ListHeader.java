package com.codingexercide.newscorp.models;

/**
 * Created by veswanaranha on 2/10/17.
 */

public class ListHeader extends PhotoListItem{

    private int albumId;

    @Override
    public int getType() {
        return PhotoListItem.HEADER_TYPE;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
}
