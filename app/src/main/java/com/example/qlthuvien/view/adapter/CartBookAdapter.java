package com.example.qlthuvien.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qlthuvien.dto.DtoFavourite;
import com.example.qlthuvien.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.qlthuvien.view.activities.DetailsBookActivity;

public class CartBookAdapter extends RecyclerView.Adapter<CartBookAdapter.CartBookAdapterViewHolder>  {
    List<DtoFavourite> list;
    Context context;
    public  CartBookAdapter(Context t ){
        context = t;
    }

    public void SetData(List<DtoFavourite> l)
    {
        list= l;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CartBookAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_book,parent,false);
        return new CartBookAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartBookAdapterViewHolder holder, int position) {
        DtoFavourite dtoFavourite = list.get(position);
        if(dtoFavourite == null)
        {
            return;
        }
        holder.nameOfBook.setText(dtoFavourite.TenSach);
        holder.imgBook.setImageResource(R.drawable.conan);
        holder.authorOfBook.setText(dtoFavourite.TacGia);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        return 0;
    }

    public class CartBookAdapterViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgBook;
        private TextView nameOfBook;
        private TextView authorOfBook;
        private Button btnRent;

        public CartBookAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            imgBook = itemView.findViewById(R.id.img_cart_book);
            nameOfBook = itemView.findViewById(R.id.txt_tensach_cart);
            authorOfBook = itemView.findViewById(R.id.txt_tacgia_cart);
        }
    }
}
