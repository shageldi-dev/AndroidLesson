package com.shageldi.androidlessons.Common;

import com.shageldi.androidlessons.Model.Api.GetWeather;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {
//    @GET("sozluk/harp/{letter}")
//    Call<ResponseBody> getWords(@Path("letter") String letter);

    // @Query("q") String city, @Query("appid") String appId,@Query("units") String units
    @GET("data/2.5/weather?")
    Call<GetWeather> getWeather(@QueryMap() Map<String, Object> query);

}
