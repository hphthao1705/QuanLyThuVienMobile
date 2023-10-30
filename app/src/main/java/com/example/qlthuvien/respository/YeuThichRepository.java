package com.example.qlthuvien.respository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qlthuvien.data.model.Loai;
import com.example.qlthuvien.data.model.YeuThich;
import com.example.qlthuvien.data.remote.Common;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YeuThichRepository {
    public LiveData<List<YeuThich>> loadFavorites()
    {
        MutableLiveData<List<YeuThich>> data = new MutableLiveData<>();
        Common.apiService.getFavorites().enqueue(new Callback<List<YeuThich>>() {
            @Override
            public void onResponse(Call<List<YeuThich>> call, Response<List<YeuThich>> response) {
                if(response.isSuccessful())
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<YeuThich>> call, Throwable t) {

            }
        });
        return data;
    }
}
