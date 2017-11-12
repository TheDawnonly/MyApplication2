package com.example.shop.utils;

import com.example.shop.bean.Bean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Mr.周 on 2017/11/11.
 */

public interface ApiService {
    //GET为GET请求,标明请求地址为BASEURI+/users/{user}
    //{user}会被getFeed方法的参数代替
    //JsonBean为自己设置返回类型,通过.addConverterFactory(GsonConverterFactory.create())
    //利用gson自动完成数据转换
    /*@GET("newlist?page=1&size=100")
    Call<Bean> getFeed();*/
    @GET("newlist?page=1&size=100")
    Observable<Bean> getDataUseRx();
}
