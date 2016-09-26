package com.example.lmj.a2hm2.Release;

import android.content.Context;
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
public class SelectAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> ArrayList_imageUrls;
    private GridView mGV;
    public SelectAdapter(GridView GV,Context context, ArrayList<String> ArrayList_imageUrls){
       this.mGV=GV;
        this.context=context;
        this.ArrayList_imageUrls=ArrayList_imageUrls;
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
                mviewHolde.img_view, new initB().select_picture_options);
        AbsListView.LayoutParams param = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                mGV.getHeight());
        convertView.setLayoutParams(param);
            return convertView;

    }
    class ViewHolder{
        ImageView img_view;
        ImageView img_selected;
    }
}