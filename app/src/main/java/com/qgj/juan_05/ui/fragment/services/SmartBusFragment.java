package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qgj.juan_05.R;
import com.qgj.juan_05.adpater.ServicesBusLineAdapter;
import com.qgj.juan_05.databinding.FragmentSmartBusBinding;
import com.qgj.juan_05.netwok.model.BusLienModel;

public class SmartBusFragment extends Fragment {

    private SmartBusViewModel mViewModel;
    FragmentSmartBusBinding binding;

    public static SmartBusFragment newInstance() {
        return new SmartBusFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSmartBusBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SmartBusViewModel.class);
        // TODO: Use the ViewModel
        initview();
        initData();
    }

    private void initData() {
        mViewModel.getMbuslie().observe(getViewLifecycleOwner(), new Observer<BusLienModel>() {
            @Override
            public void onChanged(BusLienModel busLienModel) {
                ServicesBusLineAdapter busLineAdapter = new ServicesBusLineAdapter(busLienModel.getRows(),getContext());
                binding.liens.setAdapter(busLineAdapter);
            }
        });
    }

    private void initview() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.liens.setLayoutManager(manager);
    }

}