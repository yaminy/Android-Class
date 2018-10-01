package com.yamin.session1;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Yamin on 10/1/2018.
 */

public class AndroidClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Vazir-FD.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
