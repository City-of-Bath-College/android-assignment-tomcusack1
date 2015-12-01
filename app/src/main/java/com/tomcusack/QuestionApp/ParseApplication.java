package com.tomcusack.QuestionApp;

import android.app.Application;
import com.parse.Parse;

/**
 * Created by Mark on 18/11/2015.
 *  Needed so this is only called once
 *  http://stackoverflow.com/questions/30135858/parse-error-parseenablelocaldatastorecontext-must-be-invoked-before-parse
 */
public class ParseApplication extends Application{

    public void onCreate(){
        super.onCreate();

        // Init Parse API
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "TEgJ17iShc3etRNM9szf40RRtg47D7QBATzU8x8u", "QjVvFeZJUc2oBYe1ZgHpl4EMlCMrCJR2aO073rBo");
    }
}
