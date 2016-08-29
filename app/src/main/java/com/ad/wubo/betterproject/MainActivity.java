package com.ad.wubo.betterproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ad.wubo.betterproject.fragment.GMapFragment;
import com.ad.wubo.betterproject.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    public static Fragment temp;
    public static MainFragment mainFragment;
    public static GMapFragment gMapFragment;
    public static FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainFragment = new MainFragment();
        gMapFragment = new GMapFragment();



        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.id_container,mainFragment);
        ft.commit();

        temp = mainFragment;

        setContentView(R.layout.activity_main);

    }

}
