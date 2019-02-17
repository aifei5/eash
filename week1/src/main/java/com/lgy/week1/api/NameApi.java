package com.lgy.week1.api;

import com.lgy.week1.bean.ByNameBean;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NameApi {
    @GET
    Call<ByNameBean> get(@Url String url);
}
