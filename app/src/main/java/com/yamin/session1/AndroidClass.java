package com.yamin.session1;

import android.app.Application;

import com.github.tamir7.contacts.Contacts;

/**
 * Created by Yamin on 8/26/2018.
 */

public class AndroidClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Contacts.initialize(this);

    }
}
