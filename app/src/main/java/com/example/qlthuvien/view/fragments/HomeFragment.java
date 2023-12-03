package com.example.qlthuvien.view.fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.qlthuvien.R;
import com.example.qlthuvien.data.model.ChiTietMuonTra;
import com.example.qlthuvien.data.model.Item_Book;
import com.example.qlthuvien.data.model.Item_Loai;
import com.example.qlthuvien.data.model.TaiLieu;
import com.example.qlthuvien.data.model.ThaoMet;
import com.example.qlthuvien.data.model.TimesComparator;
import com.example.qlthuvien.databinding.FragmentHomeBinding;
import com.example.qlthuvien.databinding.FragmentHomePageBinding;
import com.example.qlthuvien.view.activities.MainActivity;
import com.example.qlthuvien.view.adapter.BookInTopAdapter;
import com.example.qlthuvien.view.adapter.ImageSlideAdapter;
import com.example.qlthuvien.view.adapter.TheLoaiAdapter;
import com.example.qlthuvien.viewmodels.ChiTietMuonTraViewModel;
import com.example.qlthuvien.viewmodels.TaiLieuViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment {
    public int getId_loai() {
        return id_loai;
    }

    public void setId_loai(int id_loai) {
        this.id_loai = id_loai;
    }

    int id_loai;

    public int page;

    public HomeFragment(int i){
        _i = i;
    }
    int _i;
    FragmentHomeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(_i == -1)
        {
            CategoryFragment cate = new CategoryFragment(id_loai);
             cate.setId_loai(id_loai);
             replaceFragment(cate);
        }
        else if(_i > 0)
        {
            MainActivity activity = (MainActivity) getActivity();
            DetailsBookFragment detailsBookFragment = new DetailsBookFragment(_i);
            detailsBookFragment.setPage(page);
            detailsBookFragment.id_loai = id_loai;
            activity.replaceFragment(detailsBookFragment);
        }
        else if(_i == 0)
        {
            replaceFragment(new HomePageFragment());
        }
        else if(_i == -2)
        {
            MainActivity activity = (MainActivity) getActivity();
            NavigationBottomFragment f = new NavigationBottomFragment();
            f.setCurrent(new FavouriteFragment());
            f.setMenu_bottom(R.id.page_favourite);
            activity.replaceFragment(f);
        }
        else if(_i == -3) // category fragment
        {
            replaceFragment(new CategoryFragment(id_loai));
        }
        // Thêm OnKeyListener để theo dõi sự kiện phím Enter
        binding.searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    // Focus has left the SearchView
                    String query = binding.searchView.getQuery().toString();
                    // Xử lý sự kiện khi người dùng nhấn Enter
                    // Cũng kiểm tra xem chuỗi tìm kiếm có rỗng không và thực hiện xử lý tương ứng
                    if(query.isEmpty())
                    {
                        MainActivity activity = (MainActivity) getActivity();
                        NavigationBottomFragment f = new NavigationBottomFragment();
                        f.setCurrent(new HomeFragment(0));
                        f.setMenu_bottom(R.id.page_home);
                        activity.replaceFragment(f);
                        return;
                    }
                    replaceFragment(new SearchFragment(query));
                }
            }
        });
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.isEmpty())
                {
                    MainActivity activity = (MainActivity) getActivity();
                    NavigationBottomFragment f = new NavigationBottomFragment();
                    f.setCurrent(new HomeFragment(0));
                    f.setMenu_bottom(R.id.page_home);
                    activity.replaceFragment(f);
                    return true;
                }
                replaceFragment(new SearchFragment(query));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });
        binding.searchView.setFocusable(false);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(binding.framelayout3.getId(), fragment).commit();
    }
}