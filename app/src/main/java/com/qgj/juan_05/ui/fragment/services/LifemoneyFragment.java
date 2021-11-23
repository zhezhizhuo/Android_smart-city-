package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.R;
import com.qgj.juan_05.adpater.LifeAdapter;
import com.qgj.juan_05.databinding.LifemoneyFragmentBinding;
import com.qgj.juan_05.netwok.model.AbnnerModel;
import com.qgj.juan_05.netwok.model.LifeModel;
import com.qgj.juan_05.ui.activity.MainActivity;
import com.qgj.juan_05.ui.view.BannerView;

public class LifemoneyFragment extends Fragment {

    private LifemoneyViewModel mViewModel;
    LifemoneyFragmentBinding binding;

    NavController controller;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = LifemoneyFragmentBinding.inflate(getLayoutInflater());
        controller = NavHostFragment.findNavController(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LifemoneyViewModel.class);
        // TODO: Use the ViewModel
            initView();
            initDate();
    }

    private void initDate() {
        //图片
        mViewModel.getLifemodel().observe(getViewLifecycleOwner(), new Observer<LifeModel>() {
            @Override
            public void onChanged(LifeModel lifeModel) {
                LifeAdapter lifeAdapter = new LifeAdapter(lifeModel.getData(),getContext(),controller);
                binding.life.setAdapter(lifeAdapter);
            }
        });
        //轮播图
        mViewModel.getLunbotu().observe(getViewLifecycleOwner(), new Observer<AbnnerModel>() {
            @Override
            public void onChanged(AbnnerModel abnnerModel) {
                binding.banner.setDate(new BannerView.InitAbbnerAdapter() {
                    @Override
                    public View getSubView(ViewGroup container, int position) {
                        ImageView img = new ImageView(getContext());
                        //算出真实的索引
                        int realpostion= position%abnnerModel.getTotal();
                        Glide.with(getContext()).load(MainActivity.serverURL+abnnerModel.getRows().get(realpostion).getAdvImg()).fitCenter().into(img);
                        img.setScaleType(ImageView.ScaleType.FIT_XY);
                        if(img.getParent() instanceof ViewGroup){
                            ((ViewGroup)img.getParent()).removeView(img);
                        }
                        //
                        return img;
                    }
                },abnnerModel.getTotal());
            }
        });
    }
    //页面操作
    private void initView() {
        binding.life.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    }

}