// Copyright 2015 Tom Cusack
package com.tomcusack.QuestionApp;

import android.app.Application;
import com.parse.Parse;

public class ParseApplication extends Application
{
    public void onCreate()
    {
        super.onCreate();
        Parse.enableLocalDatastore(this);

        // Master key, then client key
        Parse.initialize(this, "WMBcnX9ILrgrMjp0PNUgL5WC4tT6EclgwACCWsoD", "1GYJmdv5a9pNsfOMhPsM52S3iqfFiWx9aDCEx0XQ");
    }
}
