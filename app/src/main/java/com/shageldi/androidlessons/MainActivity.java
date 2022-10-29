package com.shageldi.androidlessons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.shageldi.androidlessons.Common.Utils;
import com.shageldi.androidlessons.Fragment.App;
import com.shageldi.androidlessons.Fragment.Book;
import com.shageldi.androidlessons.Fragment.Game;
import com.shageldi.androidlessons.Fragment.Suggestion;
import com.shageldi.androidlessons.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Context context=this;
    public static Fragment firstFragment=Game.newInstance();
    public static Fragment secondFragment=App.newInstance();
    public static Fragment thirdFragment=Book.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setListener();
    }

    private void setListener() {
        fragmentChanger(new Game());
        binding.bottomBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.game:
                        fragmentChanger(firstFragment);
                        break;
                    case R.id.app:
                        fragmentChanger(secondFragment);
                        break;
                    case R.id.book:
                        fragmentChanger(thirdFragment);
                        break;
                }
                return true;
            }
        });
    }

    private void fragmentChanger(Fragment fragment){
        Utils.hideAdd(fragment,fragment.getClass().getSimpleName(),getSupportFragmentManager(),R.id.content);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onBackPressed() {
        Game game = (Game) getSupportFragmentManager().findFragmentByTag(Game.class.getSimpleName());
        App app = (App) getSupportFragmentManager().findFragmentByTag(App.class.getSimpleName());
        Book book = (Book) getSupportFragmentManager().findFragmentByTag(Book.class.getSimpleName());
        Suggestion suggestion = (Suggestion) getSupportFragmentManager().findFragmentByTag(Suggestion.class.getSimpleName());

        if(game!=null && game.isVisible()){
            finish();
        }

        if((app!=null && app.isVisible()) || (book!=null && book.isVisible())){
            binding.bottomBar.setSelectedItemId(R.id.game);
        }

        if(suggestion!=null && suggestion.isVisible()){
            firstFragment=Game.newInstance();
            fragmentChanger(firstFragment);
        }


    }
}