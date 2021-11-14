package com.qgj.juan_05.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.ItemServiceBinding;
import com.qgj.juan_05.netwok.model.HomeServiceModel;
import com.qgj.juan_05.ui.activity.MainActivity;
import com.qgj.juan_05.util.PagerController;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeServiceAdapter extends RecyclerView.Adapter<HomeServiceAdapter.ViewHolder> {

        List<HomeServiceModel.RowsDTO> mList;
        Context mContext;
        NavController mController;
    public HomeServiceAdapter(List<HomeServiceModel.RowsDTO> list, Context context, NavController Controller) {
        mList = list;
        mContext = context;
        mController =Controller;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemServiceBinding binding = ItemServiceBinding.inflate(LayoutInflater.from(mContext));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        //获取数据元
        HomeServiceModel.RowsDTO rowsDTO = mList.get(position);
        holder.binding.iServiceTitle.setText(rowsDTO.getServiceName());
        if (position==9){
            rowsDTO.setLink("more");
            holder.binding.iServiceImage.setImageDrawable(mContext.getDrawable(R.drawable.service_type_more));
        }else {
            Glide.with(mContext)
                    .load(MainActivity.serverURL + rowsDTO.getImgUrl())
                    .fitCenter()
                    .into(holder.binding.iServiceImage);
        }
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             PagerController.PagerController(mController, rowsDTO.getLink());
            }
        });
    }

    @Override
    public int getItemCount() {
        return  mList==null? 0:mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ItemServiceBinding binding;
        public ViewHolder(@NonNull @NotNull ItemServiceBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
