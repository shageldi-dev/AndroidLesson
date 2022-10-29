package com.shageldi.androidlessons.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.shageldi.androidlessons.Common.Utils;
import com.shageldi.androidlessons.Model.GameModel;
import com.shageldi.androidlessons.R;

import java.util.ArrayList;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private Context context;
    private ArrayList<GameModel> arrayList=new ArrayList<>();

    public GameAdapter(Context context, ArrayList<GameModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.game_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GameModel game=arrayList.get(holder.getAdapterPosition());
        Utils.setImage(context,game.getImage(), holder.image);
        Utils.setImage(context,game.getImage(), holder.userImage);
        holder.category.setText(game.getCategory());
        holder.name.setText(game.getName());
        holder.star.setText(game.getStar().toString());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                arrayList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                return false;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.setStar(game.getStar()+0.1);
                arrayList.set(holder.getAdapterPosition(),game);
                notifyItemChanged(holder.getAdapterPosition());
            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView image,userImage;
        private TextView name,category,star;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            star=itemView.findViewById(R.id.star);
            image=itemView.findViewById(R.id.image);
            userImage=itemView.findViewById(R.id.userImage);
            name=itemView.findViewById(R.id.name);
            category=itemView.findViewById(R.id.category);
        }
    }
}
