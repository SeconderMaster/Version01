package com.example.lmj.a2hm2.My;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lmj.a2hm2.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;

public class SMS_Login extends AppCompatActivity implements View.OnClickListener {

    private TextView ic_tb_SMS_login_back;
    private TextView tb_SMS_login_help;
    private TextView ic_SMS_phone_number;
    private TextView SMS_login_send_verification_code;
    private TextView ic_SMS_verification_code;
    private TextView to_account_login;
    private Button SMS_login_register;
    private Button SMS_login_go;
    private TextView ic_SMS_login_WeChat;
    private TextView ic_SMS_login_QQ;
    private TextView ic_SMS_login_Micro_blog;
    private LinearLayout SMS_login_WeChat;
    private LinearLayout SMS_login_QQ;
    private LinearLayout SMS_login_Micro_blog;
    private EditText SMS_login_set_phone_number;
    private EditText SMS_login_set_verification_code;
    private TimeCount time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms__login);
        initView();
    }

    private void initView() {
        ic_tb_SMS_login_back= (TextView) findViewById(R.id.ic_tb_SMS_login_back);
        tb_SMS_login_help= (TextView) findViewById(R.id.tb_SMS_login_help);
        ic_SMS_phone_number= (TextView) findViewById(R.id.ic_SMS_phone_number);
        SMS_login_send_verification_code= (TextView) findViewById(R.id.SMS_login_send_verification_code);
        ic_SMS_verification_code= (TextView) findViewById(R.id.ic_SMS_verification_code);
        to_account_login= (TextView) findViewById(R.id.to_account_login);
        SMS_login_set_phone_number= (EditText) findViewById(R.id.SMS_login_set_phone_number);
        SMS_login_set_verification_code= (EditText) findViewById(R.id.SMS_login_set_verification_code);
        ic_SMS_login_WeChat= (TextView) findViewById(R.id.ic_SMS_login_WeChat);
        ic_SMS_login_Micro_blog= (TextView) findViewById(R.id.ic_SMS_login_Micro_blog);
        ic_SMS_login_QQ= (TextView) findViewById(R.id.ic_SMS_login_QQ);
        SMS_login_register= (Button) findViewById(R.id.SMS_login_register);
        SMS_login_go= (Button) findViewById(R.id.SMS_login_go);
        SMS_login_WeChat= (LinearLayout) findViewById(R.id.SMS_login_WeChat);
        SMS_login_QQ= (LinearLayout) findViewById(R.id.SMS_login_QQ);
        SMS_login_Micro_blog= (LinearLayout) findViewById(R.id.SMS_login_Micro_blog);

        Typeface iconfont1 = Typeface.createFromAsset(getAssets(), "ic_search/iconfont.ttf");
        ic_tb_SMS_login_back.setTypeface(iconfont1);
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "ic_login_register/iconfont.ttf");
        ic_SMS_phone_number.setTypeface(iconfont);ic_SMS_verification_code.setTypeface(iconfont);
        Typeface ic_3_login = Typeface.createFromAsset(getAssets(), "ic_SMS_login_third_party/iconfont.ttf");
        ic_SMS_login_WeChat.setTypeface(ic_3_login);
        ic_SMS_login_QQ.setTypeface(ic_3_login);
        ic_SMS_login_Micro_blog.setTypeface(ic_3_login);

        ic_tb_SMS_login_back.setOnClickListener(this);
        tb_SMS_login_help.setOnClickListener(this);
        SMS_login_register.setOnClickListener(this);
        SMS_login_send_verification_code.setOnClickListener(this);
        SMS_login_WeChat.setOnClickListener(this);
        SMS_login_QQ.setOnClickListener(this);
        SMS_login_Micro_blog.setOnClickListener(this);
        to_account_login.setOnClickListener(this);
        SMS_login_go.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ic_tb_SMS_login_back:
                finish();
                break;
            case R.id.tb_SMS_login_help:
                Toast.makeText(SMS_Login.this,"帮助",Toast.LENGTH_SHORT).show();
                break;
            case R.id.SMS_login_send_verification_code:
                String phoneNumber=SMS_login_set_phone_number.getText().toString();
                String regExp = "^[1]([3][0-9]{1}|59|58|53|88|89)[0-9]{8}$";
                Pattern p = Pattern.compile(regExp);
                Matcher m = p.matcher(phoneNumber);
                if(m.find()){
                    time = new TimeCount(60000, 1000);//构造CountDownTimer对象
                    time.start();//开始计时
                    SMS_login_requestSMSCode(SMS_login_set_phone_number.getText().toString());
                }
                else Toast.makeText(this,"请输入正确手机号",Toast.LENGTH_SHORT).show();
                break;
            case R.id.SMS_login_go:
                SMS_login(SMS_login_set_phone_number.getText().toString(),
                        SMS_login_set_verification_code.getText().toString());
                break;
            case R.id.SMS_login_register:
                startActivity(new Intent(SMS_Login.this,Rapid_Register.class));
                SMS_Login.this.finish();
                break;
            case R.id.to_account_login:
                startActivity(new Intent(this,Account_Login.class));
                SMS_Login.this.finish();
                break;
            case R.id.SMS_login_WeChat:
                Toast.makeText(SMS_Login.this,"微信",Toast.LENGTH_SHORT).show();
                break;
            case R.id.SMS_login_QQ:
                System.out.println("QQ");
                Toast.makeText(SMS_Login.this,"QQ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.SMS_login_Micro_blog:
                System.out.println("微博");
                Toast.makeText(SMS_Login.this,"微博",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void SMS_login(String number, String code) {
        BmobUser.loginBySMSCode(number, code, new LogInListener<My_User>() {
            @Override
            public void done(My_User my_user, BmobException e) {
                if(e==null){
                    Toast.makeText(SMS_Login.this,"登陆成功",Toast.LENGTH_SHORT).show();
                    SMS_Login.this.finish();
                }else{
                    Log.i("bmob", "登录失败：code ="+e.getErrorCode()+",msg = "+e.getLocalizedMessage());
                    Toast.makeText(SMS_Login.this,"输入错误验证码，请重试",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }//验证码登录
    private void SMS_login_requestSMSCode(String number) {
        BmobSMS.requestSMSCode(number, "asd", new QueryListener<Integer>() {
            @Override
            public void done(Integer integer, BmobException e) {
                if(e==null){
                    Log.i("smile", "短信id："+integer);//用于后续的查询本次短信发送状态
                }else{
                    Log.i("bmob", "发送失败：code ="+e.getErrorCode()+",msg = "+e.getLocalizedMessage());
                }
            }
        });
    }//请求发送验证码
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }
        @Override
        public void onFinish() {//计时完毕时触发
            SMS_login_send_verification_code.setText("重新验证");
            SMS_login_send_verification_code.setClickable(true);
        }
        @Override
        public void onTick(long millisUntilFinished){//计时过程显示
            SMS_login_send_verification_code.setClickable(false);
            SMS_login_send_verification_code.setText(millisUntilFinished /1000+"秒");
        }
    }//计时
}
