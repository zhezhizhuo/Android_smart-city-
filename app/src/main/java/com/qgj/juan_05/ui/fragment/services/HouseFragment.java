package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.HouseFragmentBinding;

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
    }

}