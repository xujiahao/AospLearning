package com.skyworthdigital.download.http;

import android.text.TextUtils;

import com.skyworthdigital.download.file.FileStorageManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SDT13716 on 2018/1/28.
 */

public class HttpManager {


    private static HttpManager sInstance;
    private OkHttpClient mClient;
    private HttpManager(){
        mClient = new OkHttpClient();
    }

    public static HttpManager getInstance(){
        if (sInstance == null){
            synchronized (HttpManager.class){
                if (sInstance == null) sInstance = new HttpManager();
            }
        }
        return sInstance;
    }

    public Response syncRequest(String url){
        if (TextUtils.isEmpty(url)){
            throw new IllegalArgumentException("Illegal url!");
        }
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response =  mClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void asyncRequest(String url, Callback callback){
        Request request = new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(callback);
    }

    public void asyncDownload(final String url, final DownloadCallback callback){
        if (TextUtils.isEmpty(url)){
            throw new IllegalArgumentException("Illegal url!");
        }
        if (callback == null){
            throw new NullPointerException("DownloadCallback is null!");
        }
        Request request = new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(DownloadCallback.ERROR_NETWORK, "network error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()){
                    callback.onFailure(DownloadCallback.ERROR_NETWORK, "response failed");
                }
                File file = FileStorageManager.getInstance().getFileByUrl(url);
                FileOutputStream fos = new FileOutputStream(file);
                InputStream is = response.body().byteStream();
                byte[] buffer = new byte[500 * 1024];
                int len = 0;
                while((len = is.read(buffer)) != -1){
                    fos.write(buffer, 0, len);
                }
                fos.close();
                is.close();
                callback.onSuccess(file);
            }
        });
    }

    public Response syncRequestByRange(String url, long start, long end) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Range", "byte:" + start + "-" + end)
                .build();
        Response response = null;
        try {
            response = mClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
