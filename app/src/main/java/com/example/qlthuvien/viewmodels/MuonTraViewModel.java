package com.example.qlthuvien.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qlthuvien.data.model.MuonTra;
import com.example.qlthuvien.data.model.NhaXuatBan;
import com.example.qlthuvien.respository.MuonTraRepository;
import com.example.qlthuvien.respository.NhaXuatBanRepository;
import com.google.gson.JsonObject;

import java.util.List;

public class MuonTraViewModel extends ViewModel {
    private final MuonTraRepository repository;
    public LiveData<List<MuonTra>> liveData = new MutableLiveData();
    public MuonTraViewModel()
    {
        repository = new MuonTraRepository();
    }
    public void loadListOfBorrowBook()
    {
        liveData = repository.getListOfBorrowBook();
    }
    public void insertCallCard(JsonObject muonTra)
    {
        repository.insertCallCard(muonTra);
    }
}
