package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.R;
import com.qgj.juan_05.adpater.MoviesPlAdapter;
import com.qgj.juan_05.databinding.ItemDialogMoviesplBinding;
import com.qgj.juan_05.databinding.ItemMovieplBinding;
import com.qgj.juan_05.databinding.MovieInfoFragmentBinding;
import com.qgj.juan_05.netwok.model.DataModel;
import com.qgj.juan_05.netwok.model.MoviePlModel;
import com.qgj.juan_05.netwok.model.MoviesInfoModel;
import com.qgj.juan_05.netwok.model.SendMoviePlModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;
import com.qgj.juan_05.ui.activity.MainActivity;
import com.qgj.juan_05.util.TextUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class MovieInfoFragment extends Fragment {

    private MovieInfoViewModel mViewModel;
    MovieInfoFragmentBinding binding;
    private String TGA="MovieInfoFragment";

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
        initepl();
    }

    private void initepl() {

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
                NavHostFragment.findNavController(MovieInfoFragment.this).navigate(R.id.trailerFragment,new Bundle());

            }
        });
        //跳转
        binding.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id",id);
                NavHostFragment.findNavController(MovieInfoFragment.this).navigate(R.id.cinemaFragment,bundle);
            }
        });
        //想看
        binding.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "想看成功", Toast.LENGTH_SHORT).show();
            }
        });
        //看过
        binding.look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "看过成功", Toast.LENGTH_SHORT).show();

            }
        });
        //评论
        binding.pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());

                AlertDialog dialog = alertDialog.create();
                ItemDialogMoviesplBinding binding = ItemDialogMoviesplBinding.inflate(getLayoutInflater());
                dialog.setView(binding.getRoot());
                dialog.show();
                //获取数据
//                alertDialog.setView(binding.getRoot());
//                alertDialog.setPositiveButton("取消",new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog,int w) {
//                        Toast.makeText(getActivity(), "123"+binding.pltext.getText().toString()+"评分"+binding.score.getNumStars(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//                alertDialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //
//                        Toast.makeText(getContext(), "确定", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                alertDialog.show();
                //确定
                binding.sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (binding.pltext.getText().toString().equals("")){
                            Toast.makeText(getContext(), "评论不能为空 ! !", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (binding.score.getRating()<=0){
                            Toast.makeText(getContext(), "请评分 ~ ~", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //发送评论
                        sendPl(binding.pltext.getText().toString(),binding.score.getRating());
                        dialog.dismiss();
                    }
                });
                //取消
                binding.canle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "已关闭", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                Log.e(TGA,binding.pltext.getText().toString()+"评分"+binding.score.getNumStars());
            }
        });
    }

    private void sendPl(String pltext,float score) {

        //评星
        SendMoviePlModel sendMoviePlModel = new SendMoviePlModel();
        sendMoviePlModel.setContent(pltext);
        sendMoviePlModel.setScore(score);
        sendMoviePlModel.setMovieId(id);
        //获取输入的评论
        Toast.makeText(getContext(), sendMoviePlModel.toString(), Toast.LENGTH_SHORT).show();
         new Thread(()->{
                     try {
                         DataModel dataModel = ServiceDaoImpl.sendMoviePl(sendMoviePlModel, MainActivity.token);
                         getActivity().runOnUiThread(()->{
                             Toast.makeText(getContext(), dataModel.getMsg(), Toast.LENGTH_SHORT).show();
                             initepl();
                         });
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }).start();
        //       更新评论

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

    }

}