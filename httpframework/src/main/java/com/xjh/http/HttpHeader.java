package com.xjh.http;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by xjh on 2018/2/1.
 */

public final class HttpHeader implements NameValueMap{

    public final static String ACCEPT = "Accept";
    public final static String PRAGMA = "Pragma";
    public final static String PROXY_CONNECTION = "Proxy-Connection";
    public final static String USER_AGENT = "User-Agent";
    public final static String ACCEPT_ENCODING = "accept-encoding";
    public final static String CACHE_CONTROL = "Cache-Control";
    public final static String CONTENT_ENCODING = "Content-Encoding";
    public final static String CONNECTION = "Connection";
    public final static String CONTENT_LENGTH = "Content-length";

    public static final String CONTENT_TYPE = "Content-Type";

    private Map<String, String> mHeaders = new HashMap<>();

    public String getAccept() {
        return get(ACCEPT);
    }

    public void setAccept(String value) {
        set(ACCEPT, value);
    }

    public String getPragma() {
        return get(PRAGMA);
    }

    public void setPragma(String value) {
        set(PRAGMA, value);
    }

    public String getUserAgent() {
        return get(USER_AGENT);
    }

    public void setUserAgent(String value) {
        set(USER_AGENT, value);
    }

    public String getProxyConnection() {
        return get(PROXY_CONNECTION);
    }

    public void setProxyConnection(String value) {
        set(PROXY_CONNECTION, value);
    }

    public String getAcceptEncoding() {
        return get(ACCEPT_ENCODING);
    }

    public void setAcceptEncoding(String value) {
        set(ACCEPT_ENCODING, value);
    }

    public String getCacheControl() {
        return get(CACHE_CONTROL);
    }

    public void setCacheControl(String value) {
        set(CACHE_CONTROL, value);
    }

    public String getContentEncoding() {
        return get(CONTENT_ENCODING);
    }

    public void setContentEncoding(String value) {
        set(CONTENT_ENCODING, value);
    }

    public String getConnection() {
        return get(CONNECTION);
    }

    public void setConnection(String value) {
        set(CONNECTION, value);
    }

    public String getContentLength() {
        return get(CONTENT_LENGTH);
    }

    public void setContentLength(String value) {
        set(CONTENT_LENGTH, value);
    }

    public String getContentType() {
        return get(CONTENT_TYPE);
    }

    public void setContentType(String value) {
        set(CONTENT_TYPE, value);
    }

    @Override
    public String getValue(String name) {
        return mHeaders.get(name);
    }

    @Override
    public void set(String name, String value) {
        mHeaders.put(name,value);
    }

    @Override
    public void setAll(Map<String, String> map) {
        mHeaders.putAll(map);
    }

    @Override
    public int size() {
        return mHeaders.size();
    }

    @Override
    public boolean isEmpty() {
        return mHeaders.isEmpty();
    }

    @Override
    public boolean containsKey(Object o) {
        return mHeaders.containsKey(o);
    }

    @Override
    public boolean containsValue(Object o) {
        return mHeaders.containsValue(o);
    }

    @Override
    public String get(Object o) {
        return mHeaders.get(o);
    }

    @Override
    public String put(String s, String s2) {
        return mHeaders.put(s, s2);
    }

    @Override
    public String remove(Object o) {
        return mHeaders.remove(o);
    }

    @Override
    public void putAll(@NonNull Map<? extends String, ? extends String> map) {
        mHeaders.putAll(map);
    }

    @Override
    public void clear() {
        mHeaders.clear();
    }

    @NonNull
    @Override
    public Set<String> keySet() {
        return mHeaders.keySet();
    }

    @NonNull
    @Override
    public Collection<String> values() {
        return mHeaders.values();
    }

    @NonNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return mHeaders.entrySet();
    }
}
