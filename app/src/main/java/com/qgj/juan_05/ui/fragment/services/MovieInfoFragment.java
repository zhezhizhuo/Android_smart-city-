package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.R;
import com.qgj.juan_05.adpater.MoviesPlAdapter;
import com.qgj.juan_05.databinding.MovieInfoFragmentBinding;
import com.qgj.juan_05.netwok.model.MoviePlModel;
import com.qgj.juan_05.netwok.model.MoviesInfoModel;
import com.qgj.juan_05.ui.activity.MainActivity;
import com.qgj.juan_05.util.TextUtil;

import java.util.Date;
import java.util.List;

public class MovieInfoFragment extends Fragment {

    private MovieInfoViewModel mViewModel;
    MovieInfoFragmentBinding binding;

    public static MovieInfoFragment newInstance() {
        return new MovieInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MovieInfoFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
    int id ;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MovieInfoViewModel.class);
        // TODO: Use the ViewModel
        //获取Id
        Bundle ars = getArguments();
          id=  ars.getInt("id", 2);
        initdate();
        //所有按钮事件
        initview();
        initbth();
    }

    private void initview() {
        binding.revpl.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    private void initbth() {
        //展开关闭按钮
        binding.swich.setOnClickListener(new View.OnClickListener() {

            boolean flag;
            @Override
            public void onClick(View v) {
                if (flag){
                    //关闭
                    binding.introduction.setLines(2);
                    binding.swich.setText("展开");

                }else {
                    //打开
                    binding.introduction.setMaxLines(20);
                    binding.swich.setText("收起");
                }
                flag =!flag;
            }
        });

        //播放
        binding.bofang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "暂无视频", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initdate() {
        mViewModel.getMode(id).observe(getViewLifecycleOwner(), new Observer<MoviesInfoModel>() {

            @Override
            public void onChanged(MoviesInfoModel moviesInfoModel) {
                //设置 数据
                MoviesInfoModel.DataDTO data = moviesInfoModel.getData();
               if (data==null){
                   Log.e("TGA",moviesInfoModel.getMsg()+"");
                   return;
               }
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
                //简介
                binding.introduction.setText("      "+ TextUtil.FormatString(data.getIntroduction()));
                //加载图片
                Glide.with(getContext()).load(MainActivity.serverURL+data.getCover()).into(binding.img);
            }
        });

        mViewModel.getModpl(id).observe(getViewLifecycleOwner(), new Observer<MoviePlModel>() {
            @Override
            public void onChanged(MoviePlModel moviePlModel) {
                List<MoviePlModel.RowsDTO> rows = moviePlModel.getRows();
                //总评论
                binding.zpl.setText("评论总数: "+moviePlModel.getTotal());
                MoviesPlAdapter plAdapter = new MoviesPlAdapter(rows,getContext());
                binding.revpl.setAdapter(plAdapter);
            }
        });
    }

}