package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.CinemaFragmentBinding;
import com.qgj.juan_05.netwok.model.MoviesInfoModel;
import com.qgj.juan_05.ui.activity.MainActivity;
import com.qgj.juan_05.util.TextUtil;

public class CinemaFragment extends Fragment {

    private CinemaViewModel mViewModel;
    CinemaFragmentBinding binding;
    int id;
    public static CinemaFragment newInstance() {
        return new CinemaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CinemaFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CinemaViewModel.class);
        // TODO: Use the ViewModel
         id = getArguments().getInt("id", 1);
        initdate();
    }

    private void initdate() {
        mViewModel.getMode(id).observe(getViewLifecycleOwner(), new Observer<MoviesInfoModel>() {

            @Override
            public void onChanged(MoviesInfoModel moviesInfoModel) {
                //设置 数据
                MoviesInfoModel.DataDTO data = moviesInfoModel.getData();

                //电影名字
                binding.moviename.setText(data.getName());
                //电影是否推荐
                binding.tuijia.setText((data.getRecommend().equals("Y") == true  ?"推荐":""));
                //电影语言
                binding.language.setText("语言: "+data.getLanguage());
                //上场时间
                binding.time.setText("上映时间: "+data.getPlayDate());
                //评分
                binding.score.setText(data.getScore()+".0");
                //想看人数
                binding.likeNum.setText(data.getLikeNum()+" 人想看");
                //人看过
                binding.looknum.setText(data.getFavoriteNum()+" 人看过");
                //时长
                binding.longtime.setText("片长: "+data.getDuration()+"分钟");
                //加载图片
                Glide.with(getContext()).load(MainActivity.serverURL+data.getCover()).into(binding.img);
            }
        });
    }

}