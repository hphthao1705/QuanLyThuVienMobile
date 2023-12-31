package com.example.qlthuvien.respository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qlthuvien.data.model.MuonTra;
import com.example.qlthuvien.data.model.NhaXuatBan;
import com.example.qlthuvien.data.remote.Common;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MuonTraRepository {
    public LiveData<List<MuonTra>> getListOfBorrowBook()
    {
        MutableLiveData<List<MuonTra>> data = new MutableLiveData<>();
        Common.apiService.getListOfBorrowBook().enqueue(new Callback<List<MuonTra>>() {
            @Override
            public void onResponse(Call<List<MuonTra>> call, Response<List<MuonTra>> response) {
                if(response.isSuccessful())
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<MuonTra>> call, Throwable t) {

            }
        });
        return data;
    }
    public void insertCallCard(JsonObject muontra)
    {
         Common.apiService.insertCallCard(muontra).enqueue(new Callback<MuonTra>() {
             @Override
             public void onResponse(Call<MuonTra> call, Response<MuonTra> response) {
                 if(response.isSuccessful() && response.body()!=null)
                 {
                     Log.d("Post respone","MuonTra: " + response.body());
                 }
                 else {
                     Log.d("Post error", "Error: " + response.code());
                 }
             }

             @Override
             public void onFailure(Call<MuonTra> call, Throwable t) {

             }
         });
    }
}
