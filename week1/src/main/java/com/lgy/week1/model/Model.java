package com.lgy.week1.model;

import android.content.Context;
import com.lgy.week1.utils.RetrofitUtils;

public class Model implements IModel {
    Context context;
    @Override
    public void getModel(String url,String param,Class clazz,final SetModel setModel) {
        RetrofitUtils.getInstance().doGet(url, param, clazz, new RetrofitUtils.SetRetrofts() {
            @Override
            public void onSuccess(Object data) {
                setModel.getModelData(data);
            }
        });
    }
}
