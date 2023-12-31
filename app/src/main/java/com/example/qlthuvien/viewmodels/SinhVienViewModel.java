package com.example.qlthuvien.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.qlthuvien.data.model.DocGia;
import com.example.qlthuvien.data.model.SinhVien;
import com.example.qlthuvien.respository.DocGiaRepository;
import com.example.qlthuvien.respository.SinhVienRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;

public class SinhVienViewModel extends ViewModel {
    private SinhVienRepository sinhVienRepository;
    private MutableLiveData<SinhVien> tenSinhVienLiveData = new MutableLiveData<>();

    public SinhVienViewModel() {
        sinhVienRepository = new SinhVienRepository();
    }

    public LiveData<DocGia> registerDocGia(DocGia docGia) {
        return sinhVienRepository.registerDocGia(docGia);
    }

    public LiveData<Boolean> checkAccountExistence(int id_sv) {
        return sinhVienRepository.checkAccountExistence(id_sv);
    }
    public LiveData<Boolean> checkMssvExistence(String mssv) {
        return sinhVienRepository.checkMssvExistence(mssv);
    }

    public LiveData<Boolean> checkEmailExistence(String email)
    {
        return sinhVienRepository.checkEmailExistence(email);
    }

//    public LiveData<SinhVien> generateTenSinhVien(String mssv) {
//
//        LiveData<List<SinhVien>> sinhVienListData = sinhVienRepository.getAllSinhVienInfo();
//
//        sinhVienListData.observeForever(new Observer<List<SinhVien>>() {
//            @Override
//            public void onChanged(List<SinhVien> sinhVienList) {
//                if (sinhVienList != null && !mssv.isEmpty()) {
//                    for (SinhVien sinhVien : sinhVienList) {
//                        if(sinhVien.getMssv().equals(mssv))
//                        {
//                            tenSinhVienLiveData.setValue(sinhVien);
//                            break;
//                        }
////                        else
////                        {
////                            tenSinhVienLiveData.setValue(null);
////                        }
//                    }
//                }
//            }
//        });
//
//
//        Log.d("tenSinhVienLiveData", "" + tenSinhVienLiveData.getValue());
//
//        return tenSinhVienLiveData;
//    }

    public LiveData<SinhVien> generateTenSinhVien(String mssv) {
        LiveData<List<SinhVien>> sinhVienListData = sinhVienRepository.getAllSinhVienInfo();

        sinhVienListData.observeForever(new Observer<List<SinhVien>>() {
            @Override
            public void onChanged(List<SinhVien> sinhVienList) {
                SinhVien matchedSinhVien = null;
                if (sinhVienList != null && !mssv.isEmpty()) {
                    for (SinhVien sinhVien : sinhVienList) {
                        if(sinhVien.getMssv().equals(mssv)) {
                            matchedSinhVien = sinhVien;
                            break;
                        }
                    }
                }
                tenSinhVienLiveData.setValue(matchedSinhVien);
            }
        });

        return tenSinhVienLiveData;
    }

}
