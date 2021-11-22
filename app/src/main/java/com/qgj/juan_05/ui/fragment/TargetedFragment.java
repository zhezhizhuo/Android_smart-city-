package com.qgj.juan_05.ui.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.TargetedFragmentBinding;

public class TargetedFragment extends Fragment {

    private TargetedViewModel mViewModel;


    TargetedFragmentBinding binding;

    public static TargetedFragment newInstance() {
        return new TargetedFragment();
    }
    //极其复杂
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //代码怎么写
        binding = TargetedFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TargetedViewModel.class);
        // TODO: Use the ViewModel
    }

}