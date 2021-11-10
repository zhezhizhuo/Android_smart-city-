package com.qgj.juan_05.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qgj.juan_05.databinding.ItemListviewBinding;
import com.qgj.juan_05.netwok.model.BusLienModel;
import com.qgj.juan_05.netwok.model.BusLineInfoModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BusListview extends RecyclerView.Adapter<BusListview.ViewHolder> {

    List<BusLineInfoModel.RowsDTO> mLineInfoModels;
    private Context mcontext;

    public BusListview(List<BusLineInfoModel.RowsDTO> lineInfoModels, Context mcontext) {
        mLineInfoModels = lineInfoModels;
        this.mcontext = mcontext;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
         public   ItemListviewBinding binding;

        public ViewHolder(@NonNull @NotNull ItemListviewBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }


    @NonNull
    @NotNull
    @Override
    public BusListview.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemListviewBinding binding = ItemListviewBinding.inflate(LayoutInflater.from(mcontext), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BusListview.ViewHolder holder, int position) {
        BusLineInfoModel.RowsDTO rowsDTO = mLineInfoModels.get(position);
        if (position==0){
            holder.binding.text.setText("起始站:  "+rowsDTO.getName());
        }else {
            holder.binding.text.setText("            "+rowsDTO.getName());
        }
        if (position==mLineInfoModels.size()-1)
            holder.binding.text.setText("终点站:  "+rowsDTO.getName());

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mLineInfoModels.size();
    }

}
