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
import com.qgj.juan_05.databinding.ItemMovieBinding;
import com.qgj.juan_05.databinding.ItemOutpationBinding;
import com.qgj.juan_05.netwok.model.HotMovieModel;
import com.qgj.juan_05.netwok.model.MovieModel;
import com.qgj.juan_05.netwok.model.OutPationModel;
import com.qgj.juan_05.ui.activity.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    List<MovieModel.RowsDTO> mList;
    Context mContext;
    NavController mController;

    public MovieAdapter(List<MovieModel.RowsDTO> list, Context context, NavController controller) {
        mList = list;
        mContext = context;
        mController = controller;
    }


    @NonNull
    @NotNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemMovieBinding binding = ItemMovieBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new MovieAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MovieAdapter.ViewHolder holder, int position) {
        //获取数据元
        if (position==0){
            return;
        }
        MovieModel.RowsDTO rowsDTO = mList.get(position);

        //图片
        Glide.with(mContext).load(MainActivity.serverURL+rowsDTO.getCover()).fitCenter().into(holder.binding.image);
        //       holder.binding.image.setScaleType(ImageView.ScaleType.FIT_XY);
        //名字
        holder.binding.titile.setText(rowsDTO.getName());
        holder.binding.bth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id",rowsDTO.getId());
//                bundle.putString("count",rowsDTO.getBrief());
                mController.navigate(R.id.movieInfoFragment,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return  mList==null? 0:mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ItemMovieBinding binding;
        public ViewHolder(@NonNull @NotNull ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}