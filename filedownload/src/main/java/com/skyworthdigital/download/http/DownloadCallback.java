package com.skyworthdigital.download.http;

import java.io.File;

/**
 * Created by SDT13716 on 2018/1/28.
 */

public interface DownloadCallback {

    public static final int ERROR_NETWORK = 1;
    public static final int ERROR_RESPONSE_FAILED = 2;

    void onSuccess(File file);
    void onFailure(int errorCode, String errorMessage);
    void onProgress(int progress);
}
