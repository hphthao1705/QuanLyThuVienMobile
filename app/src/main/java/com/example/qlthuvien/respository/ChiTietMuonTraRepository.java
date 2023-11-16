package com.example.qlthuvien.respository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qlthuvien.data.model.ChiTietMuonTra;
import com.example.qlthuvien.data.model.ChiTietMuonTra_Full;
import com.example.qlthuvien.data.model.DocGia;
import com.example.qlthuvien.data.model.MuonTra;
import com.example.qlthuvien.data.remote.Common;
import com.google.gson.JsonObject;

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
    public void insertDetailOfCallCard(JsonObject muontra)
    {
        Common.apiService.insertDetailOfCallCard(muontra).enqueue(new Callback<ChiTietMuonTra>() {

            @Override
            public void onResponse(Call<ChiTietMuonTra> call, Response<ChiTietMuonTra> response) {
                if(response.isSuccessful() && response.body()!=null)
                {
                    Log.d("Post respone","ChiTietMuonTra: " + response.body());
                }
                else {
                    Log.d("Post error", "Error CTMT: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ChiTietMuonTra> call, Throwable t) {

            }
        });
    }
    public LiveData<List<ChiTietMuonTra_Full>> loadDetailOfBorrowBook2()
    {
        MutableLiveData<List<ChiTietMuonTra_Full>> data = new MutableLiveData<>();
        Common.apiService.getDetailOfBorrowBooks2().enqueue(new Callback<List<ChiTietMuonTra_Full>>() {
            @Override
            public void onResponse(Call<List<ChiTietMuonTra_Full>> call, Response<List<ChiTietMuonTra_Full>> response) {
                if (response.isSuccessful())
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ChiTietMuonTra_Full>> call, Throwable t) {

            }
        });
        return data;
    }
}
