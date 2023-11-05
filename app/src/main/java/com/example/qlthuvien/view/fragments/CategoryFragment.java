package com.example.qlthuvien.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qlthuvien.R;
import com.example.qlthuvien.data.model.Item_Book;
import com.example.qlthuvien.data.model.Loai;
import com.example.qlthuvien.data.model.TaiLieu;
import com.example.qlthuvien.databinding.FragmentCategoryBinding;
import com.example.qlthuvien.view.activities.MainActivity;
import com.example.qlthuvien.view.adapter.BookCategoryAdapter;
import com.example.qlthuvien.view.adapter.BookInTopAdapter;
import com.example.qlthuvien.viewmodels.LoaiViewModel;
import com.example.qlthuvien.viewmodels.TaiLieuViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {
    TaiLieuViewModel viewModelTaiLieu;
    public void setId_loai(int id_loai) {
        this.id_loai = id_loai;
    }

    int id_loai;
    public CategoryFragment(int id) {
        id_loai = id;
    }

    FragmentCategoryBinding binding;
    private LoaiViewModel viewModel_Loai;
    private void loadBooks()
    {
        ArrayList<Item_Book> list = new ArrayList<>();

        viewModelTaiLieu.liveData_TL.observe(getViewLifecycleOwner(), new Observer<List<TaiLieu>>() {
            @Override
            public void onChanged(List<TaiLieu> taiLieus) {
                for (TaiLieu i: taiLieus)
                {
                    if(i.getId_loai() == id_loai)
                    {
                        Item_Book temp = new Item_Book(i.getHinh(), i.getTentailieu(), i.getTacgia(), i.getId_tailieu());
                        list.add(temp);
                    }

                }
                BookCategoryAdapter adapter = new BookCategoryAdapter(list);
                adapter.setContext(getContext());
                adapter.setActivity((MainActivity) getActivity());
                binding.rcvCategory.setAdapter(adapter);
                binding.rcvCategory.hasFixedSize();
                LinearLayoutManager verticalLayoutManager = new GridLayoutManager(getActivity(), 3);
                binding.rcvCategory.setLayoutManager(verticalLayoutManager);
            }
        });


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel_Loai = new ViewModelProvider(this).get(LoaiViewModel.class);
        viewModelTaiLieu = new ViewModelProvider(this).get(TaiLieuViewModel.class);
        viewModel_Loai.liveData_TL.observe(getViewLifecycleOwner(), new Observer<List<Loai>>() {
            @Override
            public void onChanged(List<Loai> loais) {
                TabLayout tabLayout = (TabLayout) binding.tabTheloai;
                for (Loai i:
                     loais) {
                    tabLayout.addTab(tabLayout.newTab().setId(i.id_loai).setText(i.tenloai));
                }
            }
        });
        binding.tabTheloai.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                id_loai = tab.getId();
               loadBooks();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        loadBooks();
    }
}