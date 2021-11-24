package com.qgj.juan_05.ui.fragment.services;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.R;
import com.qgj.juan_05.adpater.CinameAdapter;
import com.qgj.juan_05.adpater.MovieAdapter;
import com.qgj.juan_05.databinding.FragmentMoviesfragmentBinding;
import com.qgj.juan_05.netwok.model.AbnnerModel;
import com.qgj.juan_05.netwok.model.CinameModel;
import com.qgj.juan_05.netwok.model.MovieModel;
import com.qgj.juan_05.ui.activity.MainActivity;
import com.qgj.juan_05.ui.view.BannerView;

import java.util.List;


public class Moviesfragment extends Fragment {

    FragmentMoviesfragmentBinding binding;
    MoviesViewModel mViewModel;

    NavController navController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMoviesfragmentBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
        initview();
        initDate();
        //搜索框
        initserch();
        //轮播图
        loadBanner();
        //getCinema 影院
        loadCinema();
    }

    private void loadCinema() {
        mViewModel.getCinamemodel().observe(getViewLifecycleOwner(), new Observer<CinameModel>() {
            @Override
            public void onChanged(CinameModel cinameModel) {
                CinameAdapter adapter = new CinameAdapter(cinameModel.getRows(),getActivity(),navController);
                binding.all.setText(" 全部"+cinameModel.getTotal()+"部");
                binding.cinema.setAdapter(adapter);
            }
        });
    }

    private void loadBanner() {

        mViewModel.getBanner().observe(getViewLifecycleOwner(), new Observer<AbnnerModel>() {
            @Override
            public void onChanged(AbnnerModel abnnerModel) {
                //数据
                binding.banner.setDate(new BannerView.InitAbbnerAdapter() {
                    @Override
                    public View getSubView(ViewGroup container, int position) {
                        ImageView img = new ImageView(getActivity());
                        //
                        int postion = position%abnnerModel.getTotal();
                        Glide.with(getActivity()).load(MainActivity.serverURL+abnnerModel.getRows().get(postion).getAdvImg()).fitCenter().into(img);
                        if (img.getParent() instanceof ViewGroup) {
                            ((ViewGroup) img.getParent()).removeView(img);
                        }
                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), abnnerModel.getRows().get(postion).getAdvTitle() ,Toast.LENGTH_SHORT).show();
                            }
                        });
                        return img;
                    }
                },abnnerModel.getTotal());
            }
        });
    }

    private void initserch() {
        binding.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Toast.makeText(getActivity(), ""+s.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                if (!str.equals("")){
                    Toast.makeText(getActivity(), ""+s.toString(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    private void initDate() {
        mViewModel.getMovieDate().observe(getViewLifecycleOwner(), new Observer<MovieModel>() {
            @Override
            public void onChanged(MovieModel movieModel) {
                int total = movieModel.getTotal();
                binding.hotnum.setText(" 全部"+total+"部");
                List<MovieModel.RowsDTO> rows = movieModel.getRows();
                MovieAdapter movieAdapter = new MovieAdapter(rows,getActivity(),navController);
                binding.hotmvie.setAdapter(movieAdapter);
            }
        });
    }
    private void initview() {
        binding.hotmvie.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.cinema.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }
}