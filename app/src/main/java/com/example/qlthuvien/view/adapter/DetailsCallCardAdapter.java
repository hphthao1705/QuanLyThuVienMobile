package com.example.qlthuvien.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.qlthuvien.R;
import com.example.qlthuvien.data.model.ChiTietMuonTra;
import com.example.qlthuvien.data.model.ChiTietMuonTra_Full;
import com.example.qlthuvien.data.model.Item_Book;
import com.example.qlthuvien.data.model.MuonTra;
import com.example.qlthuvien.databinding.ItemDetailsOfBookLoanSlipsBinding;
import com.example.qlthuvien.databinding.ItemHistoryBinding;
import com.example.qlthuvien.view.fragments.DetailsOfBookLoanSlipsFragment;

import java.util.ArrayList;

public class DetailsCallCardAdapter extends RecyclerView.Adapter<DetailsCallCardAdapter.MyViewHolder>{
    ArrayList<ChiTietMuonTra_Full> list;
    Context context;

    public DetailsCallCardAdapter(ArrayList<ChiTietMuonTra_Full> list,Context _context)
    {
        this.list = list;
        context = _context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDetailsOfBookLoanSlipsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_details_of_book_loan_slips, parent, false);
        return new DetailsCallCardAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChiTietMuonTra_Full post = list.get(position);
        holder.binding.txtTensach.setText(post.getTentailieu());
//        holder.binding.txtTacgia.setText(post.);
        if(post.getTinhtrangtra() == 0)
        {
            holder.binding.txtNgaytra.setText("Chưa trả");
        }
        else
        {
            holder.binding.txtNgaytra.setText(post.getNgaytra());
        }
        Glide
                .with(context)
                .load(list.get(position).getHinh()).centerCrop().placeholder(R.drawable.avatar)
                .into(holder.binding.imgLoanBook);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemDetailsOfBookLoanSlipsBinding binding;

        public MyViewHolder(final ItemDetailsOfBookLoanSlipsBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
