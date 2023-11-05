package com.example.qlthuvien.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlthuvien.data.model.Item_Book;
import com.example.qlthuvien.dto.DtoFavourite;
import com.example.qlthuvien.R;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.qlthuvien.view.activities.DetailsBookActivity;
import com.example.qlthuvien.view.fragments.CartBookFragment;
import com.example.qlthuvien.viewmodels.CartViewModel;

public class CartBookAdapter extends RecyclerView.Adapter<CartBookAdapter.CartBookAdapterViewHolder>  {
    List<DtoFavourite> list;
    private OnCheckedChangeListener onCheckedChangeListener = null;
    Context context;
    public  CartBookAdapter(Context t){
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
        if(dtoFavourite.Check == 1)
        {
            holder.checkBox.setChecked(true);
        }
        else
        {
            holder.checkBox.setChecked(false);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                onCheckedChangeListener.onChecked(b, holder);
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
        private CheckBox checkBox;

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
            checkBox = itemView.findViewById(R.id.ckeckbox);
        }
    }
    public interface OnCheckedChangeListener {
        void onChecked(boolean checked, CartBookAdapterViewHolder viewHolder);
    }
    public void setOnCheckedListener(OnCheckedChangeListener listener)
    {
        this.onCheckedChangeListener = listener;
    }
}
