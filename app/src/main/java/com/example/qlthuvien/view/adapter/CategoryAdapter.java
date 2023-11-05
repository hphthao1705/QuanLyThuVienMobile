package com.example.qlthuvien.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.qlthuvien.R;
import com.example.qlthuvien.data.model.Item_Loai;
import com.example.qlthuvien.databinding.ItemLoaiBinding;
import com.example.qlthuvien.view.activities.MainActivity;
import com.example.qlthuvien.view.fragments.CategoryFragment;
import com.example.qlthuvien.view.fragments.HomeFragment;
import com.example.qlthuvien.view.fragments.InformationOfUserFragment;
import com.example.qlthuvien.view.fragments.NavigationBottomFragment;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    ArrayList<Item_Loai> list;
    Context context;

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    MainActivity mainActivity;
    public void setContext(Context context) {
        this.context = context;
    }

    public CategoryAdapter(ArrayList<Item_Loai> list )
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
        Glide
                .with(context)
                .load(list.get(position).getIcon()).centerCrop().placeholder(R.drawable.avatar)
                .into(holder.binding.imgLoai);
        holder.binding.btnTheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationBottomFragment f = new NavigationBottomFragment();
                HomeFragment homeFragment = new HomeFragment(-1);
                homeFragment.setId_loai(list.get(position).getId_loai());
                f.setCurrent(homeFragment);
                f.setMenu_bottom(R.id.page_home);
                mainActivity.replaceFragment(f);
            }
        });
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
