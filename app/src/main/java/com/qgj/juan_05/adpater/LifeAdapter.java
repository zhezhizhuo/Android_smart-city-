package com.qgj.juan_05.adpater;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.ItemLifeBinding;
import com.qgj.juan_05.databinding.ItemListviewBinding;
import com.qgj.juan_05.databinding.ItemMovieBinding;
import com.qgj.juan_05.netwok.model.LifeModel;
import com.qgj.juan_05.netwok.model.MovieModel;
import com.qgj.juan_05.ui.activity.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LifeAdapter extends RecyclerView.Adapter<LifeAdapter.ViewHolder> {

    List<LifeModel.DataDTO> mList;
    Context mContext;
    NavController mController;

    public LifeAdapter(List<LifeModel.DataDTO> list, Context context, NavController controller) {
        mList = list;
        mContext = context;
        mController = controller;
    }


    @NonNull
    @NotNull
    @Override
    public LifeAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemLifeBinding binding = ItemLifeBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new LifeAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LifeAdapter.ViewHolder holder, int position) {
        //获取数据元
        LifeModel.DataDTO rowsDTO = mList.get(position);
        //图片
        Glide.with(mContext).load(MainActivity.serverURL+rowsDTO.getImgUrl()).fitCenter().into(holder.binding.img);
        //       holder.binding.image.setScaleType(ImageView.ScaleType.FIT_XY);
        //名字
        holder.binding.lifename.setText(rowsDTO.getLiveName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, rowsDTO.getLiveName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return  mList==null? 0:mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ItemLifeBinding binding;
        public ViewHolder(@NonNull @NotNull ItemLifeBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
