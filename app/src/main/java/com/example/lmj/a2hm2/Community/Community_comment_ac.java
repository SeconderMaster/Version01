package com.example.lmj.a2hm2.Community;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lmj.a2hm2.Adapter.Community_comment_adapter;
import com.example.lmj.a2hm2.My.My_User;
import com.example.lmj.a2hm2.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class Community_comment_ac extends AppCompatActivity implements View.OnClickListener{

    private Community_article mArticle;
    private ImageView marticle_head;
    private TextView marticle_name;
    private TextView marticle_time;
    private TextView marticle_content;
    private ListView mcomment_lv;
    private ArrayList<Article_comment> mcomments=new ArrayList<>();
    private Community_comment_adapter madapter;
    private EditText mwrite;
    private Button mrelease;
    private Intent mIntent;
    private Context mContext;
    private My_User userinfo;
    private  String marticle_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_comment);
        mIntent=getIntent();
        marticle_id=mIntent.getStringExtra("id");
        mContext=this;
        initview(marticle_id);
        initdate(marticle_id);
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        userinfo= BmobUser.getCurrentUser(My_User.class);
    }

    private void initListener() {
        mrelease.setOnClickListener(this);
    }

    private void initdate(String marticle_id) {
        new Getcomment().execute(marticle_id);
    }

    private void initview(String id) {
        marticle_name= (TextView) findViewById(R.id.tv_article_name);
        marticle_head= (ImageView) findViewById(R.id.iv_article_head_pic);
        marticle_time= (TextView) findViewById(R.id.tv_article_release_time);
        marticle_content= (TextView) findViewById(R.id.tv_article_des);
        mcomment_lv= (ListView) findViewById(R.id.comment_listview);
        mwrite= (EditText) findViewById(R.id.et_write_comment);
        mrelease= (Button) findViewById(R.id.btn_release_comment);
        new GetArticle().execute(id);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_release_comment:
                releaseComment();
                break;
        }
    }

    private void releaseComment() {
        String comment=mwrite.getText().toString();
        if(comment==null){
            Toast.makeText(mContext,"亲！请填入数据",Toast.LENGTH_SHORT).show();
        }else if(userinfo==null){
            Toast.makeText(mContext,"亲！请先登入",Toast.LENGTH_SHORT).show();
        }else {
            Article_comment mcontent=new Article_comment();
            mcontent.setArticle(userinfo);
            mcontent.setArticle_id(marticle_id);
            mcontent.setArtivle_name(userinfo.getUsername());
            mcontent.setComment_content(comment);
            mcontent.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if(e==null){
                        Toast.makeText(getApplicationContext(),"发布成功",Toast.LENGTH_SHORT).show();
                        initdate(marticle_id);
                        int x = Integer.parseInt(mArticle.getComment_num()) + 1;
                        mArticle.setComment_num(x + "");
                        mArticle.update(mArticle.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Log.i("wfh", "更新成功");
                                } else {
                                    Log.i("wfh", "更新失败：" + e.getMessage() + "," + e.getErrorCode());
                                }
                            }
                        });
                    }else{
                        Log.i("wfh", "失败：" + e.getMessage() + "," + e.getErrorCode());
                    }
                }
            });
        }
    }
    class GetArticle extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... params) {
            BmobQuery<Community_article> query=new BmobQuery<Community_article>();
            query.getObject(params[0], new QueryListener<Community_article>() {
                @Override
                public void done(Community_article community_article, BmobException e) {
                    if(e==null){
                        mArticle=community_article;
                        marticle_head.setBackgroundResource(R.drawable.user_head);
                        marticle_name.setText(community_article.getArticle_name());
                        marticle_content.setText(community_article.getDes());
                        marticle_time.setText(community_article.getCreatedAt().substring(11,19));
                    }else{
                        Log.i("wfh","失败："+e.getMessage()+","+e.getErrorCode());
                    }
                }
            });
            return null;
        }
    }
    class Getcomment extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... params) {
            BmobQuery<Article_comment> query=new BmobQuery<Article_comment>();
            query.addWhereEqualTo("article_id",params[0]);
            query.setLimit(20);
            query.findObjects(new FindListener<Article_comment>() {
                @Override
                public void done(List<Article_comment> list, BmobException e) {
                    if (e == null) {
                        Toast.makeText(getApplicationContext(), "下载成功", Toast.LENGTH_SHORT).show();
                        if (list != null) {
                            mcomments.clear();
                            mcomments.addAll(list);
                        }
                        madapter = new Community_comment_adapter(mContext, mcomments);
                        mcomment_lv.setAdapter(madapter);
                    } else {
                        Log.i("wfh", "失败：" + e.getMessage() + "," + e.getErrorCode());
                        Toast.makeText(getApplicationContext(), "评论下载失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            return null;
        }
    }
}
