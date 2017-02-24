package com.codingexercide.newscorp;
import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import com.codingexercide.newscorp.activities.MainActivity;

import java.util.Random;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.codingexercide.newscorp.TestUtils.withRecyclerView;

/**
 * Created by veswanaranha on 2/10/17.
 */

public class RecyclerViewInstrumentationTest extends ActivityInstrumentationTestCase2<MainActivity>  {
    public RecyclerViewInstrumentationTest() {
        super(MainActivity.class);
    }

    @Override protected void setUp() throws Exception {

        getActivity();
    }

    public void testItemClick() {
        //Testing the Random position clicked for a short limit of 1 to 6 on screen
        Random rand = new Random();
        int min = 1;
        int max = 4;
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;
        //espresso test for random position clicks from 1 to 4 cells
        onView(withRecyclerView(R.id.list).atPosition(randomNum)).perform(click());
        //espresso test for checking albumid match
        onView(withId(R.id.lblAlbumId)).check(matches(isDisplayed()));


    }



}
