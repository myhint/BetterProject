package com.ad.wubo.betterproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ad.wubo.betterproject.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/28.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int FIRST_TYPE = 0;
    private static final int SECOND_TYPE = 1;

    private Context context;
    private ArrayList<String> addressLists;
    private LayoutInflater inflater;

    public MyAdapter(Context context,ArrayList<String> addressLists) {
        this.context = context;
        this.addressLists = addressLists;
        inflater = LayoutInflater.from(context);
    }

    //点击接口
    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return FIRST_TYPE;
        }
        if (position >= 1 && position < 3) {
            return SECOND_TYPE;
        }
        return 0;   //默认返回值
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
       if (viewType == FIRST_TYPE){
           view = inflater.inflate(R.layout.item_one,parent,false);
           return new FirstHolder(view);
       }else {
           view = inflater.inflate(R.layout.item_two,parent,false);
           return new SecondHolder(view);
       }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FirstHolder){
            ((FirstHolder) holder).tv_one.setText(addressLists.get(position));
        }
        if (holder instanceof SecondHolder){
            ((SecondHolder) holder).tv_two.setText(addressLists.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return addressLists.size();
    }

    class FirstHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_one;
        LinearLayout rootView_one;

        public FirstHolder(View itemView) {
            super(itemView);
            tv_one = (TextView) itemView.findViewById(R.id.tv_one);
            rootView_one = (LinearLayout) itemView.findViewById(R.id.rootView_one);
            rootView_one.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null){
                clickListener.onClick(itemView,getAdapterPosition());
            }
        }
    }

    class SecondHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_two;
        LinearLayout rootView_two;

        public SecondHolder(View itemView) {
            super(itemView);
            tv_two = (TextView) itemView.findViewById(R.id.tv_two);
            rootView_two = (LinearLayout) itemView.findViewById(R.id.rootView_two);
            rootView_two.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null){
                clickListener.onClick(itemView,getAdapterPosition());
            }
        }
    }

}
