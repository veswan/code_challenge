package com.codingexercide.newscorp.activities;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.codingexercide.newscorp.R;
import com.codingexercide.newscorp.models.Photos;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by veswanaranha on 2/10/17.
 */
public class DetailViewActivityTest {

    Photos photo;
    DetailViewActivity activity;
    @Before
    public void setUp() throws Exception {
        photo = mock(Photos.class);
        activity = mock(DetailViewActivity.class);
    }

    @Test
    public void onCreate() throws Exception {
        assertEquals(photo.getAlbumId(),0);
        activity = mock(DetailViewActivity.class);
    }

    @Test
    public void testlabel(){


        // The expected text to be displayed in the textview
        photo = Mockito.mock(Photos.class);
        // Check whether both are equal, otherwise test fails

       Mockito.doReturn("accusamus beatae ad facilis cum similique qui sunt").when(photo).getTitle();

    }
}