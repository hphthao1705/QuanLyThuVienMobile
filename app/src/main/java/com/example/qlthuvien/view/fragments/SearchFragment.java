package com.example.qlthuvien.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlthuvien.R;
import com.example.qlthuvien.data.model.Item_Book;
import com.example.qlthuvien.data.model.TaiLieu;
import com.example.qlthuvien.databinding.FragmentSearchBinding;
import com.example.qlthuvien.view.activities.MainActivity;
import com.example.qlthuvien.view.adapter.SearchAdapter;
import com.example.qlthuvien.viewmodels.TaiLieuViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchFragment extends Fragment {
    FragmentSearchBinding binding;
    TaiLieuViewModel viewModelTaiLieu;
    String searchText;

    public SearchFragment(String searchText) {
        this.searchText = searchText;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModelTaiLieu = new ViewModelProvider(this).get(TaiLieuViewModel.class);
        viewModelTaiLieu.liveData_TL.observe(getViewLifecycleOwner(), new Observer<List<TaiLieu>>() {
            @Override
            public void onChanged(List<TaiLieu> taiLieus) {
                loadSearchText(taiLieus);
            }
        });
    }

    private void loadSearchText(List<TaiLieu> taiLieus)
    {
//        ArrayList<String> list = new ArrayList<>();
//        list.add("Thao");
        ArrayList<Item_Book> books = new ArrayList<>();

        taiLieus = taiLieus.stream().filter(b -> b.getTentailieu().toLowerCase().startsWith(searchText.toLowerCase())).collect(Collectors.toList());
        for(TaiLieu i : taiLieus)
        {

            Item_Book b = new Item_Book(i.getHinh(),i.getTentailieu(),i.getTacgia(),i.getId_tailieu());
            books.add(b);
        }
        SearchAdapter adapter = new SearchAdapter(books);
        adapter.setMainActivity((MainActivity) getActivity());
        LinearLayoutManager verticalLayoutManager =  new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerviewSearch.setAdapter(adapter);
        binding.recyclerviewSearch.setLayoutManager(verticalLayoutManager);


    }
}