package com.ad.wubo.betterproject.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ad.wubo.betterproject.DividerItemDecoration;
import com.ad.wubo.betterproject.MainActivity;
import com.ad.wubo.betterproject.MessageEvent;
import com.ad.wubo.betterproject.R;
import com.ad.wubo.betterproject.adapter.MyAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private ArrayList<String> addressLists;
    private View view;
    private int pos;
    private String addr;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);   //注册


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getAddr(MessageEvent messageEvent) {
        addr = messageEvent.getAddress();
        addressLists.set(pos,addr);

        myAdapter.notifyItemChanged(pos);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initData();
        view = inflater.inflate(R.layout.fragment_main, container, false);
        initView();

        return view;
    }

    protected void initData() {
        addressLists = new ArrayList<>();
        for (int i = 0; i < 3; i++) {

            addressLists.add("地址"+i);
        }
    }

    protected void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerView);
        myAdapter = new MyAdapter(getActivity(),addressLists);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(myAdapter);



        myAdapter.setClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                pos = position;

                Toast.makeText(getActivity(), "item" + position, Toast.LENGTH_SHORT).show();

                if (MainActivity.gMapFragment != MainActivity.temp) {
                    if (!MainActivity.gMapFragment.isAdded()) {
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.hide(MainActivity.mainFragment);
                        ft.add(R.id.id_container, MainActivity.gMapFragment);
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                    MainActivity.temp = MainActivity.gMapFragment;
                }
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);   //反注册
    }

}
