package com.qgj.juan_05.ui.fragment.services;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.FragmentDepartmentBinding;


public class DepartmentFragment extends Fragment {


    FragmentDepartmentBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDepartmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //渲染数据
        initview();
    }

    private void initview() {
        binding.fenglei.addTab(binding.fenglei.newTab().setText("普通"));
        binding.fenglei.addTab(binding.fenglei.newTab().setText("专家"));
        //专家数据
        setdatate(2);
        binding.fenglei.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选择完了
                
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setdatate(int i) {
        
    }
}