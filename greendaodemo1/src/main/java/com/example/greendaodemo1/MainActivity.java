package com.example.greendaodemo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.afa.tourism.greendao.gen.UserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App app = (App) getApplication();//得到数据库使用
        userDao = app.getUserDao();
    }

    public void add(View v) {//添加单条数据
        User u = new User(null, "刘备");
        userDao.insert(u);
    }

    public void addAll(View v) {//批量添加
        userDao.insertInTx(new User[]{new User(null, "达摩"),
                new User(null, "赵云"),
                new User(null, "亚瑟")
                , new User(null, "吕布")});
    }

    public void delete(View v) {//删除单条
        userDao.deleteByKey((long) 1);
    }

    public void deleteAll(View v) {  //批量删除
        userDao.deleteAll();
    }

    public void updata(View v) {   //修改单条
        User u = new User(Long.valueOf(1), "孙尚香");
        userDao.update(u);
    }

    public void updataAll(View v) {   //批量修改
        userDao.updateInTx(new User[]{
                new User(Long.valueOf(2), "孙尚香1"),
                new User(Long.valueOf(3), "孙尚香2")
                , new User(Long.valueOf(4), "孙尚香3")
        });
    }

    public void select(View v) {   //条件查询

        QueryBuilder<User> userQueryBuilder = userDao.queryBuilder();
        List<User> list = userQueryBuilder.build().list();
        List<String> l = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            l.add(list.get(i).getName());
        }
        Toast.makeText(MainActivity.this, l.toString(), Toast.LENGTH_SHORT).show();
    }

    public void selectAll(View v) {  //查询所有

        List<User> users = userDao.loadAll();
        //吐司事件
        Toast.makeText(MainActivity.this, users.toString(), Toast.LENGTH_SHORT).show();
    }
}
