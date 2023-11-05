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
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qlthuvien.R;
import com.example.qlthuvien.data.model.ChiTietMuonTra;
import com.example.qlthuvien.data.model.Item_Book;
import com.example.qlthuvien.data.model.Item_Loai;
import com.example.qlthuvien.data.model.Loai;
import com.example.qlthuvien.data.model.TaiLieu;
import com.example.qlthuvien.data.model.ThaoMet;
import com.example.qlthuvien.data.model.TimesComparator;
import com.example.qlthuvien.databinding.FragmentHomeBinding;
import com.example.qlthuvien.databinding.FragmentHomePageBinding;
import com.example.qlthuvien.view.activities.MainActivity;
import com.example.qlthuvien.view.adapter.BookCategoryAdapter;
import com.example.qlthuvien.view.adapter.BookInTopAdapter;
import com.example.qlthuvien.view.adapter.ImageSlideAdapter;
import com.example.qlthuvien.view.adapter.TheLoaiAdapter;
import com.example.qlthuvien.viewmodels.ChiTietMuonTraViewModel;
import com.example.qlthuvien.viewmodels.LoaiViewModel;
import com.example.qlthuvien.viewmodels.TaiLieuViewModel;
import com.google.android.material.tabs.TabLayout;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class HomePageFragment extends Fragment implements TheLoaiAdapter.ReplaceFragment {
    TheLoaiAdapter loaiAdapter = new TheLoaiAdapter(new ArrayList<>(), HomePageFragment.this);
    FragmentHomePageBinding binding;
    ImageSlideAdapter imageSlideAdapter = new ImageSlideAdapter(new ArrayList<>());
    BookInTopAdapter bookInTopAdapter = new BookInTopAdapter(new ArrayList<>());
    private TaiLieuViewModel viewModel_TL;
    private ChiTietMuonTraViewModel viewModel_MT;

    private LoaiViewModel viewModel_Loai;

    ArrayList<TaiLieu> list_tailieu = new ArrayList<>();
    ArrayList<ChiTietMuonTra> list_ctmt = new ArrayList<>();
    ArrayList<ThaoMet> list_countTime = new ArrayList<>();
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
        hideView(false);
        binding.progressbarStart.setVisibility(View.VISIBLE);

        viewModel_TL = new ViewModelProvider(this).get(TaiLieuViewModel.class);
        viewModel_MT = new ViewModelProvider(this).get(ChiTietMuonTraViewModel.class);
        viewModel_Loai = new ViewModelProvider(this).get(LoaiViewModel.class);
        loadBook();

        initRecyclerView();
        loadLoai();
        loadSlideImage();
    }
    private void loadLoai()
    {
        ArrayList<Item_Loai> list = new ArrayList<>();
        viewModel_Loai.liveData_TL.observe(getViewLifecycleOwner(), new Observer<List<Loai>>() {
            @Override
            public void onChanged(List<Loai> loais) {
                for (Loai i:
                        loais) {
                    Item_Loai loai = new Item_Loai();
                    loai.setName(i.tenloai);
                    loai.setIcon(i.icon);
                    list.add(loai);


                }
            }
        });
        loaiAdapter = new TheLoaiAdapter(list,HomePageFragment.this);
        loaiAdapter.setContext(getContext());
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
    private void loadNewBook()
    {
        List<TaiLieu> list_temp = null;
        ArrayList<Item_Book> list = new ArrayList<>();
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//        }
            list_temp = list_tailieu.stream().collect(Collectors.toList());
            for (TaiLieu i:list_temp) {
            Item_Book b = new Item_Book(i.getHinh(),i.getTentailieu(),i.getTacgia(), i.getId_tailieu());
            list.add(b);
        }
        bookInTopAdapter = new BookInTopAdapter(list);
        bookInTopAdapter.setContext(getContext());
        binding.recyclerSachMoi.setAdapter(bookInTopAdapter);
        clickOnItem();
    }
    private void loadRecommendBooks()
    {
        List<TaiLieu> list_temp = null;
        ArrayList<Item_Book> list = new ArrayList<>();
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Collections.shuffle(list_tailieu);
            list_temp = list_tailieu.stream().limit(10).collect(Collectors.toList());
//        }
        for (TaiLieu i:list_temp) {
            Item_Book b = new Item_Book(i.getHinh(),i.getTentailieu(),i.getTacgia(), i.getId_tailieu());
            list.add(b);
        }

        bookInTopAdapter = new BookInTopAdapter(list);
        bookInTopAdapter.setContext(getContext());
        binding.recyclerGoiY.setAdapter(bookInTopAdapter);
        clickOnItem();
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
    private void loadBook()
    {
        viewModel_TL.liveData_TL.observe(getViewLifecycleOwner(), new Observer<List<TaiLieu>>() {
            @Override
            public void onChanged(List<TaiLieu> taiLieus) {
                list_tailieu = (ArrayList<TaiLieu>) taiLieus;
                loadNewBook();
                loadRecommendBooks();
                loadDetailOfBorrowBook();
            }
        });
    }
    private void loadDetailOfBorrowBook()
    {
        viewModel_MT.liveData.observe(getViewLifecycleOwner(), new Observer<List<ChiTietMuonTra>>() {
            @Override
            public void onChanged(List<ChiTietMuonTra> chiTietMuonTras) {
                list_ctmt = (ArrayList<ChiTietMuonTra>) chiTietMuonTras;
                countTimes();
                sortListCountTime();
                loadBookInTop();
            }
        });
        hideView(true);
        binding.progressbarStart.setVisibility(View.GONE);
    }
    private void countTimes()
    {
        for (ChiTietMuonTra i:list_ctmt) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                if(list_countTime.stream().filter(o -> o.getId_tailieu() == i.getId_tailieu()).findFirst().isPresent())
                {
                    for(ThaoMet thaoMet: list_countTime)
                    {
                        if(thaoMet.getId_tailieu() == i.getId_tailieu())
                        {
                            thaoMet.setSolanmuon(thaoMet.getSolanmuon() + 1);
                        }
                    }
                }
                else
                {
                    ThaoMet thaoMet = new ThaoMet(i.getId_tailieu(), 1);
                    list_countTime.add(thaoMet);
                }
            }
        }
    }
    private void sortListCountTime()
    {
        Collections.sort(list_countTime, new TimesComparator());
    }
    private void loadBookInTop()
    {
        ArrayList<Item_Book> list = new ArrayList<>();
        for (ThaoMet i: list_countTime)
        {
            for(TaiLieu j:list_tailieu)
            {
                if(i.getId_tailieu() == j.getId_tailieu())
                {
                    Item_Book b = new Item_Book(j.getHinh(),j.getTentailieu(),j.getTacgia(),j.getId_tailieu());
                    list.add(b);
                }
            }
        }
        bookInTopAdapter = new BookInTopAdapter(list);
        bookInTopAdapter.setContext(getContext());
        binding.recyclerBookinTop.setAdapter(bookInTopAdapter);
        clickOnItem();
    }
    private void hideView(boolean check)
    {
        if(check)
        {
            binding.recyclerviewLoai.setVisibility(View.VISIBLE);
            binding.recyclerGoiY.setVisibility(View.VISIBLE);
            binding.recyclerSachMoi.setVisibility(View.VISIBLE);
            binding.recyclerSlideimg.setVisibility(View.VISIBLE);
            binding.recyclerBookinTop.setVisibility(View.VISIBLE);
            binding.txt1.setVisibility(View.VISIBLE);
            binding.txt2.setVisibility(View.VISIBLE);
            binding.txt3.setVisibility(View.VISIBLE);
            binding.txt5.setVisibility(View.VISIBLE);
            binding.txt6.setVisibility(View.VISIBLE);
        }
        else
        {
            binding.recyclerviewLoai.setVisibility(View.GONE);
            binding.recyclerGoiY.setVisibility(View.GONE);
            binding.recyclerSachMoi.setVisibility(View.GONE);
            binding.recyclerSlideimg.setVisibility(View.GONE);
            binding.recyclerBookinTop.setVisibility(View.GONE);
            binding.txt1.setVisibility(View.GONE);
            binding.txt2.setVisibility(View.GONE);
            binding.txt3.setVisibility(View.GONE);
            binding.txt5.setVisibility(View.GONE);
            binding.txt6.setVisibility(View.GONE);
        }
    }
    private void clickOnItem()
    {
        bookInTopAdapter.setOnClickListener(new BookInTopAdapter.OnClickListener() {
            @Override
            public void onClick(Item_Book item_book) {
                replaceFragment(item_book.getId_tailieu());
            }
        });
    }
}