package com.example.qlthuvien.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qlthuvien.data.model.DocGia;
import com.example.qlthuvien.data.model.TaiLieu;
import com.example.qlthuvien.respository.TaiLieuRepository;

import java.util.List;

public class TaiLieuViewModel extends ViewModel {
    private final TaiLieuRepository repository;
    public LiveData<List<TaiLieu>> liveData_TL = new MutableLiveData();

    public TaiLieuViewModel() {
        this.repository = new TaiLieuRepository();
        liveData_TL = repository.loadBook();
    }
    public LiveData<List<TaiLieu>> getBook()
    {
        return liveData_TL;
    }
}
