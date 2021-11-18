package com.qgj.juan_05.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.qgj.juan_05.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        SharedPreferences sharedPreferences =getSharedPreferences("pz", Context.MODE_PRIVATE);
        boolean  first = sharedPreferences.getBoolean("first", true);
        if (first){
            //是
            startActivity(new Intent(this,GuildActivity.class));
            return;
        }else {
            //不是

        }
        init();
        setContentView(R.layout.activity_splash);
    }

    private void init() {
        Timer timer=new Timer();
        //timertask实现runnable接口,TimerTask类表示一个在指定时间内执行的task
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