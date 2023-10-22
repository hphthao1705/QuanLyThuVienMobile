package com.example.qlthuvien.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qlthuvien.R;
import com.example.qlthuvien.data.model.Item_Book;
import com.example.qlthuvien.databinding.FragmentCategoryBinding;
import com.example.qlthuvien.view.adapter.BookCategoryAdapter;
import com.example.qlthuvien.view.adapter.BookInTopAdapter;

import java.util.ArrayList;


public class CategoryFragment extends Fragment {

    FragmentCategoryBinding binding;

    private void loadBooks()
    {
        ArrayList<Item_Book> list = new ArrayList<>();
        for (int i = 0 ; i <10; i++)
        {

            Item_Book temp = new Item_Book("", "Conan", "Thao");
            list.add(temp);
        }

        BookCategoryAdapter book = new BookCategoryAdapter(list);
        binding.rcvCategory.setAdapter(book);
        binding.rcvCategory.hasFixedSize();
        LinearLayoutManager verticalLayoutManager = new GridLayoutManager(getActivity(), 3);
        binding.rcvCategory.setLayoutManager(verticalLayoutManager);
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
        loadBooks();
    }
}