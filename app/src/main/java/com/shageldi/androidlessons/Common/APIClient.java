package com.shageldi.androidlessons.Common;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;

    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(1800, TimeUnit.SECONDS)
            .writeTimeout(1800,TimeUnit.SECONDS)
            .connectTimeout(1800, TimeUnit.SECONDS)
            .build();


    public static Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}