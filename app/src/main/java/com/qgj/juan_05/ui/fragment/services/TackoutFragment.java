package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.R;
import com.qgj.juan_05.adpater.WaiMaiViewPagerAdapter;
import com.qgj.juan_05.databinding.TackoutFragmentBinding;
import com.qgj.juan_05.netwok.model.AbnnerModel;
import com.qgj.juan_05.netwok.model.TackOutModel;
import com.qgj.juan_05.netwok.model.WaiMaiModel;
import com.qgj.juan_05.ui.activity.MainActivity;
import com.qgj.juan_05.ui.fragment.news.NewsFragment;
import com.qgj.juan_05.ui.view.BannerView;
import com.qgj.juan_05.util.PagerController;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.GET;

public class TackoutFragment extends Fragment {

    private TackoutViewModel mViewModel;
    TackoutFragmentBinding binding;

    public static TackoutFragment newInstance() {
        return new TackoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = TackoutFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TackoutViewModel.class);
        // TODO: Use the ViewModel
        initview();
        //轮播图
        mViewModel.getBanner().observe(getViewLifecycleOwner(), new Observer<AbnnerModel>() {
            @Override
            public void onChanged(AbnnerModel abnnerModel) {
                binding.bannner.setDate(new BannerView.InitAbbnerAdapter() {
                    @Override
                    public View getSubView(ViewGroup container, int position) {
                        ImageView img = new ImageView(getActivity());
                        //
                        int postion = position%abnnerModel.getTotal();
                        Glide.with(getActivity()).load(MainActivity.serverURL+abnnerModel.getRows().get(postion).getAdvImg()).into(img);
                        if (img.getParent() instanceof ViewGroup) {
                            ((ViewGroup) img.getParent()).removeView(img);
                        }
                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), abnnerModel.getRows().get(postion).getAdvTitle() ,Toast.LENGTH_SHORT).show();
                            }
                        });
                        return img;
                    }
                },abnnerModel.getTotal());
            }
        });
        //列表信息
        loadTabs();
    }

    private void initview() {
        binding.tabs.setTabTextColors(Color.BLACK, Color.RED);
    }

    private void loadTabs() {
        mViewModel.getWaiMiaiType().observe(getViewLifecycleOwner(), new Observer<WaiMaiModel>() {
            @Override
            public void onChanged(WaiMaiModel waiMaiModel) {
                List<WaiMaiModel.DataDTO> data = waiMaiModel.getData();
                List<Fragment> fragments = new ArrayList<>();
                for (WaiMaiModel.DataDTO datum : data) {
                    fragments.add(WaiMaiFragment.newInstance(datum.getId()));
                }
                WaiMaiViewPagerAdapter adapter = new WaiMaiViewPagerAdapter(getActivity().getSupportFragmentManager(), data,fragments);
                binding.waimaiinfo.setAdapter(adapter);
                binding.tabs.setupWithViewPager(binding.waimaiinfo);
            }
        });
    }

}