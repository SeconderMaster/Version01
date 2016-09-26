package com.example.lmj.a2hm2.Release;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.lmj.a2hm2.R;
import com.example.lmj.a2hm2.initB;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

import java.util.ArrayList;

/**
 * Created by lmj on 2016/9/21.
 */
public class ImageAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> ArrayList_imageUrls;
    ArrayList<String> ArrayList_imageUrls_selecd;
    private Handler mHandler;
    private GridView mGV;
    public ImageAdapter(GridView GV,Context context, ArrayList<String> ArrayList_imageUrls,
                        ArrayList<String> select_imageUrls,Handler mhandler){
        this.mGV=GV;
        this.context=context;
        this.ArrayList_imageUrls=ArrayList_imageUrls;
        this.ArrayList_imageUrls_selecd=select_imageUrls;
        this.mHandler=mhandler;
    }
    @Override
    public int getCount() {
        return ArrayList_imageUrls.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
          final ViewHolder mviewHolde;
        if (convertView==null){
            mviewHolde=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.select_picture_gridview_item,null);
            mviewHolde.img_view= (ImageView) convertView.findViewById(R.id.img_view);
            mviewHolde.img_selected= (ImageView) convertView.findViewById(R.id.img_select);
            convertView.setTag(mviewHolde);
        }else {
            mviewHolde= (ViewHolder) convertView.getTag();
        }
        new initB().imageLoader.displayImage(ImageDownloader.Scheme.FILE.wrap(ArrayList_imageUrls.get(position)),
                mviewHolde.img_view,new initB().select_picture_options);
        mviewHolde.img_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ArrayList_imageUrls_selecd.contains(ArrayList_imageUrls.get(position))) {
                    ArrayList_imageUrls_selecd.remove(ArrayList_imageUrls.get(position));
                    mviewHolde.img_view.setColorFilter(null);
                    mviewHolde.img_selected.setVisibility(View.GONE);
                } else {
                    ArrayList_imageUrls_selecd.add(ArrayList_imageUrls.get(position));
                    mviewHolde.img_view.setColorFilter(Color.parseColor("#77000000"));
                    mviewHolde.img_selected.setVisibility(View.VISIBLE);
                }
                mHandler.sendEmptyMessage(1);
            }
        });
        AbsListView.LayoutParams param = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                mGV.getHeight()/4);
        convertView.setLayoutParams(param);

        return convertView;

    }
    class ViewHolder{
        ImageView img_view;
        ImageView img_selected;
    }
}