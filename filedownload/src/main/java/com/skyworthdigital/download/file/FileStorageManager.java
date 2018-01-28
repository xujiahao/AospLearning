package com.skyworthdigital.download.file;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.skyworthdigital.download.utils.Md5Util;

import java.io.File;
import java.io.IOException;

/**
 * Created by SDT13716 on 2018/1/28.
 */

public class FileStorageManager {

    private static FileStorageManager sInstance = new FileStorageManager();
    private Context mContext;

    private FileStorageManager(){}

    public static FileStorageManager getInstance(){
        return  sInstance;
    }

    public FileStorageManager init(Context context){
        mContext = context;
        return  FileStorageManager.this;
    }

    public File getFileByUrl(String url){

        if (TextUtils.isEmpty(url) || mContext == null){
            return null;
        }
        File parent;
        File file;
        String fileName = Md5Util.generate(url);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            parent = mContext.getExternalCacheDir();
        }else{
            parent = mContext.getCacheDir();
        }
        file = new File(parent, fileName);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  file;
    }
}
