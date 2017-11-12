package com.example.shop;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.shop.fragment.ShopCatFragment;
import com.example.shop.fragment.ShopFindFragment;
import com.example.shop.fragment.ShopFirstFragment;
import com.example.shop.fragment.ShopMyFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txt_channel;
    private TextView txt_message;
    private TextView txt_better;
    private TextView txt_setting;
    private FrameLayout ly_content;

    //Fragment
    private ShopFirstFragment firstFragment;
    private ShopFindFragment findFragment;
    private ShopCatFragment catFragment;
    private ShopMyFragment myFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        bindViews();
        txt_channel.performClick();
    }

    //UI组件初始化与事件绑定
    private void bindViews() {
        txt_channel = (TextView) findViewById(R.id.txt_channel);
        txt_message = (TextView) findViewById(R.id.txt_message);
        txt_better = (TextView) findViewById(R.id.txt_better);
        txt_setting = (TextView) findViewById(R.id.txt_setting);
        ly_content = (FrameLayout) findViewById(R.id.ly_content);

        txt_channel.setOnClickListener(this);
        txt_message.setOnClickListener(this);
        txt_better.setOnClickListener(this);
        txt_setting.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    private void setSelected() {
        txt_channel.setSelected(false);
        txt_message.setSelected(false);
        txt_better.setSelected(false);
        txt_setting.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (firstFragment != null) fragmentTransaction.hide(firstFragment);
        if (findFragment != null) fragmentTransaction.hide(findFragment);
        if (catFragment != null) fragmentTransaction.hide(catFragment);
        if (myFragment != null) fragmentTransaction.hide(myFragment);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()) {
            case R.id.txt_channel:
                setSelected();
                txt_channel.setSelected(true);
                if (firstFragment == null) {
                    firstFragment = new ShopFirstFragment();
                    fTransaction.add(R.id.ly_content, firstFragment);
                } else {
                    fTransaction.show(firstFragment);
                }
                break;
            case R.id.txt_message:
                setSelected();
                txt_message.setSelected(true);
                if (findFragment == null) {
                    findFragment = new ShopFindFragment();
                    fTransaction.add(R.id.ly_content, findFragment);
                } else {
                    fTransaction.show(findFragment);
                }
                break;
            case R.id.txt_better:
                setSelected();
                txt_better.setSelected(true);
                if (catFragment == null) {
                    catFragment = new ShopCatFragment();
                    fTransaction.add(R.id.ly_content, catFragment);
                } else {
                    fTransaction.show(catFragment);
                }
                break;
            case R.id.txt_setting:
                setSelected();
                txt_setting.setSelected(true);
                if (myFragment == null) {
                    myFragment = new ShopMyFragment();
                    fTransaction.add(R.id.ly_content, myFragment);
                } else {
                    fTransaction.show(myFragment);
                }
                break;
        }
        fTransaction.commit();
    }
}
