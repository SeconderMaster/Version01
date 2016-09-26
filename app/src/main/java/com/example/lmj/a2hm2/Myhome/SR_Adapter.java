package com.example.lmj.a2hm2.Myhome;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lmj.a2hm2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lmj on 2016/9/11.
 */
public class SR_Adapter extends RecyclerView.Adapter<SR_Adapter.MyViewHolder> {
    private ArrayList<Product> productArrayList;
    private Context mContext;
    public SR_Adapter (Context context, ArrayList<Product> productArrayList){
        this. mContext=context;
        this. productArrayList=productArrayList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater. from(mContext).inflate(R.layout. sr_itemview,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        System.out.println(productArrayList.size());
        Picasso.with(mContext).load(productArrayList.get(position).getProductImage()).into(holder.image);
//        holder.image.setImageResource(productArrayList.get(position).getProductImage());
        holder.name.setText(productArrayList.get(position).getProductName());
        holder.value.setText(productArrayList.get(position).getProductValue());
        holder.place.setText(productArrayList.get(position).getProductPlace());
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,value,place;
        ImageView image;
        public MyViewHolder(View view) {
            super(view);
            image= (ImageView) view.findViewById(R.id.productImage);
            name= (TextView) view.findViewById(R.id.productName);
            value= (TextView) view.findViewById(R.id.productValue);
            place= (TextView) view.findViewById(R.id.productPlace);
        }
    }
}
