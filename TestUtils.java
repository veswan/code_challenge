package com.codingexercide.newscorp;

import android.support.annotation.IdRes;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.espresso.util.HumanReadables;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
/**
 * Created by veswanaranha on 2/10/17.
 */

public class TestUtils {

    public static RecyclerViewMatcherInstrumentationTest withRecyclerView(final int recyclerViewId) {

        return new RecyclerViewMatcherInstrumentationTest(recyclerViewId);
    }

}
