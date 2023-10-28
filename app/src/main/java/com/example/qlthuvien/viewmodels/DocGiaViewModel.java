package com.example.qlthuvien.viewmodels;

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
    public MutableLiveData<List<DocGia>> liveData_DG = new MutableLiveData();
    public DocGiaViewModel()
    {
        repository = new DocGiaRepository();
        loadUser();
    }
    private void loadUser()
    {
        Flowable<DocGia> flow = repository.loadUser();
        ArrayList<DocGia> list_DG = new ArrayList<>();
        flow.subscribe(new DisposableSubscriber<DocGia>(){

            @Override
            public void onNext(DocGia docGia) {
                list_DG.add(docGia);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                liveData_DG.postValue(list_DG);
            }
        });

    }
}
