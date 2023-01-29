package com.shageldi.androidlessons;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.shageldi.androidlessons.Activity.Second;
import com.shageldi.androidlessons.Common.Utils;
import com.shageldi.androidlessons.Fragment.App;
import com.shageldi.androidlessons.Fragment.Book;
import com.shageldi.androidlessons.Fragment.Game;
import com.shageldi.androidlessons.Fragment.Suggestion;
import com.shageldi.androidlessons.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Context context=this;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setListener();


        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            if(bundle.get("product_id")!=null && !bundle.get("product_id").toString().trim().isEmpty()){
                Toast.makeText(context, ""+bundle.get("product_id"), Toast.LENGTH_SHORT).show();
            }
        }

        db.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Toast.makeText(context, ""+task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("FIRESTORE_DATA", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("FIRESTORE_ERROR", "Error getting documents.", task.getException());
                        }
                    }
                });


    }

    @Override
    protected void onStart() {
        super.onStart();
        db.collection("products").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    Toast.makeText(context, "Error while getting data", Toast.LENGTH_SHORT).show();
                } else {
                    for(DocumentSnapshot snapshot:value.getDocuments()){
                        Log.e("SNAPSHOT",snapshot.getId());
                    }

                }
            }
        });
    }

    private void setListener() {
        Fade fade = new Fade();
        View decor = getWindow().getDecorView();

        // below 3 lines of code is to exclude
        // action bar,title bar and navigation
        // bar from animation.
        fade.excludeTarget(decor.findViewById(R.id.image), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);

        // we are adding fade animation
        // for enter transition.
        getWindow().setEnterTransition(fade);

        // we are also setting fade
        // animation for exit transition.
        getWindow().setExitTransition(fade);


        // setting on click listener for our imageview.
        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on image click we are opening new activity
                // and adding animation between this two activities.
                Intent intent = new Intent(MainActivity.this, Second.class);
                // below method is used to make scene transition
                // and adding fade animation in it.
                Pair<View, String> p1 = Pair.create((View)binding.image, "my_image");
                Pair<View, String> p2 = Pair.create((View)binding.text, "my_text");
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, p1, p2);
                // starting our activity with below method.
                startActivity(intent, options.toBundle());
            }
        });
    }


}