package com.example.qlthuvien.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qlthuvien.data.local.entities.Cart;
import com.example.qlthuvien.databinding.FragmentCartBookBinding;
import com.example.qlthuvien.view.adapter.CartBookAdapter;
import com.example.qlthuvien.view.adapter.FavouriteAdapter;
import com.example.qlthuvien.dto.DtoFavourite;
import com.example.qlthuvien.R;
import com.example.qlthuvien.viewmodels.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartBookFragment extends Fragment {
    FragmentCartBookBinding binding;
    private CartViewModel viewModel;
    List<DtoFavourite> list = new ArrayList<>();
    CartBookAdapter adapter = new CartBookAdapter(getContext());
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

        loadCart();

        initRecyclerView();
    }
    private void loadCart()
    {
        viewModel.getUserCart(0);
        viewModel.cartBook.observe(getViewLifecycleOwner(), new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> carts) {
                for (Cart i:carts) {
                    DtoFavourite j = new DtoFavourite();
                    j.Id = String.valueOf(i.getId_tailieu());
                    //j.HinhSach = i.getHinh();
                    j.TacGia = i.getTacgia();
                    j.TenSach = i.getTensach();
                    list.add(j);
                }
                adapter.SetData(list);
            }
        });
    }
    private void initRecyclerView()
    {
        binding.rcvCartBook.hasFixedSize();
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        binding.rcvCartBook.setLayoutManager(layoutManager);
}
}