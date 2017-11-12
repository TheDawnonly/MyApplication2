package com.example.shop.model;

import android.util.Log;

import com.example.shop.bean.Bean;
import com.example.shop.utils.ApiService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr.周 on 2017/11/10.
 */

public class ShopModel {

    public void getData(final ShopModelListener listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apiv3.yangkeduo.com/v5/")// '/'尽量放在baseUri的后面而不是放在注解中url的前面
                //添加转换器完成数据转换,将Json类型的数据转换为JavaBean
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //得到APIService
        ApiService service = retrofit.create(ApiService.class);
        /*Call<Bean> call = service.getFeed();
        call.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                String s = response.body().toString();
                Log.d("Response=======", "s" + s);

            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {

            }
        });*/
        service.getDataUseRx().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Bean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Bean bean) {
                Log.d("observe=========", "" + bean.getGoods_list().get(0).getGoods_name());
                listener.callBackSuccess(bean);
            }
        });

    }







   /* public void getData(final ShopModelListener listener) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://apiv3.yangkeduo.com/v5/newlist?page=1&size=100")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                Bean bean = gson.fromJson(result, Bean.class);
                listener.callBackSuccess(bean);
            }
        });
    }*/
}
