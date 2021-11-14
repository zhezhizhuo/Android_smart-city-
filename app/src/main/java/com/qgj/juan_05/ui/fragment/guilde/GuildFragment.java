package com.qgj.juan_05.ui.fragment.guilde;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.DialogPortBinding;
import com.qgj.juan_05.databinding.FragmentGuildBinding;
import com.qgj.juan_05.ui.activity.MainActivity;
import com.qgj.juan_05.util.PagerController;

import org.jetbrains.annotations.NotNull;


public class GuildFragment extends AppCompatActivity {
    FragmentGuildBinding binding;

    boolean first;
    ImageView [] imageViews;
    ImageButton [] ImageButtons;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在创建之前判断是否是第一次进入app 并且计入到app的sp 文件里面去 文件名pz
        binding = FragmentGuildBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences sharedPreferences =getSharedPreferences("pz", Context.MODE_PRIVATE);
        first = sharedPreferences.getBoolean("first", true);
        getSupportActionBar().hide();

        //适配器模式
        //如果是第一次登录那就进去引导页面
            initimg();
            ititview();
            initEven();
            initbth();
            initPotion();
            binding.bth1.setSelected(true);
    }

    private void initPotion() {
        ImageButtons = new ImageButton[5];
        ImageButtons[0] = binding.bth1;
        ImageButtons[1]  =binding.bth2;
        ImageButtons[2]  =binding.bth3;
        ImageButtons[3]  =binding.bth4;
        ImageButtons[4]  =binding.bth5;

    }

    private void initbth() {
        DialogPortBinding inflate = DialogPortBinding.inflate(getLayoutInflater());
        binding.dork.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(GuildFragment.this);
                    alertDialog.setView(inflate.getRoot());
                    alertDialog.setTitle("网络设置");
                    alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(GuildFragment.this, inflate.iptext.getText(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();

                }
            });
        //配置文件
        SharedPreferences sharedPreferences = getSharedPreferences("pz",MODE_PRIVATE);
        binding.goindex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                SharedPreferences.Editor first = edit.putBoolean("first", false);
                first.commit();
                startActivity(new Intent(GuildFragment.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
    }
    private void initEven() {
        binding.guild.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //当页面滑动的时候
            }
            @Override
            public void onPageSelected(int position) {
                //切换停下来的时候
                    if (position>3){//4
                        binding.dork.setVisibility(View.VISIBLE);
                        binding.goindex.setVisibility(View.VISIBLE);
                    }else {//     1~3
                        binding.dork.setVisibility(View.GONE);
                        binding.goindex.setVisibility(View.GONE);
                    }
                    setPotion(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setPotion(int position) {
        for (ImageButton imageButton : ImageButtons) {
            imageButton.setSelected(false);
        }
        ImageButtons[position].setSelected(true);
    }

    private void initimg() {
       imageViews = new ImageView[5];
//        Log.e("TGA",imageViews[0]+""+getActivity().getResources())
        imageViews[0] = new ImageView(this);
        imageViews[0].setImageDrawable(this.getResources().getDrawable(R.drawable.guide1));
        imageViews[1] = new ImageView(this);
        imageViews[1].setImageDrawable(this.getResources().getDrawable(R.drawable.guide2));
        imageViews[2] = new ImageView(this);
        imageViews[2].setImageDrawable(this.getResources().getDrawable(R.drawable.guide3));
        imageViews[3] = new ImageView(this);
        imageViews[3].setImageDrawable(this.getResources().getDrawable(R.drawable.guide4));
        imageViews[4] = new ImageView(this);
        imageViews[4].setImageDrawable(this.getResources().getDrawable(R.drawable.img_bg));
    }

    private void ititview() {
        binding.guild.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageViews.length;
            }
            @Override
            public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
                return object==view;
            }
            @Override
            public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
                container.removeView((View) object);
            }
            @NonNull
            @NotNull
            @Override
            public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
                ImageView img = imageViews[position];
                if(img.getParent() instanceof ViewGroup){
                    ((ViewGroup)img.getParent()).removeView(img);
                }
                img.setScaleType(ImageView.ScaleType.FIT_XY);
                container.addView(img);
                return img;
            }
        });
    }
}