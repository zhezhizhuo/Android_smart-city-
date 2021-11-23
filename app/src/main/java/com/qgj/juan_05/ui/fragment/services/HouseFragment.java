package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qgj.juan_05.R;
import com.qgj.juan_05.adpater.HouseAdapterextends;
import com.qgj.juan_05.databinding.HouseFragmentBinding;
import com.qgj.juan_05.netwok.model.HouseModel;

import java.util.List;

public class HouseFragment extends Fragment {
    HouseFragmentBinding binding;
    private HouseViewModel mViewModel;

    public static HouseFragment newInstance() {
        return new HouseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = HouseFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HouseViewModel.class);
        // TODO: Use the ViewModel
        initView();
        initDate();
    }

    private void initDate() {
        mViewModel.getMhoude().observe(getViewLifecycleOwner(), new Observer<HouseModel>() {
            @Override
            public void onChanged(HouseModel houseModel) {
                List<HouseModel.RowsDTO> rows = houseModel.getRows();
                HouseAdapterextends adapterextends = new HouseAdapterextends(rows,getContext());
                binding.houses.setAdapter(adapterextends);
            }
        });
    }

    private void initView() {
        binding.houses.setLayoutManager(new GridLayoutManager(getContext(),2));
    }

}