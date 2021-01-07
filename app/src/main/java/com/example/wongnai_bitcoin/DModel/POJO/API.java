package com.example.wongnai_bitcoin.DModel.POJO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("v1/public/coins")
    Call<Data> getData();
}
