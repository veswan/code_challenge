package com.codingexercide.newscorp.models;

/**
 * Created by veswanaranha on 2/10/17.
 */

public abstract class PhotoListItem {

    public static final int HEADER_TYPE = 0;
    public static final int PHOTO_TYPE = 1;
    abstract public int getType();

}
