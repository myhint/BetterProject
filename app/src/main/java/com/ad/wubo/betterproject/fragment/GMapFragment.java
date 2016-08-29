package com.ad.wubo.betterproject.fragment;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ad.wubo.betterproject.MainActivity;
import com.ad.wubo.betterproject.MessageEvent;
import com.ad.wubo.betterproject.R;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GMapFragment extends Fragment {

    MapView mMapView = null;
    AMap aMap = null;

    public GMapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gmap, container, false);

        mMapView = (MapView) view.findViewById(R.id.map);
        aMap = mMapView.getMap();

        mMapView.onCreate(savedInstanceState);

        //设置默认显示的地图中心点
        LatLng latLng = new LatLng(23.05, 113.75);   //选定东莞为地图中心点
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));

        //获取地图点击处的地址信息
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                Geocoder geocoder = new Geocoder(getContext());
                try {
                    List<Address> address = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 3);
                    System.out.println(address.toString());

                    String currentLoc = "";   //保存当前地址信息

                    Address addr = address.get(0);
                    currentLoc += addr.getAdminArea();
                    currentLoc += addr.getLocality();
                    currentLoc += "市辖区";
                    currentLoc += addr.getThoroughfare();

                    EventBus.getDefault().post(new MessageEvent(currentLoc));

                    Toast.makeText(getContext(), currentLoc, Toast.LENGTH_SHORT).show();

                    FragmentTransaction ft = MainActivity.fm.beginTransaction();
//                    ft.hide(MainActivity.gMapFragment);
//                    ft.show(MainActivity.mainFragment);
                    ft.remove(MainActivity.gMapFragment);
                    ft.show(MainActivity.mainFragment);
                    ft.commit();

                    MainActivity.temp = MainActivity.mainFragment;

                } catch (IOException e) {
                    e.printStackTrace();

                }

            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mMapView.onSaveInstanceState(outState);
    }
}
