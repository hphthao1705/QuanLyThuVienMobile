package com.example.qlthuvien.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qlthuvien.data.model.ChiTietMuonTra;
import com.example.qlthuvien.data.model.TaiLieu;
import com.example.qlthuvien.respository.ChiTietMuonTraRepository;
import com.example.qlthuvien.respository.TaiLieuRepository;
import com.google.gson.JsonObject;

import java.util.List;

public class ChiTietMuonTraViewModel extends ViewModel {
    private final ChiTietMuonTraRepository repository;
    public LiveData<List<ChiTietMuonTra>> liveData = new MutableLiveData();

    public ChiTietMuonTraViewModel() {
        this.repository = new ChiTietMuonTraRepository();
        liveData = repository.loadDetailOfBorrowBook();
    }
    public void insertCallCard(JsonObject muonTra)
    {
        repository.insertDetailOfCallCard(muonTra);
    }
}
