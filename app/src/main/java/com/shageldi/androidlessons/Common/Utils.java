package com.shageldi.androidlessons.Common;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerDrawable;
import com.shageldi.androidlessons.Model.AppDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Utils {
    public static void setImage(Context context, String image, ImageView imageView){
        Glide.with(context)
                .load(image)
                .into(imageView);
    }

    public static Typeface getFontByName(Context context,String name){
        return Typeface.createFromAsset(context.getAssets(),name);
    }

    public static void hideAdd(Fragment fragment, String tagFragmentName, FragmentManager mFragmentManager, int frame) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        Fragment currentFragment = mFragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragmentTemp = mFragmentManager.findFragmentByTag(tagFragmentName);
        if (fragmentTemp == null) {
            fragmentTemp = fragment;
            fragmentTransaction.add(frame, fragmentTemp, tagFragmentName);
        } else {
            fragmentTransaction.show(fragmentTemp);
        }
        fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNowAllowingStateLoss();
    }

    public static void removeShow(Fragment fragment, String tagFragmentName, FragmentManager mFragmentManager, int frame) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        Fragment currentFragment = mFragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.remove(currentFragment);
        }

        Fragment fragmentTemp = mFragmentManager.findFragmentByTag(tagFragmentName);
        if (fragmentTemp == null) {
            fragmentTemp = fragment;
            fragmentTransaction.add(frame, fragmentTemp, tagFragmentName);
        } else {
            fragmentTransaction.show(fragmentTemp);
        }
        fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNowAllowingStateLoss();
    }

    public static void setPreference(String name, String value, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(name, MODE_PRIVATE).edit();
        editor.putString(name, value);
        editor.apply();
    }

    public static String getSharedPreference(Context context, String name) {
        try{
            SharedPreferences prefs = context.getSharedPreferences(name, MODE_PRIVATE);
            String value = prefs.getString(name, "");
            return value;
        } catch (Exception ex){
            ex.printStackTrace();
            return "";
        }

    }

    public static AppDatabase getRoomDB(Context context){
        return Room.databaseBuilder(context,
                AppDatabase.class, "app_db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    public static ShimmerDrawable getShimmer(){
        Shimmer shimmer = new Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
                .setDuration(1800) // how long the shimmering animation takes to do one full sweep
                .setBaseAlpha(0.7f) //the alpha of the underlying children
                .setHighlightAlpha(0.6f) // the shimmer alpha amount
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setShape(Shimmer.Shape.LINEAR)
                .setAutoStart(true)
                .build();
        ShimmerDrawable shimmerDrawable=new ShimmerDrawable();
        shimmerDrawable.setShimmer(shimmer);
        return shimmerDrawable;
    }

    public static void setWebView(WebView webView, String code, Context context) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        String mode = Utils.getSharedPreference(context, "mode");
        String color = mode.equals("dark") ? "#FFFFFF" : "#000000";

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null) {
                    try{
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        context.startActivity(i);
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
                return true;
            }
        });

        webView.getSettings().setUseWideViewPort(true);
        webView.setWebChromeClient(new WebChromeClient());
        String margin=code.startsWith("<p>")?"margin-top:-20px;":"";

        String html = "<!DOCTYPE HTML>" +
                "<head>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "</head>" +
                "<body style='color:" + color + ";max-width:100%;'>" +
                "<div style='overflow-x: hidden;max-width:100%;width:100%;"+margin+"'>" +
                code +
                "</div>" +
                "</body>" +
                "</html>";
        webView.loadDataWithBaseURL(null, html, null, "UTF-8", null);
    }

    public static String getWeatherIcon(String icon){
        return "http://openweathermap.org/img/wn/"+icon+"@2x.png";
    }

    public static ArrayList<String> getCities(Context context){
        BufferedReader reader;
        ArrayList<String> temp=new ArrayList<>();
        try{
            final InputStream file = context.getAssets().open("citys.txt");
            reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            while(line != null){
                Log.d("StackOverflow", line);
                line = reader.readLine();
                if(line!=null && !line.trim().isEmpty()){
                    temp.add(line);
                }
            }
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
        return temp;
    }
}
