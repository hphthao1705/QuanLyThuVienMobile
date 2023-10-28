package com.example.qlthuvien.data.remote;

import com.example.qlthuvien.utils.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Common {
    public static final APIService apiService = new Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(APIService.class);

}
