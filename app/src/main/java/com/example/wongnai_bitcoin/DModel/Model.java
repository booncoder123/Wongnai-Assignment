package com.example.wongnai_bitcoin.DModel;

import com.example.wongnai_bitcoin.DController.Controller;
import com.example.wongnai_bitcoin.DController.IController;
import com.example.wongnai_bitcoin.DModel.POJO.API;
import com.example.wongnai_bitcoin.DModel.POJO.Data;
import com.example.wongnai_bitcoin.DModel.POJO.coin;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model  implements IModel{
     private Retrofit retrofit;
     private API api;
     private Call<Data> jasonAPICall;
    private  List<coin> coins;
    public  IController controller;

    public Model(Controller controller) {
                this.controller = controller;
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.coinranking.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                API api = retrofit.create(API.class);
                Call<Data> call = api.getData();
                call.enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        coins = response.body().getData().getCoins();
                        if(response.isSuccessful()){
                                setCoins(coins);
                                controller.UiInvoke();
                            }
                        }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        System.out.println("Error");
                    }
                });

    }
    private void setCoins(List<coin> data) {
        coins = data;
    }
    @Override
    public List<coin> getCoins() {
        return coins;
    }

}
