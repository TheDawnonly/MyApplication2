package com.example.shop.presenter;

import com.example.shop.bean.Bean;
import com.example.shop.model.ShopModel;
import com.example.shop.model.ShopModelListener;
import com.example.shop.view.ShopViewListener;

/**
 * Created by Mr.周 on 2017/11/10.
 */

public class ShopPresenter {
    private ShopViewListener listener;
    private ShopModel shopModel;

    public ShopPresenter(ShopViewListener listener) {
        this.listener = listener;
        this.shopModel = new ShopModel();
    }

    public void getData() {
        shopModel.getData(new ShopModelListener() {
            @Override
            public void callBackSuccess(Bean bean) {
                listener.callBackSuccess(bean);
            }
        });
    }
}
