package com.example.httpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofitService = HttpRequestUtil.getRetrofitClient(RetrofitService.class.getName());

        Observable<ResponseBody> observable= retrofitService.get();
        HttpObserver<ResponseBody> httpObserver=new HttpObserver<ResponseBody>(this, new HttpObserver.OnNextListener() {
            @Override
            public void onNext(Object o) {
                Log.e(TAG, "onNext: "+o.toString());
            }
        });
        RetrofitConfig.getInstance().statrPostTask(observable, httpObserver);
    }
}
