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
import com.qgj.juan_05.netwok.model.HotMovieModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;
import com.qgj.juan_05.ui.activity.MainActivity;

import java.io.IOException;


public class TrailerFragment extends Fragment {
    VideoView videoView;
    private String TGA ="TrailerFragment";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trailer, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        videoView = getView().findViewById(R.id.videoView);

        new Thread(()->{
            try {
                HotMovieModel movieHotAll = ServiceDaoImpl.getMovieHotAll();
                //加载指定的视频文件
                String video = movieHotAll.getRows().get(0).getVideo();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TGA,MainActivity.serverURL +video);
//                        Uri uri = Uri.parse(MainActivity.serverURL +video);
                        Uri uri = Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
                        videoView.setVideoURI(uri);
                        //创建MediaController对象
                        MediaController mediaController = new MediaController(getContext());
                        //VideoView与MediaController建立关联
                        videoView.setMediaController(mediaController);
                        //让VideoView获取焦点
                        videoView.requestFocus();
                        videoView.start();
                    }
                });
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }).start();
    }
}