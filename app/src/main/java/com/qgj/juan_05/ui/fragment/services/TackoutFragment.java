package com.qgj.juan_05.ui.fragment.services;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
import com.qgj.juan_05.databinding.TackoutFragmentBinding;
import com.qgj.juan_05.netwok.model.AbnnerModel;
import com.qgj.juan_05.netwok.model.TackOutModel;
import com.qgj.juan_05.ui.activity.MainActivity;
import com.qgj.juan_05.ui.view.BannerView;
import com.qgj.juan_05.util.PagerController;

import org.jetbrains.annotations.NotNull;

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
        //加载图片
        mViewModel.getOut().observe(getViewLifecycleOwner(), new Observer<TackOutModel>() {
            @Override
            public void onChanged(TackOutModel tackOutModel) {
                String imgUrl = tackOutModel.getRows().get(0).getImgUrl();
                Glide.with(getActivity()).load(MainActivity.serverURL+imgUrl).into(binding.demo);
            }
        });
    }

}