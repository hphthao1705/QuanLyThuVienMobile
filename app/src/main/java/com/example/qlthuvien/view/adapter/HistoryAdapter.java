package com.example.qlthuvien.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlthuvien.R;
import com.example.qlthuvien.data.model.MuonTra;
import com.example.qlthuvien.databinding.ItemHistoryBinding;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder>{
    ArrayList<MuonTra> list;
    public HistoryAdapter(ArrayList<MuonTra> list)
    {
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHistoryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_history, parent, false);
        return new HistoryAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MuonTra post = list.get(position);

        if(post.getId_muon() < 10)
        {
            holder.binding.txtMamuon.setText("PM00" + post.getId_muon());
        }
        else {
            holder.binding.txtMamuon.setText("PM0" + post.getId_muon());
        }
        holder.binding.txtNgaymuon.setText(post.getNgaymuon());
        if(post.getTintrangmuon() == 2)
        {
            holder.binding.txtNgaytra.setText("Đã trả");
        }
        else if(post.getTintrangmuon() == 1)
        {
            holder.binding.txtNgaytra.setText("Chưa trả");
        }
        else
        {
            holder.binding.txtNgaytra.setText("Đang xử lý");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemHistoryBinding binding;

        public MyViewHolder(final ItemHistoryBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
