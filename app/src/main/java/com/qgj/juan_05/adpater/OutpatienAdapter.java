package com.qgj.juan_05.adpater;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.ItemOutpationBinding;
import com.qgj.juan_05.netwok.model.OutPationModel;
import com.qgj.juan_05.ui.activity.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OutpatienAdapter extends RecyclerView.Adapter<OutpatienAdapter.ViewHolder> {

    List<OutPationModel.RowsDTO> mList;
    Context mContext;
    NavController mController;

    public OutpatienAdapter(List<OutPationModel.RowsDTO> list, Context context, NavController controller) {
        mList = list;
        mContext = context;
        mController = controller;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemOutpationBinding binding = ItemOutpationBinding.inflate(LayoutInflater.from(mContext));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        //获取数据元
        OutPationModel.RowsDTO rowsDTO = mList.get(position);
        holder.binding.lear.setRating(Integer.valueOf(rowsDTO.getLevel()));
        //图片
        Glide.with(mContext).load(MainActivity.serverURL+rowsDTO.getImgUrl()).fitCenter().into(holder.binding.image);
 //       holder.binding.image.setScaleType(ImageView.ScaleType.FIT_XY);
        //名字
        holder.binding.title.setText(rowsDTO.getHospitalName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id",rowsDTO.getId());
                bundle.putString("count",rowsDTO.getBrief());
                mController.navigate(R.id.hospitalFragment,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return  mList==null? 0:mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ItemOutpationBinding binding;
        public ViewHolder(@NonNull @NotNull ItemOutpationBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
