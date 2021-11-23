package com.qgj.juan_05.ui.fragment.services;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qgj.juan_05.R;
import com.qgj.juan_05.adpater.WaiMaiAdapter;
import com.qgj.juan_05.databinding.FragmentWaiMaiBinding;
import com.qgj.juan_05.netwok.model.WaiMai2Model;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import java.io.IOException;


public class WaiMaiFragment extends Fragment {


    private static final String MSG = "param";
    FragmentWaiMaiBinding binding;

    public WaiMaiFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static WaiMaiFragment newInstance(int id) {
        WaiMaiFragment fragment = new WaiMaiFragment();
        Bundle args = new Bundle();
        args.putInt(MSG, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWaiMaiBinding.inflate(getLayoutInflater(), container, false);
        binding.recycle.setLayoutManager(new GridLayoutManager(getActivity(),2));
        initDate();
        return binding.getRoot();
    }

    private void initDate() {
        if (getArguments()!=null){
            int id = getArguments().getInt(MSG);
            //查找数据
             new Thread(()->{
                         try {
                             WaiMai2Model waiMaiInfoAll = ServiceDaoImpl.getWaiMaiInfoAll(id);
                             //设置数据
                             WaiMaiAdapter adapter = new WaiMaiAdapter(waiMaiInfoAll.getRows(),getContext());
                             getActivity().runOnUiThread(new Runnable() {
                                 @Override
                                 public void run() {
                                     binding.recycle.setAdapter(adapter);
                                 }
                             });
                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                     }).start();
        }
    }
}