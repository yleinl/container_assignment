package com.vu.model;

public interface Constants {

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 成功标记
     */
    public static final Integer SUCCESS = 200;

    /**
     * 失败标记
     */
    public static final Integer FAIL = 500;

    /**
     * 数值类型精度
     */
    public static final  int SCALE = 8;

    public static final int AGENT_TYPE = 2;

    /**
     * 最大缓存数据量
     */
    long REDIS_MAX_CACHE_KLINE_SIZE = 10000L;

}
