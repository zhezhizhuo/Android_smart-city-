package com.qgj.juan_05.ui.fragment.services;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qgj.juan_05.adpater.OutpatienAdapter;
import com.qgj.juan_05.databinding.FragmentOutpatientBinding;
import com.qgj.juan_05.netwok.model.OutPationModel;


public class OutpatientFragment extends Fragment {

    private OutpatientViewModel mViewModel;
    FragmentOutpatientBinding binding;
    NavController navController;

    public static OutpatientFragment newInstance() {
        return new OutpatientFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentOutpatientBinding.inflate(getLayoutInflater());

        navController = NavHostFragment.findNavController(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OutpatientViewModel.class);
        // TODO: Use the ViewModel
        initview();
        //加载数据
        initdate();
    }

    private void initdate() {
        mViewModel.getOutPationModelMutableLiveData().observe(getViewLifecycleOwner(), new Observer<OutPationModel>() {
            @Override
            public void onChanged(OutPationModel outPationModel) {
                OutpatienAdapter outpatienAdapter = new OutpatienAdapter(outPationModel.getRows(),getContext(),navController);
                binding.body.setAdapter(outpatienAdapter);
            }
        });
    }

    private void initview() {
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(),3);
        binding.body.setLayoutManager(manager);
    }

}