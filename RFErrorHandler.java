package com.codingexercide.newscorp.handlers;

/**
 * Created by veswanaranha on 2/10/17.
 */

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.codingexercide.newscorp.R;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

public class RFErrorHandler implements ErrorHandler {
    public static final String ERROR_MSG = "Error";
    private Context context;

    public RFErrorHandler(Activity _context) {
        this.context = _context;
    }


    /**
     * Replace server error cause to meaningful error to user
     *
     * @param cause

     */
    @Override
    public Throwable handleError(RetrofitError cause) {
        String errorDescription = ERROR_MSG;
        if (context != null) {
            if (cause.isNetworkError()) {
                errorDescription = context.getString(R.string.network_error);
            } else if (cause.getResponse() == null) {
                errorDescription = context.getString(R.string.server_error);
            } else {
                errorDescription = context.getString(R.string.unknown_error);
            }
        }
        return new Exception(errorDescription);
    }


    /**
     * Display custom error message to user according to Retrofit error
     *
     * @param activity
     * @param e
     */
    public static void webErrorHandler(Activity activity, Throwable e) {
        String message = ERROR_MSG;
        if (activity != null) {
            if (e != null && e.getMessage() != null)
                message = e.getMessage();
        }
        Toast.makeText(activity,message,
                Toast.LENGTH_SHORT).show();

    }
}