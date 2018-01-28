package com.skyworthdigital.download.file;

import android.content.Context;
import android.support.annotation.NonNull;

import com.skyworthdigital.download.http.DownloadCallback;
import com.skyworthdigital.download.http.HttpManager;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by SDT13716 on 2018/1/28.
 */

public class DownloadManager{

    private static final int MAX_THREAD_NUM = 2;
    private static DownloadManager sIntance;
    private Context mContext;
    private ThreadPoolExecutor mExecutor;

    private DownloadManager(){
        mExecutor = new ThreadPoolExecutor(MAX_THREAD_NUM, MAX_THREAD_NUM, 0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(), new ThreadFactory() {
            AtomicInteger count = new AtomicInteger(0);
            @Override
            public Thread newThread(@NonNull Runnable runnable) {

                return new Thread(runnable, "Thread : " + count.getAndIncrement());
            }
        });
    }

    public static DownloadManager getIntance(){
        if (sIntance == null){
            sIntance = new DownloadManager();
        }
        return sIntance;
    }

    public void init(Context context){
        mContext = context;
    }

    public void download(final String url, final DownloadCallback callback){
        if (url == null || callback == null){
            throw new NullPointerException();
        }
        HttpManager.getInstance().asyncRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()){
                    callback.onFailure(DownloadCallback.ERROR_NETWORK,"Network error!");
                    return;
                }
                int len = Integer.parseInt(response.header("Content-Length"));
                if (len > 0){
                    processDownload(url, len, callback);
                    return;
                }
            }
        });
    }

    private void processDownload(String url, int len, DownloadCallback callback) {

        long start, end;
        long step = len / MAX_THREAD_NUM;
        DownloadCallback finalCallback = null;
        for (int i = 0; i < MAX_THREAD_NUM; i++) {
            start = i * step;
            end = (i + 1) * step - 1;
            if (i == MAX_THREAD_NUM - 1){
                end = len;
                finalCallback = callback;
            }
            mExecutor.execute(new DownloadTask(url, start, end, finalCallback));
        }
    }
}
