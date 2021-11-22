package com.qgj.juan_05.adpater;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.qgj.juan_05.netwok.model.HomeNewsModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NewsViewPager extends FragmentPagerAdapter {

    private List<HomeNewsModel.DataDTO>mList;

    private List<Fragment> fragmentList;

    public NewsViewPager(@NonNull @NotNull FragmentManager fm, List<HomeNewsModel.DataDTO> list, List<Fragment> fragmentList) {
        super(fm);
        mList = list;
        this.fragmentList = fragmentList;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).getName();
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }
//
//    @NonNull
//    @NotNull
//    @Override
//    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
//        return super.instantiateItem(container, position);
//    }
//    @Override
//    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
//        super.destroyItem(container, position, object);
//    }
    @Override
    public int getCount() {

        return mList.size();
    }
}
