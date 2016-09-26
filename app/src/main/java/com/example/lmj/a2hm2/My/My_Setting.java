package com.example.lmj.a2hm2.My;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lmj.a2hm2.R;
import com.example.lmj.a2hm2.isLogin;

import cn.bmob.v3.BmobUser;

public class My_Setting extends AppCompatActivity implements View.OnClickListener {

    private TextView ic_tb_my_setting_back;
    private Button log_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__setting);
        initView();
    }

    private void initView() {
        ic_tb_my_setting_back= (TextView) findViewById(R.id.ic_tb_my_setting_back);
        log_out= (Button) findViewById(R.id.log_out);
        if(!isLogin.isLogin()){
            log_out.setVisibility(View.GONE);
        }

        Typeface iconfont = Typeface.createFromAsset(getAssets(), "ic_search/iconfont.ttf");
        ic_tb_my_setting_back.setTypeface(iconfont);

        ic_tb_my_setting_back.setOnClickListener(this);
        log_out.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ic_tb_my_setting_back:
                finish();
                break;
            case R.id.log_out:
                BmobUser.logOut();   //清除缓存用户对象
                BmobUser currentUser = BmobUser.getCurrentUser(); // 现在的currentUser是null了
                startActivity(new Intent(this,SMS_Login.class));
                Toast.makeText(My_Setting.this,"退出登录成功",Toast.LENGTH_SHORT).show();
                My_Setting.this.finish();
                break;
        }
    }
}
