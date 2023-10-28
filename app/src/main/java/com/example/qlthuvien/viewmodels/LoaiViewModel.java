package com.example.qlthuvien.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qlthuvien.data.model.Loai;
import com.example.qlthuvien.data.model.TaiLieu;
import com.example.qlthuvien.respository.LoaiRepository;
import com.example.qlthuvien.respository.TaiLieuRepository;

import java.util.List;

public class LoaiViewModel extends ViewModel {
    private final LoaiRepository repository;
    public LiveData<List<Loai>> liveData_TL = new MutableLiveData();

    public LoaiViewModel() {
        this.repository = new LoaiRepository();
        liveData_TL = repository.loadCategories();
    }
    public LiveData<List<Loai>> getCategories()
    {
        return liveData_TL;
    }
}
