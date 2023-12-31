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
import com.example.qlthuvien.data.model.Item_Book;
import com.example.qlthuvien.databinding.ItemBookBinding;
import com.example.qlthuvien.databinding.ItemBookCategoryBinding;
import com.example.qlthuvien.view.activities.MainActivity;
import com.example.qlthuvien.view.fragments.HomeFragment;
import com.example.qlthuvien.view.fragments.NavigationBottomFragment;

import java.util.ArrayList;

public class BookCategoryAdapter extends RecyclerView.Adapter<BookCategoryAdapter.MyViewHolder>{
    ArrayList<Item_Book> list;

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    MainActivity activity;
    public void setContext(Context context) {
        this.context = context;
    }

    Context context;
    public BookCategoryAdapter(ArrayList<Item_Book> list)
    {
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookCategoryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_book_category, parent, false);
        return new BookCategoryAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.setBook(list.get(position));
        Glide
                .with(context)
                .load(list.get(position).getImage_book()).centerCrop().placeholder(R.drawable.avatar)
                .into(holder.binding.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationBottomFragment f = new NavigationBottomFragment();
                HomeFragment homeFragment = new HomeFragment(list.get(position).getId_tailieu());
                homeFragment.page=-3;
                homeFragment.setId_loai(list.get(position).getId_loai());
                f.setCurrent(homeFragment);
                f.setMenu_bottom(R.id.page_home);
                activity.replaceFragment(f);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemBookCategoryBinding binding;

        public MyViewHolder(final ItemBookCategoryBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
