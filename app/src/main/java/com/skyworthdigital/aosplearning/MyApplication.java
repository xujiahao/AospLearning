package com.skyworthdigital.aosplearning;

import android.app.Application;

import com.skyworthdigital.download.file.FileStorageManager;

/**
 * Created by SDT13716 on 2018/1/28.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        FileStorageManager.getInstance().init(MyApplication.this);
    }
}
