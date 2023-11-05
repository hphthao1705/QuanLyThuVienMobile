package com.example.qlthuvien.view.fragments;

import static com.example.qlthuvien.view.activities.LoginActivity.ID_DG;
import static com.example.qlthuvien.view.activities.LoginActivity.SHARED_PREFERENCES_NAME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.qlthuvien.R;
import com.example.qlthuvien.data.local.entities.Cart;
import com.example.qlthuvien.data.model.DocGia;
import com.example.qlthuvien.data.model.NhaXuatBan;
import com.example.qlthuvien.data.model.TaiLieu;
import com.example.qlthuvien.data.model.YeuThich;
import com.example.qlthuvien.data.remote.APIService;
import com.example.qlthuvien.data.remote.Common;
import com.example.qlthuvien.databinding.FragmentDetailsBookBinding;
import com.example.qlthuvien.view.activities.LoginActivity;
import com.example.qlthuvien.view.activities.MainActivity;
import com.example.qlthuvien.viewmodels.CartViewModel;
import com.example.qlthuvien.viewmodels.NhaXuatBanViewModel;
import com.example.qlthuvien.viewmodels.TaiLieuViewModel;
import com.example.qlthuvien.viewmodels.YeuThichViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailsBookFragment extends Fragment {
    MainActivity activity;
    private FragmentDetailsBookBinding binding;
    TaiLieuViewModel viewModel;
    NhaXuatBanViewModel viewModelNXB;

    YeuThichViewModel viewModelYeuThich;
    private CartViewModel cartViewModel;
    private TaiLieu taiLieu2;
    int id_tailieu;
    int id_dg = 0;
    List<YeuThich> ListYeuThich = new ArrayList<>();
    int idYeuThich = 0; // default = 0
    boolean isFavourite = false; // default = false
    public DetailsBookFragment(int id_tailieu)
    {
        this.id_tailieu = id_tailieu;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadId_dg();
        viewModelYeuThich = new ViewModelProvider(this).get(YeuThichViewModel.class);
        viewModel = new ViewModelProvider(this).get(TaiLieuViewModel.class);
        //cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        viewModelNXB = new ViewModelProvider(this).get(NhaXuatBanViewModel.class);
        binding.btnDetailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity = (MainActivity) getActivity();
                NavigationBottomFragment f = new NavigationBottomFragment();
                f.setCurrent(new HomeFragment(0));
                f.setMenu_bottom(R.id.page_home);
                activity.replaceFragment(f);
            }
        });
        if(id_dg == 0) // chua dang nhap
        {
            binding.btnFavourite.setImageResource(R.drawable.icons8_favorite_50_no_active);
        }
        else {
            viewModelYeuThich.liveData_YT.observe(getViewLifecycleOwner(), new Observer<List<YeuThich>>() {
                @Override
                public void onChanged(List<YeuThich> yeuThiches) {
                    for (YeuThich i: yeuThiches) {
                        if(i.getId_tailieu() == id_tailieu && i.getId_dg() == id_dg) // ton tai
                        {
                            binding.btnFavourite.setImageResource(R.drawable.icons8_favorite_50_active);
                            isFavourite = true;
                            idYeuThich = i.getId_yeuthich();
                        }

                    }
                }
            });
        }
        // dang nhap roi thi se co event
        binding.btnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id_dg == 0)
                {
                    Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(loginIntent);
                }
                else { // xu ly
                    APIService api = Common.apiService;
                    if(isFavourite)
                    {
                        // xoa yeu thich
                        Common.apiService.deleteFavorite(idYeuThich).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful())
                                {
                                    idYeuThich = 0;
                                    Toast.makeText(getContext(), "Xóa khỏi danh sách yêu thích", Toast.LENGTH_SHORT).show();
                                    binding.btnFavourite.setImageResource(R.drawable.icons8_favorite_50_no_active);
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });


                    }
                    else
                    {
                        // them yeu thich
                        YeuThich addYeuThich = new YeuThich();
                        addYeuThich.setId_dg(id_dg);
                        addYeuThich.setId_tailieu(id_tailieu);
                        addYeuThich.setId_yeuthich(0);
                        Call<YeuThich> call = Common.apiService.addFavorite(addYeuThich);
                        call.enqueue(new Callback<YeuThich>() {
                            @Override
                            public void onResponse(Call<YeuThich> call, Response<YeuThich> response) {
                                if (response.isSuccessful())
                                {
                                    idYeuThich = response.body().getId_yeuthich();
                                    Toast.makeText(getContext(), "Đã thêm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
                                    binding.btnFavourite.setImageResource(R.drawable.icons8_favorite_50_active);
                                }
                            }
                            @Override
                            public void onFailure(Call<YeuThich> call, Throwable t) {
                                Toast.makeText(getContext(), "loi call api", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    isFavourite = !isFavourite;

                }
            }
        });

        binding.progressbarStart.setVisibility(View.VISIBLE);

        hideView(false);
        loadDetailBook();
        addBookToCart();
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
                loadPulisher(taiLieu.getId_nxb());
                binding.setBook(taiLieu);
                binding.txtSotrang.setText(taiLieu.getSotrang() + "");
                binding.txtNsx.setText("Năm sản xuất: " + taiLieu.getNamsanxuat());
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
                if(id_dg == 0)
                {
                    Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(loginIntent);
                    getActivity().finish();
                }
                else
                {
                    LiveData<Integer> thao = cartViewModel.countBookInCart(taiLieu2.getId_tailieu(), id_dg);

                    thao.observe(getViewLifecycleOwner(), new Observer<Integer>() {
                        @Override
                        public void onChanged(Integer integer) {
                            if(integer > 0)
                            {
                                Toast.makeText(getContext(), "Cuốn sách này đã có trong giỏ sách!!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getContext(), id_dg + "", Toast.LENGTH_SHORT).show();
                                Cart cart = new Cart(taiLieu2.getId_tailieu(), id_dg, taiLieu2.getHinh(), taiLieu2.getTentailieu(), taiLieu2.getTacgia(), 0);
                                cartViewModel.insert(cart);
                                Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
    private void loadPulisher(int id)
    {
        viewModelNXB.loadPulisher(id);
        viewModelNXB.liveData.observe(getViewLifecycleOwner(), new Observer<NhaXuatBan>() {
            @Override
            public void onChanged(NhaXuatBan nhaXuatBan) {
                binding.txtNxb.setText("Nhà xuất bản: " + nhaXuatBan.getTennxb());
            }
        });
    }
    private void loadId_dg()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        id_dg = Integer.parseInt(sharedPreferences.getString("user_id", "0"));
    }
}