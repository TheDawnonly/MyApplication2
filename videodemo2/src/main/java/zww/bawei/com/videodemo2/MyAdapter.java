package zww.bawei.com.videodemo2;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.superplayer.library.SuperPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.周 on 2017/11/22.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements SuperPlayer.OnNetChangeListener {
    private List<Beans.DataBean> list;
    private Context context;

    public MyAdapter(List<Beans.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.sp.setNetChangeListener(true)//设置是否见监听手机网络
                .setOnNetChangeListener(this)//设置网络监听的回调
                .onPrepared(new SuperPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared() {
                        /**
                         * 监听视频是否已经准备完成开始播放。（可以在这里处理视频封面的显示跟隐藏）
                         */
                    }
                }).onComplete(new Runnable() {
            @Override
            public void run() {
                /**
                 * 监听视频是否已经播放完成了。（可以在这里处理视频播放完成进行的操作）
                 */
            }
        }).onInfo(new SuperPlayer.OnInfoListener() {
            @Override
            public void onInfo(int what, int extra) {
                /**
                 * 监听视频的相关信息。
                 */

            }
        }).onError(new SuperPlayer.OnErrorListener() {
            @Override
            public void onError(int what, int extra) {
                /**
                 * 监听视频播放失败的回调
                 */

            }
        }).setTitle(list.get(position).getTitle())//设置视频的titleName
                .play(list.get(position).getVedio_url());//开始播放视频
        viewHolder.sp.setScaleType(SuperPlayer.SCALETYPE_FITXY);
        viewHolder.sp.setPlayerWH(0, viewHolder.sp.getMeasuredHeight());//设置竖屏的时候屏幕的高度，如果不设置会切换后按照16:9的高度重置
        Glide.with(context).load(list.get(position).getImage_url()).into(viewHolder.imageView);
        viewHolder.textView.setText(list.get(position).getContent());
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return list.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public SuperPlayer sp;
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.item_iv);
            sp = view.findViewById(R.id.item_player);
            textView = view.findViewById(R.id.item_tv);
        }
    }

    @Override
    public void onWifi() {
        Toast.makeText(context, "当前网络环境是WIFI", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMobile() {
        Toast.makeText(context, "当前网络环境是手机网络", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisConnect() {
        Toast.makeText(context, "网络链接断开", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoAvailable() {
        Toast.makeText(context, "无网络链接", Toast.LENGTH_SHORT).show();
    }

}