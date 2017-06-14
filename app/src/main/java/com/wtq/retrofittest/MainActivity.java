package com.wtq.retrofittest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wtq.retrofittest.bean.UserBean;
import com.wtq.retrofittest.net.ApiUtils;
import com.wtq.retrofittest.net.BaseResponse;

import java.util.List;

import rx.Subscriber;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "MainActivity";
    private ApiUtils apiUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        apiUtils = new ApiUtils();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                getUser();
                break;
            case R.id.btn2:
                getUserList();
                break;
            case R.id.btn3:
                upLoadText();
                break;

        }
    }

    private void getUserList() {
        apiUtils.getUserList(new Subscriber<BaseResponse<List<UserBean>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Log.e(TAG, "error");
            }

            @Override
            public void onNext(BaseResponse<List<UserBean>> listBaseResponse) {
                Log.e(TAG, "请求成功：" + listBaseResponse.getData().size());
            }
        });


    }

    private void getUser() {
        apiUtils.getUser(new Subscriber<BaseResponse<UserBean>>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Log.e(TAG, "error");
            }

            @Override
            public void onNext(BaseResponse<UserBean> userBeanBaseResponse) {
                Log.e(TAG, "请求成功：" + userBeanBaseResponse.getData().toString());
            }
        });

    }

    private void upLoadText() {
        apiUtils.upLoadText("一段文字", new Subscriber<BaseResponse<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Log.e(TAG, "error");
            }

            @Override
            public void onNext(BaseResponse<String> stringBaseResponse) {
                Log.e(TAG, stringBaseResponse.toString());
            }
        });
    }
}



















