package com.example.qlthuvien.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qlthuvien.data.model.NhaXuatBan;
import com.example.qlthuvien.data.model.TaiLieu;
import com.example.qlthuvien.respository.NhaXuatBanRepository;
import com.example.qlthuvien.respository.TaiLieuRepository;

import java.util.List;

public class NhaXuatBanViewModel extends ViewModel {
    private final NhaXuatBanRepository repository;
    public LiveData<NhaXuatBan> liveData = new MutableLiveData();
    public NhaXuatBanViewModel()
    {
        repository = new NhaXuatBanRepository();
    }
    public void loadPulisher(int id_tailieu)
    {
        liveData = repository.loadPulisher(id_tailieu);
    }
}
