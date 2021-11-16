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
import android.widget.TextView;
import android.widget.Toast;

import com.qgj.juan_05.R;
import com.qgj.juan_05.adpater.MovieAdapter;
import com.qgj.juan_05.databinding.FragmentMoviesfragmentBinding;
import com.qgj.juan_05.netwok.model.MovieModel;

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
    }
}