package com.lgy.week1.model;

public interface IModel {
    void getModel(String url,String param,Class clazz,SetModel setModel);
    public interface SetModel{
        void getModelData(Object o);
    }
}
