package com.example.shop.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.bean.Bean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Mr.周 on 2017/11/12.
 */

public class MyFirstAdapter extends RecyclerView.Adapter<MyFirstAdapter.MyViewHolder> {

    private List<Bean.GoodsListBean> list;
    private Context context;

    public MyFirstAdapter(List<Bean.GoodsListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv.setText(list.get(position).getGoods_name());
        Uri uri = Uri.parse(list.get(position).getImage_url());
//        sdv.setImageURI(uri);

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        holder.sdv.setController(controller);
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.first_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        SimpleDraweeView sdv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.item_tv);
            sdv = view.findViewById(R.id.item_sdv);
        }

    }
}