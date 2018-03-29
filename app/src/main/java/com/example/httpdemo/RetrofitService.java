package com.example.httpdemo;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018-02-25.
 */

public interface RetrofitService {
    @GET("book/search")
    Observable<RequestResults<ResultBean>> getSearchBook(@Query("q") String name, @Query("count") int count);

    @GET("/")
    Observable<ResponseBody> get();
}
