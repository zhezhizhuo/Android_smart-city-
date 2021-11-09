 package com.qgj.juan_05.adpater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.qgj.juan_05.R;
import com.qgj.juan_05.databinding.ItemBuslineBinding;
import com.qgj.juan_05.databinding.ItemWearthercenterBinding;
import com.qgj.juan_05.netwok.model.BusLienModel;
import com.qgj.juan_05.netwok.model.BusLineInfoModel;
import com.qgj.juan_05.netwok.model.WeartherModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServicesBusLineAdapter extends RecyclerView.Adapter<ServicesBusLineAdapter.ViewHolder> {

    List<BusLienModel.RowsDTO> mList;
    Context mContext;
    ArrayAdapter adapter;

    ItemBuslineBinding   binding;
    public ServicesBusLineAdapter(List<BusLienModel.RowsDTO>   list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @NotNull
    @Override
    public ServicesBusLineAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemBuslineBinding binding = ItemBuslineBinding.inflate(LayoutInflater.from(mContext));
        return new ServicesBusLineAdapter.ViewHolder(binding);
    }
    int mInt;
    @Override
    public void onBindViewHolder(@NonNull @NotNull ServicesBusLineAdapter.ViewHolder holder, int position) {
        //获取数据元
        BusLienModel.RowsDTO rowsDTO = mList.get(position);
        binding = holder.binding;
        mInt = rowsDTO.getId();
        holder.binding.lienname.setText(rowsDTO.getName());
        holder.binding.first.setText(rowsDTO.getFirst());
        holder.binding.end.setText(rowsDTO.getEnd());
        holder.binding.startTime.setText(rowsDTO.getStartTime());
        holder.binding.endTime.setText(rowsDTO.getEndTime());
        holder.binding.mileage.setText(rowsDTO.getMileage()+"");
        holder.binding.prices.setText("  $"+rowsDTO.getPrice());
        //获取根据id所有的路径
        String[] strs = new String[]{ "12","12","12"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1,strs);
      //      holder.binding.liest.setAdapter(arrayAdapter);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                boolean flag;
                @Override
                public void onClick(View v) {
                    Log.e("TGA",rowsDTO.getId()+"");
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) binding.listItem.getLayoutParams();
                    if(flag){
                       layoutParams.height=0;
                       flag=false;
                   }else {
                       layoutParams.height=200;
                       flag=true;
                   }
                    binding.liest.setLayoutParams(layoutParams);
                }
            });
    }

    private void setData(int position) {


    }

    @Override
    public int getItemCount() {
        return  mList==null? 0:mList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        ItemBuslineBinding binding;
        public ViewHolder(@NonNull @NotNull ItemBuslineBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}