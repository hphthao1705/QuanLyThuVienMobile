package com.example.qlthuvien.respository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qlthuvien.data.model.Loai;
import com.example.qlthuvien.data.model.TaiLieu;
import com.example.qlthuvien.data.remote.Common;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoaiRepository {

    public LiveData<List<Loai>> loadCategories()
    {
        MutableLiveData<List<Loai>> data = new MutableLiveData<>();
        Common.apiService.getCategories().enqueue(new Callback<List<Loai>>() {
            @Override
            public void onResponse(Call<List<Loai>> call, Response<List<Loai>> response) {
                if(response.isSuccessful())
                {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Loai>> call, Throwable t) {

            }
        });
        return data;
    }
}
