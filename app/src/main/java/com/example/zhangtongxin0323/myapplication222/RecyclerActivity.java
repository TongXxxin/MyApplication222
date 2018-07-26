package com.example.zhangtongxin0323.myapplication222;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * Created by zhangtongxin0323 on 2018/7/15.
 */


public class RecyclerActivity extends Activity {
    private static final String TAG =RecyclerActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter() {


            @Nullable
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@Nullable ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_base_use, parent, false);
                VH vh = new VH(v);
                return vh;
            }


            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                VH vh = (VH) holder;
                vh.mText.setText("aaaaa" + position);
            }


            @Override
            public int getItemCount() {
                return 20;//列表长度
            }

            class VH extends RecyclerView.ViewHolder {
                TextView mText;

                VH(View itemView) {
                    super(itemView);
                    mText = itemView.findViewById(R.id.item_tx);

                }
            }
        });
    }
}












