package com.wtq.retrofittest.net;

import com.wtq.retrofittest.bean.UserBean;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 所有请求接口存放
 * Created by wtq on 2017/6/8.
 */

public interface IApi {

    @GET("login.php")
    Observable<BaseResponse<UserBean>> getUser();

    @GET("userList.php")
    Observable<BaseResponse<List<UserBean>>> getUserList();

    @POST("upLoadText.php")
    @FormUrlEncoded
    Observable<BaseResponse<String>> upLoadText(@Field("text") String text);




}
