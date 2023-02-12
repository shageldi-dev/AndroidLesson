package com.shageldi.androidlessons.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shageldi.androidlessons.Common.APIClient;
import com.shageldi.androidlessons.Common.ApiInterface;
import com.shageldi.androidlessons.Common.Utils;
import com.shageldi.androidlessons.Fragment.DrawerFragment;
import com.shageldi.androidlessons.Model.Api.GetWeather;
import com.shageldi.androidlessons.Model.Api.Weather;
import com.shageldi.androidlessons.R;
import com.shageldi.androidlessons.databinding.ActivityDrawerLessonBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrawerLesson extends AppCompatActivity {
    private ActivityDrawerLessonBinding binding;
    private Context context=this;
    private float oldOffset=0f;
    private String appId="c33f1fa73169b9c1cf4186a69375fb9e";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDrawerLessonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListener();
        Utils.setWebView(binding.webView,"<marquee><h1>Shageldi</h1></marquee>",DrawerLesson.this);

        ApiInterface apiInterface= APIClient.getClient().create(ApiInterface.class);
//        Call<ResponseBody> call=apiInterface.getWords("ç");
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if(response.isSuccessful()){
//                    try {
//                        Log.e("DATA",response.body().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    Toast.makeText(DrawerLesson.this, ""+response.code(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(DrawerLesson.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
        // enqueue  async
        // execute  sync

        // rec   |  get request
        Map<String,Object> query=new HashMap<>();
        query.put("q","Moscow");
        query.put("appid",appId);
        query.put("units","metric");

        long milliseconds = 1676170842;
        int seconds = (int) (milliseconds / 1000) % 60 ;
        int minutes = (int) ((milliseconds / (1000*60)) % 60);
        int hours   = (int) ((milliseconds / (1000*60*60)) % 24);

        Log.e("Sunrise",seconds+":"+minutes+":"+hours);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
                ArrayList<String> cities=Utils.getCities(context);
                for(String city:cities){
                    Log.e("City",city);
                }
//            }
//        },5000);

        Call<GetWeather> call=apiInterface.getWeather(query);
        call.enqueue(new Callback<GetWeather>() {
            @Override
            public void onResponse(Call<GetWeather> call, Response<GetWeather> response) {
                if(response.isSuccessful() && response.body()!=null){
                    GetWeather weather = response.body();
                    Log.e("Temp",weather.getMain().getTemp()+"");
                    Glide.with(context)
                            .load(Utils.getWeatherIcon(weather.getWeather().get(0).getIcon()))
                            .into(binding.icon);


                } else {
                    Toast.makeText(DrawerLesson.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetWeather> call, Throwable t) {
                Toast.makeText(DrawerLesson.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // ?q=Ashgabat&appid=c33f1fa73169b9c1cf4186a69375fb9e&units=metric

    private void setListener() {
        binding.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startAnim();
                binding.drawer.openDrawer(GravityCompat.START);
            }
        });
        binding.drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
//                if(oldOffset>slideOffset){
//                    binding.menu.setImageDrawable(getResources().getDrawable(R.drawable.avd_drawer_open));
//                    startAnim();
//                } else {
//                    binding.menu.setImageDrawable(getResources().getDrawable(R.drawable.avd_drawer_close));
//                }
//                oldOffset=slideOffset;
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                binding.menu.setImageDrawable(getResources().getDrawable(R.drawable.avd_drawer_open));
                startAnim();
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                binding.menu.setImageDrawable(getResources().getDrawable(R.drawable.avd_drawer_close));
                startAnim();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        Fragment fragment=DrawerFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.content,fragment ,DrawerFragment.class.getSimpleName()).commit();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawer.openDrawer(GravityCompat.START);
            }
        });
    }

    private void startAnim() {
        Animatable animatable=(Animatable) binding.menu.getDrawable();
        animatable.start();
    }
}