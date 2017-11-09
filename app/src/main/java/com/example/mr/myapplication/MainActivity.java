package com.example.mr.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    DownLoadFile downLoadFile;
    private String loadUrl = "http://gdown.baidu.com/data/wisegame/d2fbbc8e64990454/wangyiyunyinle_87.apk";
    private String filePath = Environment.getExternalStorageDirectory() + "/" + "网易云音乐.apk";
    private ProgressBar progressBar;
    private TextView tvprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.main_pb);
        tvprogress = findViewById(R.id.tv_progress);
        downLoadFile = new DownLoadFile(this, loadUrl, filePath, 3);
        downLoadFile.setOnDownLoadListener(new DownLoadFile.DownLoadListener() {
            @Override
            public void getProgress(int progress) {
                tvprogress.setText("当前进度 ：" + progress + " %");
                progressBar.setProgress(progress);
            }

            @Override
            public void onComplete() {
                Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                //下载完成后跳转到安装界面
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setDataAndType(Uri.fromFile(new File(filePath)), "application/vnd.android.package-archive");
                startActivity(i);
            }


            @Override
            public void onFailure() {
                Toast.makeText(MainActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downLoadFile.downLoad();
            }
        });
        findViewById(R.id.bt_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downLoadFile.onPause();
            }
        });
        findViewById(R.id.bt_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downLoadFile.onStart();
            }
        });
        findViewById(R.id.bt_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downLoadFile.cancel();
            }
        });
    }

    @Override
    protected void onDestroy() {
        downLoadFile.onDestroy();
        super.onDestroy();
    }
}
