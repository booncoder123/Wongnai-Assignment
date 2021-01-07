package com.example.wongnai_bitcoin.DController;

import com.example.wongnai_bitcoin.DModel.POJO.coin;

import java.util.List;

public interface IController {
    List<coin> GetAllCoin();
    void UiInvoke();
}
