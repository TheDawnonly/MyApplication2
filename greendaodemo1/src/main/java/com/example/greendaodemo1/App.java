package com.example.greendaodemo1;

import android.app.Application;

import com.afa.tourism.greendao.gen.DaoMaster;
import com.afa.tourism.greendao.gen.UserDao;

/**
 * Created by Mr.周 on 2017/11/10.
 */

public class App extends Application {
    private UserDao userDao;
    //暴露给外部使用此数据库
    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //对数据库进行初始化    liu.db 数据库的名字
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this,"liu.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        userDao = daoMaster.newSession().getUserDao();
    }
}