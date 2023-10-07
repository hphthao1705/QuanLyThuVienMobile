package com.example.qlthuvien.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlthuvien.Dtos.DtoFavourite;
import com.example.qlthuvien.R;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>  {
    List<DtoFavourite> list;
    Context context;
    public  FavouriteAdapter(Context t ){
        context = t;
    }

    public void SetData(List<DtoFavourite> l)
    {
        list= l;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favourite_book,parent,false);
        return new FavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
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
        holder.btnRent.setOnClickListener(new View.OnClickListener() {
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

    public class FavouriteViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgBook;
        private TextView nameOfBook;
        private TextView authorOfBook;
        private Button btnRent;

        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            imgBook = itemView.findViewById(R.id.img_favourite_book);
            nameOfBook = itemView.findViewById(R.id.txt_tensach_favourite);
            authorOfBook = itemView.findViewById(R.id.txt_tacgia_favourite);
            btnRent = itemView.findViewById(R.id.btn_muonngay_favourite);
        }
    }
}
