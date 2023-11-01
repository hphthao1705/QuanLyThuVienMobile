package com.example.qlthuvien.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qlthuvien.data.model.Loai;
import com.example.qlthuvien.data.model.YeuThich;
import com.example.qlthuvien.respository.LoaiRepository;
import com.example.qlthuvien.respository.YeuThichRepository;

import java.util.List;

public class YeuThichViewModel  extends ViewModel {
    private final YeuThichRepository repository;
    public LiveData<List<YeuThich>> liveData_YT = new MutableLiveData();

    public YeuThichViewModel() {
        this.repository = new YeuThichRepository();
        liveData_YT = repository.loadFavorites();
    }
    public LiveData<List<YeuThich>> getFavorites()
    {
        return liveData_YT;
    }
}
