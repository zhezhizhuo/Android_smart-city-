package com.qgj.juan_05.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.ItemPagerBinding;
import com.qgj.juan_05.util.SizeUtils;

import org.jetbrains.annotations.NotNull;
@SuppressWarnings("all")
public class BannerView extends LinearLayout {
    private PagerAdapter adapter;

    ItemPagerBinding binding;
    //数据的长度
    int count;
    private String TGA ="AbbnerView";

    public BannerView(Context context) {
        this(context,null);
    }

    public BannerView(Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public BannerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
         super(context, attrs, defStyleAttr);
         binding = ItemPagerBinding.inflate(LayoutInflater.from(context));
         initEven();
         addView(binding.getRoot());
    }


    public static abstract class InitAbbnerAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
            container.removeView((View) object);
        }

        @NonNull
        @NotNull
        @Override
        public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
            View subView = getSubView(container,position);
            container.addView(subView);
            return subView;
          }
        public abstract View getSubView( ViewGroup container, int position);
    }


    public void setDate(PagerAdapter adapter,int count){
        binding.viewpager.setAdapter(adapter);
        this.adapter = adapter;
        //数据的个数
        this.count = count;
        setNumPoint();
    }

    private void setNumPoint() {
        if (adapter!=null&&count>0) {
            binding.container.removeAllViews();
            for (int i = 0; i < count; i++) {
                View view = new View(getContext());
                if (binding.viewpager.getCurrentItem()%count==i){
                    view.setBackground(getContext().getResources().getDrawable(R.drawable.banner_yuan_selected));
                }else {
                    view.setBackground(getContext().getResources().getDrawable(R.drawable.banner_yuan_selcet));
                }
                //设置大小
                LayoutParams params = new LayoutParams(SizeUtils.dipBox(getContext(),10),SizeUtils.dipBox(getContext(),10));
                params.setMargins(SizeUtils.dipBox(getContext(),5),0,SizeUtils.dipBox(getContext(),5),0);
                view.setLayoutParams(params);
                //添加到容器
                binding.container.addView(view);
            }
        }
    }

    private void initEven() {

        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //切换的一个回调方法
            }

            @Override
            public void onPageSelected(int position) {
                //切换停下来的时候
                setNumPoint();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //切换状态改变的回调
            }
        });
    }
}
