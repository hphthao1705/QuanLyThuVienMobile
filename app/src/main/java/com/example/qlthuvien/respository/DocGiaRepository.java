package com.example.qlthuvien.respository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.qlthuvien.data.model.DocGia;
import com.example.qlthuvien.data.remote.APIService;
import com.example.qlthuvien.data.remote.Common;
import com.example.qlthuvien.utils.Constant;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DocGiaRepository {
    public LiveData<List<DocGia>> loadUser()
    {
        MutableLiveData<List<DocGia>> data = new MutableLiveData<>();
        Common.apiService.getUser().enqueue(new Callback<List<DocGia>>() {
            @Override
            public void onResponse(Call<List<DocGia>> call, Response<List<DocGia>> response) {
                if(response.isSuccessful())
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<DocGia>> call, Throwable t) {

            }
        });
        return data;
    }
}
