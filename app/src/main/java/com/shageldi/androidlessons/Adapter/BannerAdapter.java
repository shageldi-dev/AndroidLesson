package com.shageldi.androidlessons.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.shageldi.androidlessons.Common.Utils;
import com.shageldi.androidlessons.Model.BannerItem;
import com.shageldi.androidlessons.R;

import java.util.ArrayList;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<BannerItem> items=new ArrayList<>();
    private ViewPager2 viewPager2;

    public BannerAdapter(Context context, ArrayList<BannerItem> items,ViewPager2 viewPager2) {
        this.context = context;
        this.items = items;
        this.viewPager2=viewPager2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.banner_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(items.get(position).getImage())
                .placeholder(Utils.getShimmer())
                .into(holder.image);
        if(position==items.size()-2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
        }
    }

    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            items.addAll(items);
            notifyDataSetChanged();
        }
    };
}
