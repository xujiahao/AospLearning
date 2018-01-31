package com.xjh.http;

import java.util.Map;

/**
 * 定义存放键值对的数据接口
 * Created by xjh on 2018/2/1.
 */

public interface NameValueMap extends Map<String, String> {

    String getValue(String name);

    void set(String name, String value);

    void setAll(Map<String, String> map);
}
