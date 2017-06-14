package com.wtq.retrofittest.net;

import com.wtq.retrofittest.bean.UserBean;

import java.util.List;

import rx.Subscriber;

/**
 * 接口具体实现类
 * Created by wtq on 2017/6/9.
 */
public class ApiUtils {

    private IApi api;

    public ApiUtils() {
        api = HttpUtil.getInstance().getApi();
    }


    /**
     * 获取登录 返回Object的
     *
     * @param subscriber subscriber
     */
    public void getUser(Subscriber<BaseResponse<UserBean>> subscriber) {
        api.getUser()
                .compose(RxHelper.schedulers())
                .subscribe(subscriber);

    }

    public void getUserList(Subscriber<BaseResponse<List<UserBean>>> subscriber) {
        api.getUserList()
                .compose(RxHelper.schedulers())
                .subscribe(subscriber);
    }


    public void upLoadText(String params, Subscriber<BaseResponse<String>> subscriber) {
        api.upLoadText(params)
                .compose(RxHelper.schedulers())
                .subscribe(subscriber);
    }
}
