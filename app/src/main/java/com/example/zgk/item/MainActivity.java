package com.example.zgk.item;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView_project;
    private ReAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView_project = findViewById(R.id.re);
        adapter = new ReAdapter();
        adapter.setData(this, 28);//设置数据
        adapter.setHasStableIds(true);//设置唯一下标  使用过RecyclerView的应该都知道。这个布局数据在刷新时会错乱，添上这个设置+重写适配器的getItemId()方法即可解决
        recyclerView_project.setLayoutManager(new GridLayoutManager(this, 14, GridLayoutManager.VERTICAL, false));//网格布局
        recyclerView_project.setAdapter(adapter);//设置适配器
        //适配器的点击事件
        adapter.setItemOnClick(item -> {
            //回传点击的下标 得到要改变的Item
            adapter.setItem(item);
            //刷新视图
            adapter.notifyDataSetChanged();

        });
    }


}