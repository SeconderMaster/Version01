package com.example.lmj.a2hm2.My;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lmj.a2hm2.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class Account_Login extends AppCompatActivity implements View.OnClickListener {
    private TextView ic_tb_account_login_back;
    private TextView tb_account_login_help;
    private TextView ic_account_phone_number;
    private TextView ic_account_login_password;
    private TextView ic_account_login_psd_hide;
    private TextView to_SMS_login;
    private TextView find_psd;
    private Button account_login_go;
    private boolean psd_eye=false;
    private EditText account_login_set_phone_number;
    private EditText account_login_set_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);
        initView();
    }

    private void initView() {
        ic_tb_account_login_back= (TextView) findViewById(R.id.ic_tb_account_login_back);
        tb_account_login_help= (TextView) findViewById(R.id.tb_account_login_help);
        ic_account_phone_number= (TextView) findViewById(R.id.ic_account_phone_number);
        ic_account_login_password= (TextView) findViewById(R.id.ic_account_login_password);
        ic_account_login_psd_hide= (TextView) findViewById(R.id.ic_account_login_psd_hide);
        to_SMS_login= (TextView) findViewById(R.id.to_SMS_login);
        find_psd= (TextView) findViewById(R.id.find_psd);
        account_login_go= (Button) findViewById(R.id.account_login_go);
        account_login_set_phone_number= (EditText) findViewById(R.id.account_login_set_phone_number);
        account_login_set_password= (EditText) findViewById(R.id.account_login_set_password);
        account_login_set_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        Typeface iconfont1 = Typeface.createFromAsset(getAssets(), "ic_search/iconfont.ttf");
        ic_tb_account_login_back.setTypeface(iconfont1);
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "ic_login/iconfont.ttf");
        ic_account_login_psd_hide.setTypeface(iconfont);
        Typeface ic_login_register = Typeface.createFromAsset(getAssets(), "ic_login_register/iconfont.ttf");
        ic_account_phone_number.setTypeface(ic_login_register);
        ic_account_login_password.setTypeface(ic_login_register);

        ic_tb_account_login_back.setOnClickListener(this);
        tb_account_login_help.setOnClickListener(this);
        ic_account_login_psd_hide.setOnClickListener(this);
        account_login_go.setOnClickListener(this);
        find_psd.setOnClickListener(this);
        to_SMS_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ic_tb_account_login_back:
                finish();
                break;
            case R.id.tb_account_login_help:
                Toast.makeText(Account_Login.this,"帮助",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ic_account_login_psd_hide:
                if(psd_eye==false){
                    ic_account_login_psd_hide.setTextColor(Color.parseColor("#ff0000"));
                    account_login_set_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Editable etable = account_login_set_password.getText();
                    Selection.setSelection(etable, etable.length());//输入光标一直位于字符后面
                    psd_eye=true;
                }
                else {
                    ic_account_login_psd_hide.setTextColor(Color.parseColor("#767676"));
                    account_login_set_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Editable etable = account_login_set_password.getText();
                    Selection.setSelection(etable, etable.length());//输入光标一直位于字符后面
                    psd_eye=false;
                }
                break;
            case R.id.account_login_go:
                login_u(account_login_set_phone_number.getText().toString(),account_login_set_password.getText().toString());
                break;
            case R.id.to_SMS_login:
                startActivity(new Intent(this,SMS_Login.class));
                Account_Login.this.finish();
                break;
            case R.id.find_psd:
                Toast.makeText(Account_Login.this,"找回密码",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void login_u(String login_account, String login_password) {
        BmobUser.loginByAccount(login_account, login_password, new LogInListener<My_User>() {
            @Override
            public void done(My_User user, BmobException e) {
                if(user!=null){
                    Toast.makeText(Account_Login.this,"登陆成功",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Log.i("fail","用户登陆失败"+e.getMessage()+","+e.getErrorCode());
                    if(e.getErrorCode()==101){
                        Toast.makeText(Account_Login.this,"账户或密码错误",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
