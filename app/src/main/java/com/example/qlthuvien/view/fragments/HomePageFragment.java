package com.example.qlthuvien.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qlthuvien.R;
import com.example.qlthuvien.data.model.Item_Book;
import com.example.qlthuvien.data.model.Item_Loai;
import com.example.qlthuvien.databinding.FragmentHomeBinding;
import com.example.qlthuvien.databinding.FragmentHomePageBinding;
import com.example.qlthuvien.view.activities.MainActivity;
import com.example.qlthuvien.view.adapter.BookCategoryAdapter;
import com.example.qlthuvien.view.adapter.BookInTopAdapter;
import com.example.qlthuvien.view.adapter.ImageSlideAdapter;
import com.example.qlthuvien.view.adapter.TheLoaiAdapter;

import java.util.ArrayList;

public class HomePageFragment extends Fragment implements TheLoaiAdapter.ReplaceFragment {
    TheLoaiAdapter loaiAdapter = new TheLoaiAdapter(new ArrayList<Item_Loai>(), HomePageFragment.this);
    FragmentHomePageBinding binding;
    ImageSlideAdapter imageSlideAdapter = new ImageSlideAdapter(new ArrayList<>());
    BookInTopAdapter bookInTopAdapter = new BookInTopAdapter(new ArrayList<>());
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View par = view.getRootView();
        initRecyclerView();
        loadLoai();
        loadSlideImage();
        loadBookInTop();
        loadNewBook();
        loadGoiY();
    }
    private void loadLoai()
    {
        ArrayList<Item_Loai> list = new ArrayList<>();
        //
        Item_Loai loai = new Item_Loai("Tin học", R.drawable.ic_theloai_tinhoc);
        list.add(loai);
        //
        Item_Loai loai2 = new Item_Loai("Kinh tế", R.drawable.ic_theloai_ketoan);
        list.add(loai2);
        //
        Item_Loai loai3 = new Item_Loai("Sinh học", R.drawable.ic_theloai_sinhhoc);
        list.add(loai3);
        //
        Item_Loai loai4 = new Item_Loai("Du lịch", R.drawable.ic_theloai_dulich);
        list.add(loai4);
        //
        Item_Loai loai5 = new Item_Loai("Ngoại ngữ", R.drawable.ic_theloai_ngoaingu);
        list.add(loai5);
        //
        Item_Loai loai6 = new Item_Loai("Thời trang", R.drawable.ic_theloai_detmay);
        list.add(loai6);
        loaiAdapter = new TheLoaiAdapter(list,HomePageFragment.this);

        binding.recyclerviewLoai.setAdapter(loaiAdapter);
    }
    private void initRecyclerView()
    {
        binding.recyclerviewLoai.hasFixedSize();
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerviewLoai.setLayoutManager(layoutManager);

        binding.recyclerSlideimg.hasFixedSize();
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerSlideimg.setLayoutManager(layoutManager2);

        binding.recyclerBookinTop.hasFixedSize();
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerBookinTop.setLayoutManager(layoutManager3);

        binding.recyclerSachMoi.hasFixedSize();
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerSachMoi.setLayoutManager(layoutManager4);

        binding.recyclerGoiY.hasFixedSize();
        LinearLayoutManager layoutManager5 = new GridLayoutManager(getActivity(), 2);
        binding.recyclerGoiY.setLayoutManager(layoutManager5);

    }
    private void loadSlideImage()
    {
        ArrayList<Integer> list = new ArrayList<>();
        Integer i = R.drawable.thao_bg_1;
        Integer i2 = R.drawable.thao_bg_2;
        Integer i3 = R.drawable.thao_bg_3;

        list.add(i);
        list.add(i2);
        list.add(i3);
        imageSlideAdapter = new ImageSlideAdapter(list);
        binding.recyclerSlideimg.setAdapter(imageSlideAdapter);
    }

    private void loadBookInTop()
    {
        ArrayList<Item_Book> list = new ArrayList<>();
        Item_Book i = new Item_Book("", "Conan", "Thao");
        Item_Book i2 = new Item_Book("", "Hehe", "Thao1");

        list.add(i);
        list.add(i2);

        bookInTopAdapter = new BookInTopAdapter(list);
        binding.recyclerBookinTop.setAdapter(bookInTopAdapter);
    }

    private void loadNewBook()
    {
        ArrayList<Item_Book> list = new ArrayList<>();
        Item_Book i = new Item_Book("", "Conan", "Thao");
        Item_Book i2 = new Item_Book("", "Hehe", "Thao1");

        list.add(i);
        list.add(i2);

        bookInTopAdapter = new BookInTopAdapter(list);
        binding.recyclerSachMoi.setAdapter(bookInTopAdapter);
    }

    private void loadGoiY()
    {
        ArrayList<Item_Book> list = new ArrayList<>();
        Item_Book i = new Item_Book("", "Conan", "Thao");
        Item_Book i2 = new Item_Book("", "Hehe", "Thao1");

        Item_Book i3 = new Item_Book("", "Conan", "Thao3");
        Item_Book i4 = new Item_Book("", "Hehe", "Thao4");

        list.add(i);
        list.add(i2);
        list.add(i3);
        list.add(i4);

        bookInTopAdapter = new BookInTopAdapter(list);
        binding.recyclerGoiY.setAdapter(bookInTopAdapter);
    }

    @Override
    public void replaceFragment() {
        MainActivity activity = (MainActivity) getActivity();
        NavigationBottomFragment f = new NavigationBottomFragment();
        HomeFragment homeFragment = new HomeFragment(1);
        f.setCurrent(homeFragment);
        f.setMenu_bottom(R.id.page_home);
        activity.replaceFragment(f);
    }
}