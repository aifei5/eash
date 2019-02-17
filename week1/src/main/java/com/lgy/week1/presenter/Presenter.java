package com.lgy.week1.presenter;

import com.lgy.week1.MainActivity;
import com.lgy.week1.bean.ByNameBean;
import com.lgy.week1.model.IModel;
import com.lgy.week1.model.Model;

public class Presenter implements IPresenter{
    MainActivity mView;
    private final Model model;

    public Presenter(MainActivity mView) {
        this.mView=mView;
        model = new Model();
    }

    @Override
    public void getPre(String url,String param,Class clazz) {
        model.getModel(url, param, clazz, new IModel.SetModel() {
            @Override
            public void getModelData(Object o) {
                mView.getView(o);
            }
        });
    }
}
