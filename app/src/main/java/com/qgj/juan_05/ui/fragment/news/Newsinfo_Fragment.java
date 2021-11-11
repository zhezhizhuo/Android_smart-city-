package com.qgj.juan_05.ui.fragment.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.adpater.HomeNewsInfoPlAdapter;
import com.qgj.juan_05.databinding.NewsinfoFragmentBinding;
import com.qgj.juan_05.netwok.model.NewsInfoModel;
import com.qgj.juan_05.netwok.model.NewsInfoplModel;
import com.qgj.juan_05.ui.activity.MainActivity;
import com.qgj.juan_05.util.TextUtil;


public class Newsinfo_Fragment extends Fragment {

    private NewsinfoViewModel mViewModel;
    NewsinfoFragmentBinding bind;
    NavController navController;

    //你要查询的那个新闻的id
    int search =0;
    public static Newsinfo_Fragment newInstance() {
        return new Newsinfo_Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        bind = NewsinfoFragmentBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);
        Bundle arguments = getArguments();
        search = arguments.getInt("getNewsInfoById");
        Toast.makeText(getActivity(), ""+search, Toast.LENGTH_SHORT).show();
        return bind.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NewsinfoViewModel.class);
        // TODO: Use the ViewModel

        initdata();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        bind.pl.setLayoutManager(manager);
        //评论
        initpl();
    }

    private void initpl() {

            mViewModel.getMpldata(search).observe(getViewLifecycleOwner(), new Observer<NewsInfoplModel>() {
                @Override
                public void onChanged(NewsInfoplModel newsInfoplModel) {
                    HomeNewsInfoPlAdapter adapter =new HomeNewsInfoPlAdapter(newsInfoplModel.getRows(),getActivity());
                    bind.pl.setAdapter(adapter);
                }
            });
    }

    private void initdata() {

        if (search<=0){
        return;
        }
        mViewModel.getNewsInfoModelMutableLiveData(search).observe(getViewLifecycleOwner(), new Observer<NewsInfoModel>() {


            @Override
            public void onChanged(NewsInfoModel newsInfoModel) {
                //设置数据
                NewsInfoModel.DataDTO data = newsInfoModel.getData();
                //标题
                bind.title.setText(data.getTitle());
                //图片
                Glide.with(getActivity()).load(MainActivity.serverURL+data.getCover()).into(bind.cover);
                //内容
                bind.content.setText(TextUtil.FormatString(data.getContent()));
                //副标题
                bind.subTitle.setText(data.getSubTitle());
                //幅图片

            }
        });
    }

}