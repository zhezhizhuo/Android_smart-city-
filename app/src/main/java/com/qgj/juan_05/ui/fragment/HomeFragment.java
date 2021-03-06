package com.qgj.juan_05.ui.fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.qgj.juan_05.R;
import com.qgj.juan_05.adpater.HomeNewsInfoAdapter;
import com.qgj.juan_05.adpater.HomeServiceAdapter;
import com.qgj.juan_05.databinding.HomeFragmentBinding;
import com.qgj.juan_05.netwok.model.AbnnerModel;
import com.qgj.juan_05.netwok.model.HomeNewAllModel;
import com.qgj.juan_05.netwok.model.HomeNewsModel;
import com.qgj.juan_05.netwok.model.HomeServiceModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;
import com.qgj.juan_05.ui.activity.MainActivity;
import com.qgj.juan_05.ui.view.BannerView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;

    HomeFragmentBinding binding;
    NavController navController;
    private String TAG ="HomeFragment";
    private boolean temp = true;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         binding = HomeFragmentBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);
        return binding.getRoot();
    }
    //???????????????????????????
    @Override
    public void onStart() {
        super.onStart();
        binding.search.setText("");
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
        //?????????
        //  //????????????????????????????????????
                RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 5);
                binding.services.setLayoutManager(manager);
        //??????tobler???????????????
       // binding.newsheader.setTabTextColors(Color.WHITE,Color.YELLOW);
        binding.search.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String search = v.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("search",search);
                    HomeFragment.this.setArguments(bundle);
                  //  navController.navigate(R.id.searchFragment,bundle);
                    return true;
                }
                return false;
            }
        });
        //???????????????
        loadBanner();
        //??????????????????
        loadServiceAll();
        //???????????????
      //  loadNewsAll();
        //????????????
        searchNews();
        loadNewsListView(17);
    }

    private void searchNews() {
        //????????????

    }

    /**
     * ???????????????
     */
    private void loadBanner() {
        mViewModel.getMbnnerDate().observe(getViewLifecycleOwner(), new Observer<AbnnerModel>() {

            @Override
            public void onChanged(AbnnerModel abnnerModel) {
                binding.banner.setDate(new BannerView.InitAbbnerAdapter() {
                    @Override
                    public View getSubView(ViewGroup container, int position) {
                        ImageView img = new ImageView(getContext());
                        //?????????????????????
                        int realpostion= position%abnnerModel.getTotal();
                        Glide.with(getContext()).load(MainActivity.serverURL+abnnerModel.getRows().get(realpostion).getAdvImg()).fitCenter().into(img);
                        img.setScaleType(ImageView.ScaleType.FIT_XY);
                        if(img.getParent() instanceof ViewGroup){
                            ((ViewGroup)img.getParent()).removeView(img);
                        }
                        //
                        return img;
                    }
                },abnnerModel.getTotal());
            }
        });

    }

    /**
     * ??????????????????
     */
//    private void loadNewsAll() {
//        mViewModel.getNewslbData().observe(getViewLifecycleOwner(),s->{
//            List<HomeNewsModel.DataDTO> dataBeans = s.getData();
//            for (HomeNewsModel.DataDTO dataBean : dataBeans) {
//                binding.newsheader.addTab(binding.newsheader.newTab().setText(dataBean.getName()).setTag(dataBean));
//            }
//            loadNewsListView(dataBeans.get(0).getId());// ??????????????? ?????????????????????
//            binding.newsheader.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//                @Override
//                public void onTabSelected(TabLayout.Tab tab) {//??????????????????????????????????????????
//                    HomeNewsModel.DataDTO data = (HomeNewsModel.DataDTO) tab.getTag();
//                    //????????????
//                    loadNewsListView(data.getId());
//                }
//                @Override
//                public void onTabUnselected(TabLayout.Tab tab) {
//                }
//
//                @Override
//                public void onTabReselected(TabLayout.Tab tab) {
//                }
//            });
//        });
//    }

    private void loadNewsListView(int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HomeNewAllModel homeNewsModel = ServiceDaoImpl.getHomeNewsById(id);

                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Collections.sort(homeNewsModel.getRows(), new Comparator<HomeNewAllModel.RowsDTO>() {
                                    DateFormat f = new SimpleDateFormat("yyyy-mm-dd");
                                    @Override
                                    public int compare(HomeNewAllModel.RowsDTO o1, HomeNewAllModel.RowsDTO o2) {
                                        try {
                                            return f.parse(o1.getCreateTime()).compareTo(f.parse(o2.getCreateTime()));
                                        } catch (ParseException e) {
                                            throw new IllegalArgumentException(e);
                                        }
                                    }
                                });
                                HomeNewsInfoAdapter adapter = new HomeNewsInfoAdapter(homeNewsModel.getRows(),getActivity(),navController);
                                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
                                binding.newsinfo.setLayoutManager(manager);
                                binding.newsinfo.setAdapter(adapter);
                            }
                        });
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * ??????????????????
     */
    private void loadServiceAll() {

        mViewModel.getMservicesData().observe(getViewLifecycleOwner(), new Observer<HomeServiceModel>() {
            @Override
            public void onChanged(HomeServiceModel homeServiceModel) {
                List<HomeServiceModel.RowsDTO> rows = homeServiceModel.getRows();
                //??????????????????
                Collections.sort(rows, new Comparator<HomeServiceModel.RowsDTO>() {
                    @Override
                    public int compare(HomeServiceModel.RowsDTO rowsBean, HomeServiceModel.RowsDTO t1) {
                        return rowsBean.getSort() - t1.getSort();
                    }
                });
              //   ???????????????
                while (rows.size()>10) {
                    rows.remove(rows.size() - 1);
                }
                rows.get(rows.size()-1).setServiceName("????????????");
                HomeServiceAdapter serviceAdapter = new HomeServiceAdapter(rows,getActivity(),navController);
                binding.services.setAdapter(serviceAdapter);
            }
        });

    }

}