package com.example.qlthuvien.respository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qlthuvien.data.model.DocGia;
import com.example.qlthuvien.data.model.TaiLieu;
import com.example.qlthuvien.data.remote.Common;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaiLieuRepository {
    public LiveData<List<TaiLieu>> loadBook()
    {
        MutableLiveData<List<TaiLieu>> data = new MutableLiveData<>();
        Common.apiService.getBook().enqueue(new Callback<List<TaiLieu>>() {
            @Override
            public void onResponse(Call<List<TaiLieu>> call, Response<List<TaiLieu>> response) {
                if(response.isSuccessful())
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<TaiLieu>> call, Throwable t) {

            }
        });
        return data;
    }
    public LiveData<TaiLieu> loadDetailOfBook(int id_tailieu)
    {
        MutableLiveData<TaiLieu> data = new MutableLiveData<>();
        Common.apiService.getDetailOfBook(id_tailieu).enqueue(new Callback<TaiLieu>() {
            @Override
            public void onResponse(Call<TaiLieu> call, Response<TaiLieu> response) {
                if(response.isSuccessful())
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TaiLieu> call, Throwable t) {

            }
        });
        return data;
    }
}