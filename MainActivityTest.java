package com.codingexercide.newscorp.activities;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.codingexercide.newscorp.R;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by veswanaranha on 2/10/17.
 */
public class MainActivityTest {
    SwipeRefreshLayout mSwipeRefreshLayout;
    boolean isrefreshing = true;
    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = mock(MainActivity.class);
        mSwipeRefreshLayout = mock(SwipeRefreshLayout.class);

    }

    @Test
    public void onCreate() throws Exception {

    }

    @Test
    public void refreshItems() throws Exception {
        //checking whether the app is initially refreshing on load, should return false
        isrefreshing = mSwipeRefreshLayout.isRefreshing() ;
        assertEquals(isrefreshing,false);


    }


    @Test
    public void onItemsLoadComplete() throws Exception {

    }

}