package com.shageldi.androidlessons.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shageldi.androidlessons.R;


public class Suggestion extends Fragment {


    public Suggestion() {
        // Required empty public constructor
    }


    public static Suggestion newInstance(String id) {
        Suggestion fragment = new Suggestion();
        Bundle args = new Bundle();
        args.putString("id",id);
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
        getArguments().getString("id");
        return inflater.inflate(R.layout.fragment_suggestion, container, false);
    }
}