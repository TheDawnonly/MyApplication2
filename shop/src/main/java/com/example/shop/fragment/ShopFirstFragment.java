package com.example.shop.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shop.R;
import com.example.shop.adapter.MyFirstAdapter;
import com.example.shop.bean.Bean;
import com.example.shop.presenter.ShopPresenter;
import com.example.shop.view.ShopViewListener;

import java.util.List;

/**
 * Created by Mr.周 on 2017/11/10.
 */

public class ShopFirstFragment extends Fragment implements ShopViewListener {
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private ShopPresenter presenter;
    private Handler handler = new Handler();
    private List<Bean.GoodsListBean> list;
    private MyFirstAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shop_first_fragment, container, false);
        recyclerView = view.findViewById(R.id.first_fragmentRv);
        presenter = new ShopPresenter(this);
        presenter.getData();
        return view;
    }


    @Override
    public void callBackSuccess(Bean bean) {
        list = bean.getGoods_list();
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter = new MyFirstAdapter(list, getActivity());
        recyclerView.setAdapter(adapter);
    }
}