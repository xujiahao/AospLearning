package com.xjh.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xjh on 2018/2/1.
 */

public interface HttpResponse extends Header{

    HttpStatus getStatus();

    String getStatusMsg();

    InputStream getBody() throws IOException;

    long getContentLength();

    void close();
}
