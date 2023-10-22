package com.example.qlthuvien.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlthuvien.R;
import com.example.qlthuvien.data.model.Item_Loai;
import com.example.qlthuvien.databinding.ItemLoaiBinding;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.MyViewHolder>{
    ArrayList<Item_Loai> list;
    public TheLoaiAdapter(ArrayList<Item_Loai> list)
    {
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLoaiBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_loai, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.txtName.setText(list.get(position).getName());
        holder.binding.imgLoai.setImageResource(list.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemLoaiBinding binding;

        public MyViewHolder(final ItemLoaiBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
