package com.example.qlthuvien.view.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qlthuvien.data.local.entities.Cart;
import com.example.qlthuvien.data.model.MuonTra;
import com.example.qlthuvien.databinding.FragmentCartBookBinding;
import com.example.qlthuvien.view.activities.MainActivity;
import com.example.qlthuvien.view.adapter.CartBookAdapter;
import com.example.qlthuvien.view.adapter.FavouriteAdapter;
import com.example.qlthuvien.dto.DtoFavourite;
import com.example.qlthuvien.R;
import com.example.qlthuvien.viewmodels.CartViewModel;
import com.example.qlthuvien.viewmodels.MuonTraViewModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class CartBookFragment extends Fragment {
    FragmentCartBookBinding binding;
    private CartViewModel viewModel;
    List<DtoFavourite> list = new ArrayList<>();
    List<Cart> cartList = new ArrayList<>();
    CartBookAdapter adapter = new CartBookAdapter(getContext());
    private MuonTraViewModel muonTraViewModel;
    int number = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart_book, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        muonTraViewModel = new ViewModelProvider(this).get(MuonTraViewModel.class);
        //viewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        loadCart();
        initRecyclerView();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.rcvCartBook);
        loadCountBook();
        borrowBooks();
    }
    private void loadCart()
    {
        viewModel.cartBook.observe(getViewLifecycleOwner(), new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> carts) {
                list.clear();
                for (Cart i:carts) {
                    cartList = carts;
                    DtoFavourite j = new DtoFavourite();
                    j.Id = String.valueOf(i.getId_tailieu());
                    j.Check = i.getCheckbox();
                    j.TacGia = i.getTacgia();
                    j.TenSach = i.getTensach();
                    list.add(j);
                }
                adapter.SetData(list);
                binding.rcvCartBook.setAdapter(adapter);
                chooseBooks();
            }
        });
    }
    private void initRecyclerView()
    {
        binding.rcvCartBook.hasFixedSize();
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        binding.rcvCartBook.setLayoutManager(layoutManager);
    }
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            switch (direction)
            {
                case ItemTouchHelper.LEFT:{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn muốn xóa quyển sách này ra khỏi giỏ sách?");
                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // quang code cua thao vo day
                            Cart cart = new Cart(cartList.get(position).getId_tailieu(), 0, cartList.get(position).getHinh(), cartList.get(position).getTensach(),  cartList.get(position).getTacgia(),  cartList.get(position).getCheckbox());
                            viewModel.delete(cart);
                            list.remove(position);
                            // Do nothing, but close the dialog
                            dialog.dismiss();
                            return;

                        }
                    });
                    builder.setNegativeButton("Từ chối", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            adapter.notifyItemChanged(position);
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(), R.color.red))
                    .addSwipeLeftActionIcon(R.drawable.baseline_arrow_back_24)
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };
    private void chooseBooks()
    {
        adapter.setOnCheckedListener(new CartBookAdapter.OnCheckedChangeListener() {
            @Override
            public void onChecked(boolean checked, CartBookAdapter.CartBookAdapterViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
                if(checked)
                {
                    viewModel.update(1, cartList.get(position).getId_tailieu());
                }
                else
                {
                    viewModel.update(0, cartList.get(position).getId_tailieu());
                }
            }
        });
    }
    public void loadCountBook()
    {
        viewModel.count.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.txtSoLuong.setText("Số lượng: " + viewModel.count.getValue());
            }
        });
    }
    public void borrowBooks()
    {
        binding.btnMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                muonTraViewModel.liveData.observe(getViewLifecycleOwner(), new Observer<List<MuonTra>>() {
                    @Override
                    public void onChanged(List<MuonTra> muonTras) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                        Date date = new Date();
                        Date d;
                        try {
                            d = formatter.parse(String.valueOf(date));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        number = muonTras.size();
                        number++;
                        MuonTra mt = new MuonTra(number,1,d,0,1);
                        Log.d("check", mt.getId_muon() + " - " + mt.getId_dg() + " - " + mt.getId_nv() + " - " + mt.getNgaymuon());
                        muonTraViewModel.insertCallCard(mt);
                    }
                });
                viewModel.deleteBooksWhichIsBorrowed();
            }
        });
    }
}