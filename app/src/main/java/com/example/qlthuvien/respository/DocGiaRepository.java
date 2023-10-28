package com.example.qlthuvien.respository;

import com.example.qlthuvien.data.model.DocGia;
import com.example.qlthuvien.data.remote.APIService;
import com.example.qlthuvien.utils.Constant;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DocGiaRepository {
    APIService apiService = new Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(APIService.class);

    public Flowable<DocGia> loadUser()
    {
        try{
            return apiService.getUser();
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
        return null;
    }
}
