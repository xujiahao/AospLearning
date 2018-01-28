package com.skyworthdigital.aosplearning;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.skyworthdigital.download.file.DownloadManager;
import com.skyworthdigital.download.http.DownloadCallback;
import com.skyworthdigital.download.utils.Logger;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.imageView);
        String url = "http://img1.gtimg.com/rcdimg/20180128/02/0571876031_227x148.jpg";
        DownloadManager.getIntance().download(url, new DownloadCallback() {
            @Override
            public void onSuccess(final File file) {
                Logger.d(TAG, "onSuccess");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mImageView.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                    }
                });
            }

            @Override
            public void onFailure(int errorCode, String errorMessage) {
                Logger.d(TAG, "onFailure");
            }

            @Override
            public void onProgress(int progress) {

            }
        });
//        HttpManager.getInstance().asyncDownload(url, new DownloadCallback() {
//            @Override
//            public void onSuccess(final File file) {
//                Logger.d(TAG, "onSuccess");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mImageView.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
//                    }
//                });
//
//            }
//
//            @Override
//            public void onFailure(int errorCode, String errorMessage) {
//                Logger.d(TAG, "onFailure");
//            }
//
//            @Override
//            public void onProgress(int progress) {
//
//            }
//        });
    }
}
