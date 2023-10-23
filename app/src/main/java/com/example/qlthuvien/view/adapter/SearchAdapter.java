package com.example.qlthuvien.view.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.example.qlthuvien.databinding.ItemLoaiBinding;
import com.example.qlthuvien.databinding.ItemTimkiemBinding;

public class SearchAdapter {
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemTimkiemBinding binding;

        public MyViewHolder(final ItemTimkiemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
