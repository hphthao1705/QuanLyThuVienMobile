package com.example.qlthuvien.view.fragments;

import static com.example.qlthuvien.view.activities.LoginActivity.EMAIL;
import static com.example.qlthuvien.view.activities.LoginActivity.ID_DG;
import static com.example.qlthuvien.view.activities.LoginActivity.MSSV;
import static com.example.qlthuvien.view.activities.LoginActivity.NAME;
import static com.example.qlthuvien.view.activities.LoginActivity.PASSWORD;
import static com.example.qlthuvien.view.activities.LoginActivity.SHARED_PREFERENCES_NAME;
import static com.example.qlthuvien.view.activities.LoginActivity.USER_ID;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qlthuvien.data.model.Item_Book;
import com.example.qlthuvien.data.model.TaiLieu;
import com.example.qlthuvien.data.model.YeuThich;
import com.example.qlthuvien.databinding.FragmentFavouriteBinding;
import com.example.qlthuvien.di.DaggerYeuThichComponent;
import com.example.qlthuvien.di.YeuThichComponent;
import com.example.qlthuvien.view.activities.MainActivity;
import com.example.qlthuvien.view.adapter.BookInTopAdapter;
import com.example.qlthuvien.view.adapter.FavouriteAdapter;
import com.example.qlthuvien.dto.DtoFavourite;
import com.example.qlthuvien.R;
import com.example.qlthuvien.view.adapter.TheLoaiAdapter;
import com.example.qlthuvien.view.adapter.YeuThichAdapter;
import com.example.qlthuvien.viewmodels.LoaiViewModel;
import com.example.qlthuvien.viewmodels.TaiLieuViewModel;
import com.example.qlthuvien.viewmodels.YeuThichViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FavouriteFragment extends Fragment implements TheLoaiAdapter.ReplaceFragment{

    String id_dg = "0";
    YeuThichViewModel viewModelYeuThich;
    TaiLieuViewModel viewModelTaiLieu;
    FragmentFavouriteBinding binding;
    YeuThichComponent component = DaggerYeuThichComponent.create();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        loadId_dg();
        super.onViewCreated(view, savedInstanceState);
    }

    private void loadId_dg()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        id_dg = sharedPreferences.getString(ID_DG, "0");
//        id_dg = Integer.parseInt(sharedPreferences.getString("user_id", "0"));
    }
    private void  loadYeuThich(List<YeuThich> yeuThiches)
    {
        viewModelTaiLieu = new ViewModelProvider(this).get(TaiLieuViewModel.class);
        int userIdCurrent = Integer.parseInt(id_dg);
        ArrayList<Item_Book> list = new ArrayList<>();
        yeuThiches = yeuThiches.stream()
                .filter(yeuThich -> yeuThich.getId_dg() == userIdCurrent)
                .collect(Collectors.toList());
        List<Integer> Id_TaiLieus = new ArrayList<Integer>();
        for (YeuThich i : yeuThiches)
        {
            Id_TaiLieus.add(i.getId_tailieu());
        }
        viewModelTaiLieu.liveData_TL.observe(getViewLifecycleOwner(), new Observer<List<TaiLieu>>() {
            @Override
            public void onChanged(List<TaiLieu> taiLieus) {
                for (TaiLieu i:
                     taiLieus) {
                    if(Id_TaiLieus.contains(i.getId_tailieu()))
                    {
                        Item_Book b = new Item_Book(i.getHinh(),i.getTentailieu(),i.getTacgia(), i.getId_tailieu());
                        list.add(b);
                    }
                }
                LinearLayoutManager verticalLayoutManager =  new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                binding.rcvFavourite.setLayoutManager(verticalLayoutManager);
                YeuThichAdapter adapter = new YeuThichAdapter(getContext());
                adapter.setOnClickListener(new YeuThichAdapter.OnClickListener() {
                    @Override
                    public void onClick(Item_Book item_book) {
                        replaceFragment(item_book.getId_tailieu());
                    }
                });
                adapter.SetData(list);
                binding.rcvFavourite.setAdapter(adapter);
                binding.progressbarStart.setVisibility(View.GONE);
            }
        });


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loadId_dg();
        Log.d("id_dg", "" + id_dg);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false);
        if(Integer.parseInt(id_dg) == 0)
        {
            binding.empty1.setVisibility(View.VISIBLE);
            binding.empty2.setVisibility(View.VISIBLE);
            binding.progressbarStart.setVisibility(View.GONE);
        }
        else
        {
            binding.empty1.setVisibility(View.GONE);
            binding.empty2.setVisibility(View.GONE);
            //viewModelYeuThich = new ViewModelProvider(this).get(YeuThichViewModel.class);
            viewModelYeuThich = component.getViewModel();
            viewModelYeuThich.liveData_YT.observe(getViewLifecycleOwner(), new Observer<List<YeuThich>>() {
                @Override
                public void onChanged(List<YeuThich> yeuThiches) {
                    loadYeuThich(yeuThiches);
                }
            });
        }


        return binding.getRoot();
    }

    @Override
    public void replaceFragment(int id_tailieu) {
        MainActivity activity = (MainActivity) getActivity();
        NavigationBottomFragment f = new NavigationBottomFragment();
        HomeFragment homeFragment = new HomeFragment(id_tailieu);
        homeFragment.page = -2;
        f.setCurrent(homeFragment);
        f.setMenu_bottom(R.id.page_home);
        activity.replaceFragment(f);
    }
}