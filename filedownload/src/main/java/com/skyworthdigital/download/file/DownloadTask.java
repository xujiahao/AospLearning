package com.skyworthdigital.download.file;

import android.os.Process;

import com.skyworthdigital.download.http.DownloadCallback;
import com.skyworthdigital.download.http.HttpManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.Response;

/**
 * Created by SDT13716 on 2018/1/28.
 */

public class DownloadTask implements Runnable {

    private String mUrl;
    private long mStart;
    private long mEnd;
    private DownloadCallback mCallback;

    public DownloadTask(String url, long start, long end, DownloadCallback callback) {
        mUrl = url;
        mStart = start;
        mEnd = end;
        mCallback = callback;
    }

    @Override
    public void run() {
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        File file = FileStorageManager.getInstance().getFileByUrl(mUrl);
        Response response = HttpManager.getInstance().syncRequestByRange(mUrl, mStart, mEnd);
        if (response != null && response.isSuccessful()){
            RandomAccessFile randowmFile = null;
            InputStream is = response.body().byteStream();
            try {
                randowmFile = new RandomAccessFile(file, "rwd");
                int len = 0, offset = 0;
                byte[] buffer = new byte[5 * 1024];
                while((len = is.read(buffer)) != -1){
                    randowmFile.seek(offset);
                    randowmFile.write(buffer, 0, len);
                    offset += len;
                }
                if (mCallback != null) mCallback.onSuccess(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                    if (randowmFile != null) randowmFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
