package com.qgj.juan_05.ui.fragment.services;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.FragmentTrailerBinding;
import com.qgj.juan_05.netwok.model.HotMovieModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;
import com.qgj.juan_05.ui.activity.MainActivity;

import java.io.IOException;


public class TrailerFragment extends Fragment {
    private String TGA ="TrailerFragment";
    FragmentTrailerBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentTrailerBinding.inflate(getLayoutInflater());
       // Uri uri = Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        

        setVideo();
        //创建MediaController对象
        MediaController mediaController = new MediaController(getContext());
        //VideoView与MediaController建立关联
        binding.videoView.setMediaController(mediaController);
        //让VideoView获取焦点
        binding.videoView.requestFocus();
        binding.videoView.start();
    }

    private void setVideo() {
        //拿到Id然后获取预包
//        binding.videoView.setVideoURI(uri);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragm
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}