package com.example.qlthuvien.respository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qlthuvien.data.model.ChiTietMuonTra;
import com.example.qlthuvien.data.model.DocGia;
import com.example.qlthuvien.data.remote.Common;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietMuonTraRepository {
    public LiveData<List<ChiTietMuonTra>> loadDetailOfBorrowBook()
    {
        MutableLiveData<List<ChiTietMuonTra>> data = new MutableLiveData<>();
        Common.apiService.getDetailOfBorrowBooks().enqueue(new Callback<List<ChiTietMuonTra>>() {
            @Override
            public void onResponse(Call<List<ChiTietMuonTra>> call, Response<List<ChiTietMuonTra>> response) {
                if (response.isSuccessful())
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ChiTietMuonTra>> call, Throwable t) {

            }
        });
        return data;
    }
}
