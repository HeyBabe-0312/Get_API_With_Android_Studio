package com.example.api_ver1.model;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DogsApi {
    @GET("DevTides/DogsApi/master/dogs.json")
    Call<ArrayList<DogBreed>> getDogs();
}
