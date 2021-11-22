package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qgj.juan_05.R;
import com.qgj.juan_05.adpater.HomeNewsInfoAdapter;
import com.qgj.juan_05.adpater.HomeNewsInfoPlAdapter;
import com.qgj.juan_05.databinding.NewsViewPagerFragmentBinding;
import com.qgj.juan_05.netwok.model.HomeNewAllModel;
import com.qgj.juan_05.netwok.model.NewsInfoModel;

public class NewsViewPagerFragment extends Fragment {

    private NewsViewPagerViewModel mViewModel;
    NewsViewPagerFragmentBinding binding;
    private static final String ARG_SECTION_NUMBER = "MSG";
    public static NewsViewPagerFragment newInstance(int id) {
        NewsViewPagerFragment fragment = new NewsViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, id);
        fragment.setArguments(bundle);
        return fragment;
    }
    NavController navController;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = NavHostFragment.findNavController(NewsViewPagerFragment.this);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = NewsViewPagerFragmentBinding.inflate(getLayoutInflater(), container, false);
        // TODO: Use the ViewModel
        //设置数据
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NewsViewPagerViewModel.class);
        binding.newsinfo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        if (getArguments() != null) {
            int anInt = getArguments().getInt(ARG_SECTION_NUMBER);
            mViewModel.getNewsInfoModelMutableLiveData(anInt).observe(getViewLifecycleOwner(), new Observer<HomeNewAllModel>() {
                @Override
                public void onChanged(HomeNewAllModel homeNewAllModel) {
                    binding.newsinfo.setAdapter(new HomeNewsInfoAdapter(homeNewAllModel.getRows(),getActivity(),navController));
                }
            });

        }
        binding.shuai.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(()->{
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.shuai.setRefreshing(false);
                        Toast.makeText(getActivity(), "刷新成功!!!", Toast.LENGTH_SHORT).show();
                    }
                });
                }).start();
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}