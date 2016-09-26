package com.example.lmj.a2hm2.Release;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lmj.a2hm2.Community.Community_article;
import com.example.lmj.a2hm2.My.My_User;
import com.example.lmj.a2hm2.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Release_essary extends AppCompatActivity implements View.OnClickListener {

    private EditText write_essary;
    private Button release_essary;
    private Community_article marticle;
    private My_User userinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_essary);
        initview();
        initlistener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        userinfo=BmobUser.getCurrentUser(My_User.class);
        if(userinfo !=null){
            release_essary.setEnabled(true);
        }else {
            release_essary.setEnabled(false);
        }
    }

    private void initlistener() {
        release_essary.setOnClickListener(this);
    }

    private void initview() {
        write_essary= (EditText) findViewById(R.id.et_write_essary);
        release_essary= (Button) findViewById(R.id.btn_release_essary);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_release_essary:
                essarry_release();
                break;
        }
    }

    private void essarry_release() {
        marticle=new Community_article();
        marticle.setArticle(userinfo);
        marticle.setArticle_name(userinfo.getUsername());
        marticle.setDes(write_essary.getText().toString());
        marticle.setThumb_num("0");
        marticle.setComment_num("0");
        marticle.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    Toast.makeText(getApplicationContext(),"发布成功",Toast.LENGTH_SHORT).show();
                }else{
                    Log.i("wfh", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }
}
