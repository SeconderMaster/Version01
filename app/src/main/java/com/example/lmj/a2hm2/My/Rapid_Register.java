package com.example.lmj.a2hm2.My;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class Rapid_Register extends AppCompatActivity implements View.OnClickListener {
    private TextView ic_tb_rapid_register_back;
    private TextView tb_rapid_registration_help;
    private TextView ic_rapid_registration_phone_number;
    private TextView ic_rapid_registration_verification_code;
    private TextView ic_rapid_registration_set_password;
    private TextView ic_rapid_registration_confirm_password;
    private TextView rapid_registration_send_verification_code;
    private TextView rapid_registration_other_method_register;
    private Button rapid_registration_go;
    private EditText rapid_registration_set_phone_number;
    private EditText rapid_registration_set_verification_code;
    private EditText rapid_registration_set_password;
    private EditText rapid_registration_confirm_password;
    private TimeCount time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapid_register);
        initView();
    }

    private void initView() {
        ic_tb_rapid_register_back= (TextView) findViewById(R.id.ic_tb_rapid_register_back);
        tb_rapid_registration_help= (TextView) findViewById(R.id.tb_rapid_registration_help);
        ic_rapid_registration_phone_number= (TextView) findViewById(R.id.ic_rapid_registration_phone_number);
        ic_rapid_registration_verification_code= (TextView) findViewById(R.id.ic_rapid_registration_verification_code);
        ic_rapid_registration_set_password= (TextView) findViewById(R.id.ic_rapid_registration_set_password);
        ic_rapid_registration_confirm_password= (TextView) findViewById(R.id.ic_rapid_registration_confirm_password);
        rapid_registration_other_method_register= (TextView) findViewById(R.id.rapid_registration_other_method_register);
        rapid_registration_send_verification_code= (TextView) findViewById(R.id.rapid_registration_send_verification_code);
        rapid_registration_go= (Button) findViewById(R.id.rapid_registration_go);
        rapid_registration_set_phone_number= (EditText) findViewById(R.id.rapid_registration_set_phone_number);
        rapid_registration_set_verification_code= (EditText) findViewById(R.id.rapid_registration_set_verification_code);
        rapid_registration_set_password= (EditText) findViewById(R.id.rapid_registration_set_password);
        rapid_registration_confirm_password= (EditText) findViewById(R.id.rapid_registration_confirm_password);
        rapid_registration_set_phone_number.requestFocus();

        rapid_registration_set_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        rapid_registration_confirm_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        Typeface iconfont1 = Typeface.createFromAsset(getAssets(), "ic_search/iconfont.ttf");
        ic_tb_rapid_register_back.setTypeface(iconfont1);
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "ic_login_register/iconfont.ttf");
        ic_rapid_registration_phone_number.setTypeface(iconfont);ic_rapid_registration_verification_code.setTypeface(iconfont);
        ic_rapid_registration_set_password.setTypeface(iconfont);ic_rapid_registration_confirm_password.setTypeface(iconfont);
        ic_tb_rapid_register_back.setOnClickListener(this);
        tb_rapid_registration_help.setOnClickListener(this);
        rapid_registration_go.setOnClickListener(this);
        rapid_registration_send_verification_code.setOnClickListener(this);
        rapid_registration_other_method_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ic_tb_rapid_register_back:
                startActivity(new Intent(this,SMS_Login.class));
                Rapid_Register.this.finish();
                break;
            case R.id.tb_rapid_registration_help:
                Toast.makeText(Rapid_Register.this,"帮助",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rapid_registration_other_method_register:
                startActivity(new Intent(Rapid_Register.this,Email_Register.class));
                Rapid_Register.this.finish();
                break;
            case R.id.rapid_registration_go:
                if(!TextUtils.isEmpty(rapid_registration_set_verification_code.getText()))
                {
                    if (rapid_registration_set_password.getText().toString().equals(rapid_registration_confirm_password.getText().toString())&&
                            !TextUtils.isEmpty(rapid_registration_set_password.getText())){
                        Rapid_registrationSend_signOrLogin(rapid_registration_set_phone_number.getText().toString(),
                                rapid_registration_set_verification_code.getText().toString(),
                                rapid_registration_set_password.getText().toString());
                    }
                    else {
                        Toast.makeText(Rapid_Register.this,"密码设置错误",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Rapid_Register.this,"请输入验证码",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rapid_registration_send_verification_code:
                String phoneNumber=rapid_registration_set_phone_number.getText().toString();
                String regExp = "^[1]([3][0-9]{1}|59|58|53|88|89)[0-9]{8}$";
                Pattern p = Pattern.compile(regExp);
                Matcher m = p.matcher(phoneNumber);
                if(m.find()){
                    time = new TimeCount(60000, 1000);//构造CountDownTimer对象
                    time.start();//开始计时
                    Rapid_registrationSend_verification_code(rapid_registration_set_phone_number.getText().toString());
                }
                else Toast.makeText(this,"请输入正确手机号",Toast.LENGTH_SHORT).show();
        }
    }
    private void Rapid_registrationSend_verification_code(String number) {
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
    }//发送验证码
    private void Rapid_registrationSend_signOrLogin(String phoneNumber, String smsCode,String password){
        My_User my_user=new My_User();
        my_user.setMobilePhoneNumber(phoneNumber);
        my_user.setPassword(password);
        my_user.signOrLogin(smsCode, new SaveListener<My_User>() {
            @Override
            public void done(My_User my_user, cn.bmob.v3.exception.BmobException e) {
                if(e==null){
                    Toast.makeText(Rapid_Register.this,"登陆成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Log.i("bmob", "登录失败：code ="+e.getErrorCode()+",msg = "+e.getLocalizedMessage());
                    Toast.makeText(Rapid_Register.this,"输入错误验证码，请重试",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }//一键登录
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }
        @Override
        public void onFinish() {//计时完毕时触发
            rapid_registration_send_verification_code.setText("重新验证");
            rapid_registration_send_verification_code.setClickable(true);
        }
        @Override
        public void onTick(long millisUntilFinished){//计时过程显示
            rapid_registration_send_verification_code.setClickable(false);
            rapid_registration_send_verification_code.setText(millisUntilFinished /1000+"秒");
        }
    }//计时
}
