package com.shageldi.androidlessons.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.shageldi.androidlessons.Common.Utils;
import com.shageldi.androidlessons.Model.AppDatabase;
import com.shageldi.androidlessons.Model.Users;
import com.shageldi.androidlessons.R;
import com.shageldi.androidlessons.databinding.ActivityLocalStorageBinding;

import java.util.List;

public class LocalStorage extends AppCompatActivity {
    private Context context=this;
    private ActivityLocalStorageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLocalStorageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppDatabase appDatabase=Utils.getRoomDB(context);


        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=binding.username.getText().toString();
                if(username.trim().isEmpty()){
                    Toast.makeText(context, "Enter username!", Toast.LENGTH_SHORT).show();
                    return;
                }


                appDatabase.userDAO().insertAll(new Users(username,binding.phone.getText().toString()));
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Users> users=appDatabase.userDAO().getAll();
                String username=binding.username.getText().toString();
                String phone=binding.phone.getText().toString();
                boolean isLogin=false;
                for(Users user:users){
                    if(user.getPhoneNumber().equals(phone) && user.getUsername().equals(username))
                        isLogin=true;
                }



                if(isLogin){
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=binding.username.getText().toString();
                String phone=binding.phone.getText().toString();
                appDatabase.userDAO().deleteByUsernamePhone(username,phone);
            }
        });

        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=binding.username.getText().toString();
                String phone=binding.phone.getText().toString();
                String oldUsername="hello";

                appDatabase.userDAO().update(username,phone,oldUsername);
            }
        });
    }
}