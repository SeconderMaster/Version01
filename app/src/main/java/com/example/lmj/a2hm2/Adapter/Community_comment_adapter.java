package com.example.lmj.a2hm2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lmj.a2hm2.Community.Article_comment;
import com.example.lmj.a2hm2.R;

import java.util.ArrayList;

/**
 * Created by wfh on 2016/9/20.
 */
public class Community_comment_adapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Article_comment> mcomments;

    public Community_comment_adapter(Context context, ArrayList<Article_comment> mcomments) {
        mContext = context;
        this.mcomments = mcomments;
    }

    @Override
    public int getCount() {
        return mcomments.size();
    }

    @Override
    public Object getItem(int position) {
        return mcomments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mviewholder;
        if(convertView==null){
            mviewholder=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.comment_item,null);
            mviewholder.mhead= (ImageView) convertView.findViewById(R.id.iv_comment_head);
            mviewholder.mname= (TextView) convertView.findViewById(R.id.tv_comment_name);
            mviewholder.mcontent= (TextView) convertView.findViewById(R.id.tv_comment_content);
            mviewholder.mtime= (TextView) convertView.findViewById(R.id.tv_comment_releasetime);
            convertView.setTag(mviewholder);
        }else {
            mviewholder= (ViewHolder) convertView.getTag();
        }
        Article_comment comment=mcomments.get(position);
        mviewholder.mhead.setBackgroundResource(R.drawable.user_head);
        mviewholder.mname.setText(comment.getArtivle_name());
        mviewholder.mcontent.setText(comment.getComment_content());
        mviewholder.mtime.setText(comment.getCreatedAt().substring(11,19));
        return convertView;
    }
    class ViewHolder{
        TextView mname;
        ImageView mhead;
        TextView mtime;
        TextView mcontent;

    }
}
