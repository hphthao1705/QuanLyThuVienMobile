package com.example.qlthuvien.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qlthuvien.data.model.Item_Book;
import com.example.qlthuvien.data.model.TaiLieu;
import com.example.qlthuvien.data.model.YeuThich;
import com.example.qlthuvien.databinding.FragmentFavouriteBinding;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavouriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavouriteFragment extends Fragment implements TheLoaiAdapter.ReplaceFragment{


    YeuThichViewModel viewModelYeuThich;
    TaiLieuViewModel viewModelTaiLieu;
    FragmentFavouriteBinding binding;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void  loadYeuThich(List<YeuThich> yeuThiches)
    {
        viewModelTaiLieu = new ViewModelProvider(this).get(TaiLieuViewModel.class);
        int userIdCurrent = 1;
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false);

        viewModelYeuThich = new ViewModelProvider(this).get(YeuThichViewModel.class);
        viewModelYeuThich.liveData_YT.observe(getViewLifecycleOwner(), new Observer<List<YeuThich>>() {
            @Override
            public void onChanged(List<YeuThich> yeuThiches) {
                loadYeuThich(yeuThiches);
            }
        });


        return binding.getRoot();
    }

    @Override
    public void replaceFragment(int id_tailieu) {
        MainActivity activity = (MainActivity) getActivity();
        NavigationBottomFragment f = new NavigationBottomFragment();
        HomeFragment homeFragment = new HomeFragment(id_tailieu);
        f.setCurrent(homeFragment);
        f.setMenu_bottom(R.id.page_home);
        activity.replaceFragment(f);
    }
}