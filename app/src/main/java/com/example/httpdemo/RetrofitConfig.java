package com.example.httpdemo;

/**
 * 创 建 人： 燕归来兮
 * 电子邮箱：zhoutao_it@126.com
 * 个人博客：http://www.zhoutaotao@xyz.com
 * 创建时间： 2017/5/23
 * 文件作用：
 */

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 请求的配置文件类
 */
class RetrofitConfig<T> {
    private String baseUrl = "https://pay.tangchaogouwu.com/";
    private static Retrofit retrofitBook;
    //连接,读取，写入时间限制
    private static int CONNECT_TIMEOUT_MIL = 10000;
    private static int READ_TIMEOUT_MIL = 10000;
    private static int WRITE_TIMEOUT_MIL = 10000;
    private static OkHttpClient okHttpClient;

    public int getConnectTimeoutMil() {
        return CONNECT_TIMEOUT_MIL;
    }

    /**
     * 采取默认时间设置，单位毫秒
     *
     * @return
     */
    public static OkHttpClient getDefaultRequestConfig() {
        return getRequestConfig(CONNECT_TIMEOUT_MIL, READ_TIMEOUT_MIL, WRITE_TIMEOUT_MIL);
    }

    /**
     * 配置连接器的请求时间，响应时间以及拦截日志等级
     * @param connect
     * @param read
     * @param write
     * @return
     */
    public static OkHttpClient getRequestConfig(int connect, int read, int write) {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("拦截器拦截到日志信息:", "Retrofit---------->" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(CONNECT_TIMEOUT_MIL, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIMEOUT_MIL, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT_MIL, TimeUnit.MILLISECONDS)
                .build();
        return okHttpClient;
    }

    /**
     * 获取连接OkHttp连接器
     * @param okHttpClient
     * @return
     */
    public Retrofit getRetrofit(OkHttpClient okHttpClient, String type) {
        if(Constant.TYPE.equals(type)){
            if (retrofitBook == null) {
                retrofitBook = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .client(okHttpClient)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofitBook;
        }
        return null;
    }
}