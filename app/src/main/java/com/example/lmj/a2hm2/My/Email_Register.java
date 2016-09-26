package com.example.lmj.a2hm2.My;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lmj.a2hm2.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Email_Register extends AppCompatActivity implements View.OnClickListener {

    private TextView ic_tb_email_registration_back;
    private TextView tb_email_registration_help;
    private TextView ic_email_registration_email;
    private TextView ic_email_registration_set_password;
    private TextView ic_email_registration_confirm_password;
    private TextView to_SMS_register;
    private EditText email_registration_set_email;
    private EditText email_registration_set_password;
    private EditText email_registration_confirm_password;
    private Button email_registration_go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email__register);
        initView();
    }

    private void initView() {
        ic_tb_email_registration_back= (TextView) findViewById(R.id.ic_tb_email_registration_back);
        tb_email_registration_help= (TextView) findViewById(R.id.tb_email_registration_help);
        ic_email_registration_email= (TextView) findViewById(R.id.ic_email_registration_email);
        ic_email_registration_set_password= (TextView) findViewById(R.id.ic_email_registration_set_password);
        ic_email_registration_confirm_password= (TextView) findViewById(R.id.ic_email_registration_confirm_password);
        to_SMS_register= (TextView) findViewById(R.id.to_SMS_register);
        email_registration_set_email= (EditText) findViewById(R.id.email_registration_set_email);
        email_registration_set_password= (EditText) findViewById(R.id.email_registration_set_password);
        email_registration_confirm_password= (EditText) findViewById(R.id.email_registration_confirm_password);
        email_registration_go= (Button) findViewById(R.id.email_registration_go);

        email_registration_set_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        email_registration_confirm_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        Typeface iconfont1 = Typeface.createFromAsset(getAssets(), "ic_search/iconfont.ttf");
        ic_tb_email_registration_back.setTypeface(iconfont1);
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "ic_login_register/iconfont.ttf");
        ic_email_registration_email.setTypeface(iconfont);
        ic_email_registration_set_password.setTypeface(iconfont);ic_email_registration_confirm_password.setTypeface(iconfont);

        ic_tb_email_registration_back.setOnClickListener(this);
        tb_email_registration_help.setOnClickListener(this);
        email_registration_go.setOnClickListener(this);
        to_SMS_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ic_tb_email_registration_back:
                finish();
                break;
            case R.id.tb_email_registration_help:
                Toast.makeText(Email_Register.this,"帮助",Toast.LENGTH_SHORT).show();
                break;
            case R.id.email_registration_go:
                String email=email_registration_set_email.getText().toString();
                String regExp="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
                Pattern p = Pattern.compile(regExp);
                Matcher m = p.matcher(email);
                if(m.find()){
                    if (email_registration_set_password.getText().toString().equals(email_registration_confirm_password.getText().toString())&&
                                !TextUtils.isEmpty(email_registration_set_password.getText())){
                            Email_Register_signUp(email_registration_set_email.getText().toString(),
                                    email_registration_set_password.getText().toString());
                    }
                    else {
                            Toast.makeText(Email_Register.this,"密码设置错误",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Email_Register.this,"邮箱验证错误",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.to_SMS_register:
                startActivity(new Intent(Email_Register.this,Rapid_Register.class));
                Email_Register.this.finish();
                break;
        }
    }

    private void Email_Register_signUp(String email, String password) {
        BmobUser bu=new BmobUser();
        bu.setUsername(email);
        bu.setEmail(email);
        bu.setPassword(password);
        bu.signUp(new SaveListener<My_User>() {
            @Override
            public void done(My_User my_user, BmobException e) {
                if(e==null){
                    Toast.makeText(Email_Register.this,"登陆成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Log.i("bmob", "登录失败：code ="+e.getErrorCode()+",msg = "+e.getLocalizedMessage());
                }
            }
        });
    }
}
