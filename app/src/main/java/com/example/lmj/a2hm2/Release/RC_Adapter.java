package com.example.lmj.a2hm2.Release;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lmj.a2hm2.R;

import java.util.ArrayList;

/**
 * Created by lmj on 2016/9/17.
 */
public class RC_Adapter extends RecyclerView.Adapter<RC_Adapter.MyViewHolder> {
    private ArrayList<String> release_Classify_ListView;
    private Context mContext;
    public RC_Adapter (Context context, ArrayList<String> release_Classify_ListView){
        this. mContext=context;
        this. release_Classify_ListView=release_Classify_ListView;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater. from(mContext).inflate(R.layout. rc_itemview,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        System.out.println(release_Classify_ListView.size());
        holder.tv_release_classify_listView_item.setText(release_Classify_ListView.get(position));
    }

    @Override
    public int getItemCount() {
        return release_Classify_ListView.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_release_classify_listView_item;
        public MyViewHolder(View view) {
            super(view);
            tv_release_classify_listView_item= (TextView) view.findViewById(R.id.tv_release_classify_listView_item);
        }
    }
}