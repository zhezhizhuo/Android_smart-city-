package com.qgj.juan_05.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.qgj.juan_05.databinding.ItemWeartherbodyBinding;
import com.qgj.juan_05.netwok.model.WeartherModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WearTherBodyAdapter extends RecyclerView.Adapter<WearTherBodyAdapter.ViewHolder> {

    List<WeartherModel.DataDTO.WeatherListDTO> mList;
    Context mContext;

    public WearTherBodyAdapter( List<WeartherModel.DataDTO.WeatherListDTO>  list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemWeartherbodyBinding binding = ItemWeartherbodyBinding.inflate(LayoutInflater.from(mContext));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        //获取数据元
        WeartherModel.DataDTO.WeatherListDTO weatherListDTO = mList.get(position);
        holder.binding.air.setText(weatherListDTO.getAir());
        holder.binding.day.setText(weatherListDTO.getLabel());
        holder.binding.weather.setText(weatherListDTO.getWeather());
        holder.binding.temperature.setText(weatherListDTO.getTemperature());
        holder.binding.daytime.setText(weatherListDTO.getDay());
    }

    @Override
    public int getItemCount() {
        return  mList==null? 0:mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ItemWeartherbodyBinding binding;
        public ViewHolder(@NonNull @NotNull ItemWeartherbodyBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}