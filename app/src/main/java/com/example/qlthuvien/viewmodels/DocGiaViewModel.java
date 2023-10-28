package com.example.qlthuvien.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.qlthuvien.data.model.DocGia;
import com.example.qlthuvien.respository.DocGiaRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;

public class DocGiaViewModel extends ViewModel {
    private final DocGiaRepository repository;
    public LiveData<List<DocGia>> liveData_DG = new MutableLiveData();
    public DocGiaViewModel()
    {
        repository = new DocGiaRepository();
        liveData_DG = repository.loadUser();
    }
    public LiveData<List<DocGia>> getData()
    {
        return liveData_DG;
    }
}
