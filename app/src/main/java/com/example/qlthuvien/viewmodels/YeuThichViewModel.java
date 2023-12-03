package com.example.qlthuvien.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qlthuvien.data.model.Loai;
import com.example.qlthuvien.data.model.TaiLieu;
import com.example.qlthuvien.data.model.YeuThich;
import com.example.qlthuvien.respository.LoaiRepository;
import com.example.qlthuvien.respository.YeuThichRepository;

import java.util.List;

import javax.inject.Inject;

public class YeuThichViewModel  extends ViewModel {
    private final YeuThichRepository repository;

    private LiveData<List<TaiLieu>> favoriteBooks;

    public MutableLiveData<List<YeuThich>> liveData_YT = new MutableLiveData();

    @Inject
    public YeuThichViewModel(YeuThichRepository repository) {
        this.repository = repository;
        liveData_YT = repository.loadFavorites();
    }


    public LiveData<List<YeuThich>> getFavourites()
    {
        return liveData_YT;
    }


    public void init(int idDg) {
        favoriteBooks = repository.getFavoriteBooks(idDg);
    }

    public LiveData<List<TaiLieu>> getFavoriteBooks() {
        return favoriteBooks;
    }
}