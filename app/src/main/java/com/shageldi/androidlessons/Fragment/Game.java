package com.shageldi.androidlessons.Fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.shageldi.androidlessons.Adapter.GameAdapter;
import com.shageldi.androidlessons.Common.Fonts;
import com.shageldi.androidlessons.Common.Utils;
import com.shageldi.androidlessons.MainActivity;
import com.shageldi.androidlessons.Model.GameModel;
import com.shageldi.androidlessons.R;
import com.shageldi.androidlessons.databinding.FragmentGameBinding;
import com.shageldi.androidlessons.databinding.GameItemBinding;

import java.util.ArrayList;
import java.util.Random;


public class Game extends Fragment {

    private ArrayList<GameModel> offlineGames = new ArrayList<>();
    private ArrayList<GameModel> suggest = new ArrayList<>();
    private ArrayList<GameModel> recommended = new ArrayList<>();
    private Context context;
    private FragmentGameBinding binding;

    public Game() {
    }

    public static Game newInstance() {
        Game fragment = new Game();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getContext();
        binding = FragmentGameBinding.inflate(inflater, container, false);
        setFonts();
        initArrays();
        binding.myTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Suggestion suggestion=Suggestion.newInstance("5");
                Utils.hideAdd(suggestion,suggestion.getClass().getSimpleName(),getFragmentManager(),R.id.content);
                MainActivity.firstFragment=suggestion;
            }
        });
        return binding.getRoot();
    }

    private void setFonts() {
    }

    private void initArrays() {
        offlineGames.clear();

        for (int i = 0; i <= 50; i++) {
            offlineGames.add(getRandomGame());
            suggest.add(getRandomGame());
            recommended.add(getRandomGame());
        }

        binding.offlineGamesRec.setAdapter(new GameAdapter(context, offlineGames));
        binding.offlineGamesRec.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));

        binding.sugRec.setAdapter(new GameAdapter(context, suggest));
        binding.sugRec.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));

//        binding.recoRec.setAdapter(new GameAdapter(context, recommended));
//        binding.recoRec.setLayoutManager(new GridLayoutManager(context,2,RecyclerView.HORIZONTAL,false));
//

        try{
            binding.recoRec.setAdapter(new GameAdapter(context, recommended));
            GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position % 3 == 0)
                        return 3;
                    else if (position % 4 == 0)
                        return 1;
                    else
                        return 2;
                }
            });
            binding.recoRec.setLayoutManager(layoutManager);
        } catch (Exception ex){
            ex.printStackTrace();
        }
//        binding.recoRec.setHasFixedSize(false);


    }


    private GameModel getRandomGame() {
        return new GameModel(
                getRandom(1000),
                "https://picsum.photos/id/" + getRandom(500) + "/200/300",
                "Game-" + getRandom(100),
                "Category-" + getRandom(100),
                Math.ceil(new Random().nextDouble() + getRandom(5)));
    }

    private int getRandom(int length) {
        return new Random().nextInt(length);
    }


}