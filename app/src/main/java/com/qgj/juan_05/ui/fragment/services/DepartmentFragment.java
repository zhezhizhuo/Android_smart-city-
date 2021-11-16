package com.qgj.juan_05.ui.fragment.services;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.qgj.juan_05.R;
import com.qgj.juan_05.adpater.DepartmentAdapter;
import com.qgj.juan_05.databinding.FragmentDepartmentBinding;
import com.qgj.juan_05.netwok.model.CardDepartmentModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;
import java.util.List;


public class DepartmentFragment extends Fragment {


    FragmentDepartmentBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDepartmentBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);
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
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.type.setLayoutManager(manager);
        //默认选择专家
        setDate(2);
        binding.fenglei.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            boolean flag;
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选择完了
                if (flag){
                    //专家
                    setDate(2);
                }else {
                     //普通
                    setDate(1);
                }
                flag = !flag;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setDate(int i) {
        new Thread(()->{
            try {
                List<CardDepartmentModel.RowsDTO> rows = ServiceDaoImpl.getDepartmentAll(i).getRows();
                DepartmentAdapter adapter = new DepartmentAdapter(rows,getActivity(),navController);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.type.setAdapter(adapter);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}