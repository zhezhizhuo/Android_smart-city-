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
        //??????Id
        Bundle ars = getArguments();
          id=  ars.getInt("id", 2);
        initdate();
        //??????????????????
        initview();
        initbth();
        initepl();
    }

    private void initepl() {

        mViewModel.getModpl(id).observe(getViewLifecycleOwner(), new Observer<MoviePlModel>() {
            @Override
            public void onChanged(MoviePlModel moviePlModel) {
                List<MoviePlModel.RowsDTO> rows = moviePlModel.getRows();
                //?????????
                binding.zpl.setText("????????????: "+moviePlModel.getTotal());
                MoviesPlAdapter plAdapter = new MoviesPlAdapter(rows,getContext());
                binding.revpl.setAdapter(plAdapter);
            }
        });
    }

    private void initview() {
        binding.revpl.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    private void initbth() {
        //??????????????????
        binding.swich.setOnClickListener(new View.OnClickListener() {

            boolean flag;
            @Override
            public void onClick(View v) {
                if (flag){
                    //??????
                    binding.introduction.setLines(2);
                    binding.swich.setText("??????");

                }else {
                    //??????
                    binding.introduction.setMaxLines(20);
                    binding.swich.setText("??????");
                }
                flag =!flag;
            }
        });

        //??????
        binding.bofang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id",id);
                NavHostFragment.findNavController(MovieInfoFragment.this).navigate(R.id.trailerFragment,bundle);
            }
        });
        //??????
        binding.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id",id);
                NavHostFragment.findNavController(MovieInfoFragment.this).navigate(R.id.cinemaFragment,bundle);
            }
        });
        //??????
        binding.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "????????????", Toast.LENGTH_SHORT).show();
            }
        });
        //??????
        binding.look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "????????????", Toast.LENGTH_SHORT).show();

            }
        });
        //??????
        binding.pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());

                AlertDialog dialog = alertDialog.create();
                ItemDialogMoviesplBinding binding = ItemDialogMoviesplBinding.inflate(getLayoutInflater());
                dialog.setView(binding.getRoot());
                dialog.show();
                //????????????
//                alertDialog.setView(binding.getRoot());
//                alertDialog.setPositiveButton("??????",new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog,int w) {
//                        Toast.makeText(getActivity(), "123"+binding.pltext.getText().toString()+"??????"+binding.score.getNumStars(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//                alertDialog.setNegativeButton("??????", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //
//                        Toast.makeText(getContext(), "??????", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                alertDialog.show();
                //??????
                binding.sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (binding.pltext.getText().toString().equals("")){
                            Toast.makeText(getContext(), "?????????????????? ! !", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (binding.score.getRating()<=0){
                            Toast.makeText(getContext(), "????????? ~ ~", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //????????????
                        sendPl(binding.pltext.getText().toString(),binding.score.getRating());
                        dialog.dismiss();
                    }
                });
                //??????
                binding.canle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "?????????", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                Log.e(TGA,binding.pltext.getText().toString()+"??????"+binding.score.getNumStars());
            }
        });
    }

    private void sendPl(String pltext,float score) {

        //??????
        SendMoviePlModel sendMoviePlModel = new SendMoviePlModel();
        sendMoviePlModel.setContent(pltext);
        sendMoviePlModel.setScore(score);
        sendMoviePlModel.setMovieId(id);
        //?????????????????????
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
        //       ????????????

    }

    private void initdate() {
        mViewModel.getMode(id).observe(getViewLifecycleOwner(), new Observer<MoviesInfoModel>() {

            @Override
            public void onChanged(MoviesInfoModel moviesInfoModel) {
                //?????? ??????
                MoviesInfoModel.DataDTO data = moviesInfoModel.getData();
               if (data==null){
                   Log.e("TGA",moviesInfoModel.getMsg()+"");
                   return;
               }
                //????????????
                binding.moviename.setText(data.getName());
                //??????????????????
                binding.tuijia.setText((data.getRecommend().equals("Y") == true  ?"??????":""));
                //????????????
                binding.language.setText("??????: "+data.getLanguage());
                //????????????
                binding.time.setText("????????????: "+data.getPlayDate());
                //??????
                binding.score.setText(data.getScore()+".0");
                //????????????
                binding.likeNum.setText(data.getLikeNum()+" ?????????");
                //?????????
                binding.looknum.setText(data.getFavoriteNum()+" ?????????");
                //??????
                binding.longtime.setText("??????: "+data.getDuration()+"??????");
                //??????
                binding.introduction.setText("      "+ TextUtil.FormatString(data.getIntroduction()));
                //????????????
                Glide.with(getContext()).load(MainActivity.serverURL+data.getCover()).into(binding.img);
            }
        });

    }

}