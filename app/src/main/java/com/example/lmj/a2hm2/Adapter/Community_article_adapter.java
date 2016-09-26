package com.example.lmj.a2hm2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lmj.a2hm2.Community.Community_article;
import com.example.lmj.a2hm2.Community.Community_comment_ac;
import com.example.lmj.a2hm2.R;

import java.util.ArrayList;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by wfh on 2016/9/16.
 */
public class Community_article_adapter extends BaseAdapter{
    private ArrayList<Community_article> articles;
    private Context mContext;

    public Community_article_adapter(ArrayList<Community_article> articles, Context context) {
        this.articles = articles;
        mContext = context;
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Object getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder mViewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.community_item,null);
            mViewHolder=new ViewHolder();
            mViewHolder.head_pic= (ImageView) convertView.findViewById(R.id.iv_community_head_pic);
            mViewHolder.article_name= (TextView) convertView.findViewById(R.id.tv_community_name);
            mViewHolder.release_time= (TextView) convertView.findViewById(R.id.tv_community_release_time);
            mViewHolder.title= (TextView) convertView.findViewById(R.id.tv_community_title);
            mViewHolder.goods_pic= (GridView) convertView.findViewById(R.id.community_goods_pic);
            mViewHolder.goods_des= (TextView) convertView.findViewById(R.id.community_des);
            mViewHolder.thumb_num= (TextView) convertView.findViewById(R.id.tv_community_thumb_up_num);
            mViewHolder.comments_num= (TextView) convertView.findViewById(R.id.tv_community_comments_num);
            convertView.setTag(mViewHolder);
        }else {
            mViewHolder=(ViewHolder)convertView.getTag();
        }
        final Community_article marticle=articles.get(position);
        mViewHolder.head_pic.setBackgroundResource(R.drawable.user_head);
        mViewHolder.article_name.setText(marticle.getArticle_name());
        mViewHolder.release_time.setText(marticle.getCreatedAt().substring(11, 19));
        mViewHolder.title.setText("求点赞");
        mViewHolder.goods_des.setText(marticle.getDes());
        mViewHolder.thumb_num.setText(marticle.getThumb_num());
        mViewHolder.comments_num.setText(marticle.getComment_num());
        mViewHolder.thumb_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(marticle.getThumb_num()) + 1;
                mViewHolder.thumb_num.setText(x + "");
                marticle.setThumb_num(x + "");
                marticle.update(marticle.getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Log.i("wfh", "更新成功");
                        } else {
                            Log.i("wfh", "更新失败：" + e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });
            }
        });
        mViewHolder.comments_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(mContext,Community_comment_ac.class);
                it.putExtra("id",marticle.getObjectId());
                mContext.startActivity(it);
            }
        });
        return convertView;
    }

    class ViewHolder{
        ImageView head_pic;
        TextView article_name;
        TextView release_time;
        TextView title;
        GridView goods_pic;
        TextView goods_des;
        TextView thumb_num;
        TextView comments_num;
    }
}
