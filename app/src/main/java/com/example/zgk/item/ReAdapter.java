package com.example.zgk.item;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by ZGK on 2019/4/25.
 */

public class ReAdapter extends RecyclerView.Adapter<ReAdapter.My> {
    private int i;
    private Context context;
    private itemOnClick itemOnclick;
    private int item;
    //获得点击的下标 由外部传进来进行判断哪个要放大（即点即了哪个或者选中了哪个）
    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    //数据源
    public void setData(Context context, int i) {
        this.context = context;
        this.i = i;
    }

    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new My(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull My my, int i) {
        //随机给控件上色
        TextView tv = my.itemView.findViewById(R.id.speed_tv);
        tv.setBackgroundColor(0xff000000 | new Random().nextInt(0x00ffffff));
        tv.setText(String.valueOf(i));
        if (getItem() == i) {
            //1.1为原来的大小+1的0.1倍放大
            my.itemView.findViewById(R.id.speed_view).setScaleX(1.3f);
            my.itemView.findViewById(R.id.speed_view).setScaleY(1.3f);
        } else {
            //缩小同理   1为布局设定的大小
            my.itemView.findViewById(R.id.speed_view).setScaleX(1f);
            my.itemView.findViewById(R.id.speed_view).setScaleY(1f);
        }
        //item监听
        my.itemView.setOnClickListener(v -> {
            if (itemOnclick != null)
                itemOnclick.OnClick(i);
        });

    }

    //设置回调接口
    public void setItemOnClick(itemOnClick itemOnclick) {
        this.itemOnclick = itemOnclick;
    }

    //点击事件回调出去
    public interface itemOnClick {
        void OnClick(int item);
    }

    //重写方法保证不会数据错乱
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return i;
    }

    class My extends RecyclerView.ViewHolder {
        My(@NonNull View itemView) {
            super(itemView);
        }
    }
}
