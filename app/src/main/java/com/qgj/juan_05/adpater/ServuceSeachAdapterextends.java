package com.qgj.juan_05.adpater;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.databinding.ItemServiceBinding;
import com.qgj.juan_05.databinding.ItemSeviceSeachBinding;
import com.qgj.juan_05.netwok.model.HomeServiceModel;
import com.qgj.juan_05.ui.activity.MainActivity;
import com.qgj.juan_05.util.PagerController;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ServuceSeachAdapterextends extends RecyclerView.Adapter<ServuceSeachAdapterextends.ViewHolder> {

        List<HomeServiceModel.RowsDTO> mList;
        Context mContext;
        NavController mController;
    AlertDialog mBuilder;
public ServuceSeachAdapterextends(List<HomeServiceModel.RowsDTO> list, Context context, NavController Controller, AlertDialog alertDialog) {
        mList = list;
        mContext = context;
        mController =Controller;
        mBuilder= alertDialog;
        }

@NonNull
@NotNull
@Override
public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    ItemSeviceSeachBinding binding = ItemSeviceSeachBinding.inflate(LayoutInflater.from(mContext));
        return new ViewHolder(binding);
        }

@Override
public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        //获取数据元
        HomeServiceModel.RowsDTO rowsDTO = mList.get(position);
        //服务类型
        holder.binding.serviceType.setText(rowsDTO.getServiceType());
        //服务名字
        holder.binding.servicename.setText(rowsDTO.getServiceName());
        //介绍
        holder.binding.js.setText(rowsDTO.getServiceDesc());
        //图片加载
        Glide.with(mContext)
        .load(MainActivity.serverURL + rowsDTO.getImgUrl())
        .fitCenter()
        .into(holder.binding.img);

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    PagerController.PagerController(mController, rowsDTO.getLink());
                mBuilder.dismiss();
          }
      });
     }

        @Override
        public int getItemCount() {
                return  mList==null? 0:mList.size();
                }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ItemSeviceSeachBinding binding;
        public ViewHolder(@NonNull @NotNull ItemSeviceSeachBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}

