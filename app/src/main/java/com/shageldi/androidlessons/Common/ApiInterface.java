package com.shageldi.androidlessons.Common;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("sozluk/harp/{letter}")
    Call<ResponseBody> getWords(@Path("letter") String letter);
}
