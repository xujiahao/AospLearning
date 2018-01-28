package com.skyworthdigital.download.utils;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by SDT13716 on 2018/1/28.
 */

public class Md5Util {

    public static String generate(String str){
        if (TextUtils.isEmpty(str)) return  null;
        StringBuilder result = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] cipher = digest.digest(str.getBytes());
            for (byte b : cipher) {
                String hexStr = Integer.toHexString(b&0xff);
                result.append(hexStr.length()==1?"0"+hexStr:hexStr);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
