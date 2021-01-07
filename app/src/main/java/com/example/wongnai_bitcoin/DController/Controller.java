package com.example.wongnai_bitcoin.DController;

import com.example.wongnai_bitcoin.DModel.POJO.coin;
import com.example.wongnai_bitcoin.DModel.IModel;
import com.example.wongnai_bitcoin.DModel.Model;
import com.example.wongnai_bitcoin.DView.IView;

import java.util.List;

public class Controller implements IController{
    static IView view;
    static IModel model;

    public Controller(IView view) {
        this.view = view;
        this.model = new Model(this);
    }

    public List<coin> GetAllCoin(){ return model.getCoins();
    }

    @Override
    public void UiInvoke() {
        view.ShowDataOnRecycleView();
    }
}
