package com.qgj.juan_05.ui.fragment.services;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.qgj.juan_05.R;
import com.qgj.juan_05.adpater.ServicesActivityAdapter;
import com.qgj.juan_05.databinding.FragmentActivityBinding;
import com.qgj.juan_05.netwok.model.ActivityBannerModel;
import com.qgj.juan_05.netwok.model.ActivityTypeModel;
import com.qgj.juan_05.netwok.model.ActivyInfoModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;
import com.qgj.juan_05.ui.activity.MainActivity;
import com.qgj.juan_05.ui.view.BannerView;
import com.qgj.juan_05.util.PagerController;

import java.io.IOException;
import java.util.List;

public class ActivityFragment extends Fragment {

    FragmentActivityBinding binding;

    ActivityViewModel mViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentActivityBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ActivityViewModel.class);
        initview();
        //加载轮播图
        initbanner();
        //设置活动分类列表
        initactivtyType();
    }

    private void initactivtyType() {
            mViewModel.getactivyType().observe(getViewLifecycleOwner(), new Observer<ActivityTypeModel>() {
                @Override
                public void onChanged(ActivityTypeModel activityTypeModel) {
                    List<ActivityTypeModel.DataDTO> data = activityTypeModel.getData();
                    for (ActivityTypeModel.DataDTO datum : data) {
                        binding.activiType.addTab(binding.activiType.newTab().setText(datum.getName()).setTag(datum));
                    }
                    //加载第一个列表
                    loadTypeinfo(data.get(0).getId());
                    //添加事件
                    binding.activiType.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            ActivityTypeModel.DataDTO datum  = (ActivityTypeModel.DataDTO) tab.getTag();
                            int id = datum.getId();
                            loadTypeinfo(id);
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });
                }
            });
    }

    private void loadTypeinfo(int id) {
        new Thread(()->{
            try {
                ActivyInfoModel activityTypeInfoById = ServiceDaoImpl.getActivityTypeInfoById(id);
                List<ActivyInfoModel.RowsDTO> rows = activityTypeInfoById.getRows();
                ServicesActivityAdapter adapter = new ServicesActivityAdapter(rows,getActivity(),navController);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.activyInfo.setAdapter(adapter);
                    }
                });
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }).start();
    }

    private void initbanner() {
        mViewModel.getMactivybanner().observe(getViewLifecycleOwner(), new Observer<ActivityBannerModel>() {
            @Override
            public void onChanged(ActivityBannerModel activityBannerModel) {
                //设置数据
                binding.banner.setDate(new BannerView.InitAbbnerAdapter() {
                    @Override
                    public View getSubView(ViewGroup container, int position) {
                        ImageView img =  new ImageView(getContext());
                        //算出真实的postion
                        int realpositton = position%activityBannerModel.getTotal();
                        //
                        Glide.with(getContext()).load(MainActivity.serverURL + activityBannerModel.getRows().get(realpositton).getAdvImg()).into(img);
                        if(img.getParent() instanceof ViewGroup){
                            ((ViewGroup)img.getParent()).removeView(img);
                        }
                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), activityBannerModel.getRows().get(realpositton).getServModule(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        img.setScaleType(ImageView.ScaleType.FIT_XY);
                        return img;
                    }
                },activityBannerModel.getTotal());
            }
        });
    }

    private void initview() {
        //设置布局管理器
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        binding.activyInfo.setLayoutManager(manager);
        binding.activiType.setTabTextColors(Color.GRAY,Color.RED);
    }
}