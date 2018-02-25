package com.example.httpdemo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018-02-25.
 */

public interface RetrofitService {
    @GET("book/search")
    Observable<ResultBean> getSearchBook(@Query("q") String name, @Query("count") int count);
}
