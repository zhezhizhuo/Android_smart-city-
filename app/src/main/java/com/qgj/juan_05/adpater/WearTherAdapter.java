package com.qgj.juan_05.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.qgj.juan_05.databinding.ItemWearthercenterBinding;
import com.qgj.juan_05.netwok.model.WeartherModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WearTherAdapter  extends RecyclerView.Adapter<WearTherAdapter.ViewHolder> {

    List<WeartherModel.DataDTO.TodayDTO.HoursDTO> mList;
    Context mContext;

    public WearTherAdapter(List<WeartherModel.DataDTO.TodayDTO.HoursDTO>  list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemWearthercenterBinding binding = ItemWearthercenterBinding.inflate(LayoutInflater.from(mContext));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        //获取数据元
       WeartherModel.DataDTO.TodayDTO.HoursDTO data = mList.get(position);
        holder.binding.time.setText(data.getHour());
        holder.binding.temperature.setText(data.getTemperature()+"℃");
        holder.binding.weather.setText(data.getWeather());
    }

    @Override
    public int getItemCount() {
        return  mList==null? 0:mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ItemWearthercenterBinding binding;
        public ViewHolder(@NonNull @NotNull ItemWearthercenterBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}