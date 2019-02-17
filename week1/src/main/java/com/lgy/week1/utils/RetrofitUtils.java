package com.lgy.week1.utils;

import android.util.Log;

import com.lgy.week1.api.Api;
import com.lgy.week1.api.NameApi;
import com.lgy.week1.bean.ByNameBean;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static RetrofitUtils instance;
    public Interceptor setInterceptor(){
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Log.i("++++拦截前啊++++", "intercept: "+request);
                Response proceed = chain.proceed(request);
                Log.i("++++拦截后啊++++", "intercept: "+proceed);
                return null;
            }
        };
        return  interceptor;
    }
    private RetrofitUtils() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(setInterceptor())
                .readTimeout(2000,TimeUnit.SECONDS)
                .connectTimeout(2000,TimeUnit.SECONDS)
                .writeTimeout(2000,TimeUnit.SECONDS);

    }
    /**
     * 单例模式
     */
    public static RetrofitUtils getInstance(){
        if (instance==null){
            synchronized (RetrofitUtils.class){
                if (instance==null){
                    instance=new RetrofitUtils();
                }
            }
        }
        return instance;
    }

    public void doGet(String url,String parm,Class clazz, final SetRetrofts setRetrofts){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASEUREL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NameApi nameApi = retrofit.create(NameApi.class);
        nameApi.get(url).enqueue(new Callback<ByNameBean>() {
            @Override
            public void onResponse(Call<ByNameBean> call, retrofit2.Response<ByNameBean> response) {
                setRetrofts.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ByNameBean> call, Throwable t) {

            }
        });

    }

    public interface SetRetrofts {
        void onSuccess(Object data);
    }
}
