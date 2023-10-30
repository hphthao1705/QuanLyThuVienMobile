package com.example.qlthuvien.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qlthuvien.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.qlthuvien.data.model.Item_Book;
import com.example.qlthuvien.view.activities.DetailsBookActivity;

public class YeuThichAdapter extends RecyclerView.Adapter<YeuThichAdapter.FavouriteViewHolder>  {
    List<Item_Book> list;
    Context context;
    private YeuThichAdapter.OnClickListener onClickListener = null;

    public  YeuThichAdapter(Context t ){
        context = t;
    }

    public void SetData(List<Item_Book> l)
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
        Item_Book book = list.get(position);
        if(book == null)
        {
            return;
        }
        holder.nameOfBook.setText(book.getName_book());
        Glide
                .with(context)
                .load(list.get(position).getImage_book()).centerCrop().placeholder(R.drawable.avatar)
                .into(holder.imgBook);
        holder.authorOfBook.setText(book.getAuthor_book());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    onClickListener.onClick(book);
            }
        });
        holder.btnRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, DetailsBookActivity.class);
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        return 0;
    }
    public interface OnClickListener {
        void onClick(Item_Book item_book);
    }
    public void setOnClickListener(YeuThichAdapter.OnClickListener listener)
    {
        this.onClickListener = listener;
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
