package com.example.qlthuvien.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlthuvien.R;
import com.example.qlthuvien.databinding.ItemLoaiBinding;
import com.example.qlthuvien.databinding.ItemSlideBinding;

import java.util.ArrayList;

public class ImageSlideAdapter extends RecyclerView.Adapter<ImageSlideAdapter.MyViewHolder>{
    ArrayList<Integer> list;
    public ImageSlideAdapter(ArrayList<Integer> list)
    {
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSlideBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_slide, parent, false);
        return new ImageSlideAdapter.MyViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.imgBookHome1.setImageResource(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemSlideBinding binding;

        public MyViewHolder(final ItemSlideBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
