package com.example.httpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RetrofitService retrofitService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofitService = HttpRequestUtil.getRetrofitClient(RetrofitService.class.getName(), Constant.TYPE);
        retrofitService.getSearchBook("name",1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResultBean resultBean) {

                    }
                });
    }
}
