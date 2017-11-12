package com.example.shop.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shop.R;

/**
 * Created by Mr.周 on 2017/11/10.
 */

public class ShopFindFragment extends Fragment {


    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shop_find_fragment,container,false);

        return view;
    }
}