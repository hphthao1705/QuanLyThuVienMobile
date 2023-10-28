package com.example.qlthuvien.view.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlthuvien.R;
import com.example.qlthuvien.data.model.Item_Book;
import com.example.qlthuvien.databinding.ItemBookBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.net.URL;

public class BookInTopAdapter extends RecyclerView.Adapter<BookInTopAdapter.MyViewHolder>{
    ArrayList<Item_Book> list;
    public BookInTopAdapter(ArrayList<Item_Book> list)
    {
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_book, parent, false);
        return new BookInTopAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //fix loi hinh
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        holder.binding.setBook(list.get(position));
        holder.binding.imageView.setImageBitmap(getBitmapFromURL(list.get(position).getImage_book()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemBookBinding binding;

        public MyViewHolder(final ItemBookBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
    private Bitmap getBitmapFromURL(String src){
        try {
            return BitmapFactory.decodeStream(new URL(src).openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception", e.toString());
            return null;
        }
    }
}
