package com.shageldi.androidlessons.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shageldi.androidlessons.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link App#newInstance} factory method to
 * create an instance of this fragment.
 */
public class App extends Fragment {



    public App() {
        // Required empty public constructor
    }
    public static App newInstance() {
        App fragment = new App();
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
        return inflater.inflate(R.layout.fragment_app, container, false);
    }
}