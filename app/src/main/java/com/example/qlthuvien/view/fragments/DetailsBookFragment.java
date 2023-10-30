package com.example.qlthuvien.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.qlthuvien.R;
import com.example.qlthuvien.data.local.entities.Cart;
import com.example.qlthuvien.data.model.TaiLieu;
import com.example.qlthuvien.databinding.FragmentDetailsBookBinding;
import com.example.qlthuvien.viewmodels.CartViewModel;
import com.example.qlthuvien.viewmodels.TaiLieuViewModel;


public class DetailsBookFragment extends Fragment {
    private FragmentDetailsBookBinding binding;
    TaiLieuViewModel viewModel;
    private CartViewModel cartViewModel;
    private TaiLieu taiLieu2;
    int id_tailieu;
    public DetailsBookFragment(int id_tailieu)
    {
        this.id_tailieu = id_tailieu;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(TaiLieuViewModel.class);
        //cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        binding.btnDetailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.progressbarStart.setVisibility(View.VISIBLE);
        hideView(false);
        loadDetailBook();
        //addBookToCart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_book, container, false);
        return binding.getRoot();
    }
    private void loadDetailBook()
    {
        viewModel.loadDetailBook(id_tailieu);
        viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<TaiLieu>() {
            @Override
            public void onChanged(TaiLieu taiLieu) {
                taiLieu2 = taiLieu;
                binding.setBook(taiLieu);
                binding.hinhsachDetails.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

                Glide
                        .with(getContext())
                        .load(taiLieu.getHinh()).centerCrop().placeholder(R.drawable.avatar)
                        .into(binding.hinhsachDetails);

                hideView(true);
                binding.progressbarStart.setVisibility(View.GONE);
            }
        });
    }
    private void hideView(boolean check)
    {
        if(check)
        {
            binding.view1.setVisibility(View.VISIBLE);
            binding.view2.setVisibility(View.VISIBLE);
            binding.btnAddcart.setVisibility(View.VISIBLE);
        }
        else
        {
            binding.view1.setVisibility(View.GONE);
            binding.view2.setVisibility(View.GONE);
            binding.btnAddcart.setVisibility(View.GONE);
        }
    }
    private void addBookToCart()
    {
        binding.btnAddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart cart = new Cart(taiLieu2.getId_tailieu(), 0, taiLieu2.getHinh(), taiLieu2.getTentailieu(), taiLieu2.getTacgia(), false, false);
                cartViewModel.insert(cart);
            }
        });
    }
}