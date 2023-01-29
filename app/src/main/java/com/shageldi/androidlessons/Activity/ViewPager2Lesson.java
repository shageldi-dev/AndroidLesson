package com.shageldi.androidlessons.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.shageldi.androidlessons.Adapter.BannerAdapter;
import com.shageldi.androidlessons.Model.BannerItem;
import com.shageldi.androidlessons.R;
import com.shageldi.androidlessons.databinding.ActivityViewPager2LessonBinding;

import java.util.ArrayList;
import java.util.Random;

public class ViewPager2Lesson extends AppCompatActivity {
    private Context context=this;
    private ActivityViewPager2LessonBinding binding;
    private ArrayList<BannerItem> items=new ArrayList<>();
    private Handler sliderHandler = new Handler();
    private static final int BANNER_DELAY=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityViewPager2LessonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setBanner();
    }

    private void setBanner() {
        items.clear();
        for(int i=1;i<=5;i++){
            items.add(new BannerItem(getRandomImage(i),i-1));
        }
        binding.slider.setAdapter(new BannerAdapter(context,items,binding.slider));
        binding.slider.setOffscreenPageLimit(1);
        binding.indicator.setNoOfPages(items.size());
//        binding.slider.setUserInputEnabled(false);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        binding.slider.setPageTransformer(compositePageTransformer);
        binding.slider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.indicator.onPageChange(items.get(position).getPos());
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,BANNER_DELAY);
            }
        });
    }

    private Runnable sliderRunnable=new Runnable() {
        @Override
        public void run() {
            binding.slider.setCurrentItem(binding.slider.getCurrentItem()+1);
        }
    };

    private String getRandomImage(int r){
        return "https://picsum.photos/id/"+r+"/200/300";
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable,BANNER_DELAY);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }
}