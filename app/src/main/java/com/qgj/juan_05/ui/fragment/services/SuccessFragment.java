package com.qgj.juan_05.ui.fragment.services;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.FragmentSuccessBinding;


public class SuccessFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentSuccessBinding inflate = FragmentSuccessBinding.inflate(getLayoutInflater());
        return inflater.inflate(R.layout.fragment_success, container, false);
    }
}