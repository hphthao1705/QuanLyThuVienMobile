package com.example.qlthuvien.respository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qlthuvien.data.model.Loai;
import com.example.qlthuvien.data.model.NhaXuatBan;
import com.example.qlthuvien.data.remote.Common;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NhaXuatBanRepository  {
    public LiveData<NhaXuatBan> loadPulisher(int id_nxb)
    {
        MutableLiveData<NhaXuatBan> data = new MutableLiveData<>();
        Common.apiService.getPublisher(id_nxb).enqueue(new Callback<NhaXuatBan>() {

            @Override
            public void onResponse(Call<NhaXuatBan> call, Response<NhaXuatBan> response) {
                if(response.isSuccessful())
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NhaXuatBan> call, Throwable t) {

            }
        });
        return data;
    }
}
