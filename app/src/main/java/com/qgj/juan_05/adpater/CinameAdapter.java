package com.qgj.juan_05.adpater;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.ItemCinemaBinding;
import com.qgj.juan_05.databinding.ItemHomenewsBinding;
import com.qgj.juan_05.netwok.model.CinameModel;
import com.qgj.juan_05.netwok.model.HomeNewAllModel;
import com.qgj.juan_05.ui.activity.MainActivity;
import com.qgj.juan_05.util.TextUtil;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CinameAdapter extends RecyclerView.Adapter<CinameAdapter.ViewHolder> {

    List<CinameModel.RowsDTO> mList;
    FragmentActivity mContext;

    NavController mController;
    public CinameAdapter(List<CinameModel.RowsDTO> list, FragmentActivity context, NavController navController) {
        mList = list;
        mContext = context;
        mController = navController;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemCinemaBinding binding = ItemCinemaBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        //获取数据元
        CinameModel.RowsDTO dataDTO = mList.get(position);
        //名字
        holder.binding.name.setText(dataDTO.getName());
        //距离
        holder.binding.distance.setText(dataDTO.getDistance());
        //地区
        holder.binding.address.setText(dataDTO.getAddress());
        //地址
        holder.binding.city.setText(dataDTO.getProvince()+" "+dataDTO.getCity()+" "+dataDTO.getArea());
        //加载图片
        Glide.with(mContext)
                .load("https://up.enterdesk.com/edpic/6b/06/c4/6b06c4719374d57d32617249162cc88f.jpg")
                .fitCenter()
                .into(holder.binding.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("getNewsInfoById",dataDTO.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemCinemaBinding binding;
        public ViewHolder(@NonNull @NotNull ItemCinemaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}