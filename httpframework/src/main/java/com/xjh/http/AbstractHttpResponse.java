package com.xjh.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * 可供继承的响应类，主要封装了“数据压缩类型判断”的逻辑
 *
 * Created by xjh on 2018/2/1.
 */

public abstract class AbstractHttpResponse implements HttpResponse{

    private static final String GZIP = "gzip";

    private InputStream mInputStream;

    @Override
    public InputStream getBody() throws IOException {
        if (mInputStream == null){
            InputStream internal = getBodyInternal();
            if (isGZip(getHeaders())){
                mInputStream = new GZIPInputStream(internal);
            }else{
                mInputStream = internal;
            }
        }
        return mInputStream;
    }

    private boolean isGZip(HttpHeader header) throws IOException {
        if (header == null || header.getContentEncoding() == null)
            return false;
        return GZIP.equals(header.getContentEncoding());
    }

    @Override
    public void close() {
        if (mInputStream != null){
            try {
                mInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        closeInternal();
    }

    protected abstract void closeInternal();

    protected abstract InputStream getBodyInternal();
}
