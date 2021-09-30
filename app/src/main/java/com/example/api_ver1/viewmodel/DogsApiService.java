package com.example.api_ver1.viewmodel;

import com.example.api_ver1.model.DogsApi;
import com.example.api_ver1.model.DogBreed;

import java.util.ArrayList;
import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DogsApiService {
    private static final  String BASE_URL = "https://raw.githubusercontent.com";
    private DogsApi api;
    public DogsApiService(){
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(DogsApi.class);
    }

    public Call<ArrayList<DogBreed>> getDogs(){
         return api.getDogs();
    }
}
