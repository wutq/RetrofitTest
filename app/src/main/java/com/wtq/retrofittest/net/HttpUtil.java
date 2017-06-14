package com.wtq.retrofittest.net;

import android.support.annotation.NonNull;
import android.util.Log;

import com.wtq.retrofittest.biz.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 网络请求帮助类
 * Created by wtq on 2017/6/9.
 */

public class HttpUtil {

    private static final String TGA = "HttpUtil";

    private static HttpUtil instance;
    private OkHttpClient client;
    private Retrofit retrofit;
    private IApi iApi;

    static HttpUtil getInstance() {
        if (instance == null) {
            synchronized (HttpUtil.class) {
                if (instance == null) {
                    instance = new HttpUtil();
                }
            }
        }
        return instance;
    }

    private HttpUtil() {
        client = getClient();
        retrofit = getRetrofit();
    }

    /**
     * 创建OkHttpClient对象
     *
     * @return OkHttpClient
     */
    @NonNull
    private OkHttpClient getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e(TGA, message);

            }
        });
        // 注意这个对象HttpLoggingInterceptor注意这里的拦截器 用来设置log的拦截等级的
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .connectTimeout(Constant.TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(Constant.TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(Constant.TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 创建Retrofit对象并且设置参数
     *
     * @return Retrofit
     */
    @NonNull
    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
    }


    /**
     * 通过retrofit创建Iapi的实现类对象(retrofit已经帮你实现好了 不需要自己去实现 只要调用方法)
     *
     * @return IApi
     */
    IApi getApi() {
        if (iApi == null) {
            iApi = retrofit.create(IApi.class);
        }
        return iApi;    
    }

    // 设置请求的线程 重复代码 每次都要写所以封装起来
    public static Observable.Transformer schedulers() {
        return new Observable.Transformer() {
            @Override
            public Object call(Object observable) {
                return ((Observable) observable)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
