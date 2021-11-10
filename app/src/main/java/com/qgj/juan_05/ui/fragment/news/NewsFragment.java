package com.qgj.juan_05.ui.fragment.news;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.qgj.juan_05.R;
import com.qgj.juan_05.adpater.HomeNewsInfoAdapter;
import com.qgj.juan_05.databinding.NewsFragmentBinding;
import com.qgj.juan_05.netwok.model.HomeNewAllModel;
import com.qgj.juan_05.netwok.model.HomeNewsModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;
import com.qgj.juan_05.ui.activity.MainActivity;
import com.qgj.juan_05.ui.fragment.HomeViewModel;
import com.qgj.juan_05.ui.view.BannerView;
import com.qgj.juan_05.util.PagerController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NewsFragment extends Fragment {

    private NewsViewModel mViewModel;
    private HomeViewModel mHomeViewModel;
    NewsFragmentBinding binding;
    NavController navController;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = NewsFragmentBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        mHomeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
        //切换颜色
        binding.newsheader.setTabTextColors(Color.GRAY,Color.RED);
        //        加载轮播图
        loadebanner();
        loadNewsAll();
    }

    private void loadebanner() {

        mViewModel.getMbannerDate().observe(getViewLifecycleOwner(),abnnerModel->{
            binding.banner.setDate(new BannerView.InitAbbnerAdapter() {
                @Override
                public View getSubView(ViewGroup container, int position) {
                    ImageView img =  new ImageView(getContext());
                    //算出真实的postion
                    int realpositton = position%abnnerModel.getTotal();
                    //

                    Glide.with(getContext()).load(MainActivity.serverURL + abnnerModel.getRows().get(realpositton).getAdvImg()).into(img);
                    if(img.getParent() instanceof ViewGroup){
                        ((ViewGroup)img.getParent()).removeView(img);
                    }
                    img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(), abnnerModel.getRows().get(realpositton).getServModule(), Toast.LENGTH_SHORT).show();
                            PagerController.PagerController(navController,"metro_query/index");
                        }
                    });
                    img.setScaleType(ImageView.ScaleType.FIT_XY);
                    return img;
                }
            }, abnnerModel.getTotal());
        });
    }
    private void loadNewsAll() {
        binding.newsheader.removeAllTabs();
        mHomeViewModel.getNewslbData().observe(getViewLifecycleOwner(),s->{
            List<HomeNewsModel.DataDTO> dataBeans = s.getData();
            for (HomeNewsModel.DataDTO dataBean : dataBeans) {
                binding.newsheader.addTab(binding.newsheader.newTab().setText(dataBean.getName()).setTag(dataBean));
            }
            loadNewsListView(dataBeans.get(0).getId());// 加载第一个 下面的数据方法
            binding.newsheader.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    HomeNewsModel.DataDTO data = (HomeNewsModel.DataDTO) tab.getTag();

                    //重新加载
                    loadNewsListView(data.getId());
                }
                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
        });

    }
    private void loadNewsListView(int id){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HomeNewAllModel homeNewsModel = ServiceDaoImpl.getHomeNewsById(id);

                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Collections.sort(homeNewsModel.getRows(), new Comparator<HomeNewAllModel.RowsDTO>() {
                                    DateFormat f = new SimpleDateFormat("yyyy-mm-dd");
                                    @Override
                                    public int compare(HomeNewAllModel.RowsDTO o1, HomeNewAllModel.RowsDTO o2) {
                                        try {
                                            return f.parse(o1.getCreateTime()).compareTo(f.parse(o2.getCreateTime()));
                                        } catch (ParseException e) {
                                            throw new IllegalArgumentException(e);
                                        }
                                    }
                                });
                                HomeNewsInfoAdapter adapter = new HomeNewsInfoAdapter(homeNewsModel.getRows(),getActivity(),navController);
                                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
                                binding.newsinfo.setLayoutManager(manager);
                                binding.newsinfo.setAdapter(adapter);
                            }
                        });
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}