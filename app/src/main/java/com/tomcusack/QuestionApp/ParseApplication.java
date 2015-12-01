package com.tomcusack.QuestionApp;

import android.app.Application;
import com.parse.Parse;

<<<<<<< HEAD
=======
/**
 * Created by Mark on 18/11/2015.
 *  Needed so this is only called once
 *  http://stackoverflow.com/questions/30135858/parse-error-parseenablelocaldatastorecontext-must-be-invoked-before-parse
 */
>>>>>>> origin/master
public class ParseApplication extends Application{

    public void onCreate(){
        super.onCreate();
<<<<<<< HEAD
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "WMBcnX9ILrgrMjp0PNUgL5WC4tT6EclgwACCWsoD", "1GYJmdv5a9pNsfOMhPsM52S3iqfFiWx9aDCEx0XQ");
    }

=======

        // Init Parse API
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "TEgJ17iShc3etRNM9szf40RRtg47D7QBATzU8x8u", "QjVvFeZJUc2oBYe1ZgHpl4EMlCMrCJR2aO073rBo");
    }
>>>>>>> origin/master
}
