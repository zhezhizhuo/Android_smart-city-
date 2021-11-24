package com.qgj.juan_05.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.R;
import com.qgj.juan_05.netwok.model.AbnnerModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    ImageView mImageView;
    private String TGA ="SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar()!=null) {
            getSupportActionBar().hide();
        }

        SharedPreferences sharedPreferences =getSharedPreferences("pz", Context.MODE_PRIVATE);
        boolean  first = sharedPreferences.getBoolean("first", true);
        if (first){
            //是
            startActivity(new Intent(this,GuildActivity.class));
            return;
        }
        setContentView(R.layout.activity_splash);
        mImageView = findViewById(R.id.open);
        initView();

    }

    private void initView() {
         new Thread(()->{
                     try {
                         AbnnerModel openBanner = ServiceDaoImpl.getOpenBanner(0, 1);
                         //加载图片
                         runOnUiThread(new Runnable() {
                             @Override
                             public void run() {
                                 String url = openBanner.getRows().get(0).getAdvImg();
                                 Glide.with(SplashActivity.this).load(MainActivity.serverURL+url).into(mImageView);
                                 init();
                             }
                         });
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }).start();
    }

    private void init() {
        Timer timer=new Timer();
        //timer task 实现runnable接口,TimerTask类表示一个在指定时间内执行的task
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        };
        timer.schedule(task, 1500);//设置这个task在延迟三秒之后自动执行
    }
}