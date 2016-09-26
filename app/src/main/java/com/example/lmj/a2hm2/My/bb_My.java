package com.example.lmj.a2hm2.My;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lmj.a2hm2.R;
import com.example.lmj.a2hm2.isLogin;

/**
 * Created by lmj on 2016/9/11.
 */
public class bb_My extends Fragment implements View.OnClickListener {
    private View v_my;
    private LinearLayout my_release;
    private TextView ic_my_release;
    private TextView ic_into_my_release;
    private LinearLayout my_sold;
    private TextView ic_my_sold;
    private TextView ic_into_my_sold;
    private LinearLayout my_buy;
    private TextView ic_my_buy;
    private TextView ic_into_my_buy;
    private LinearLayout my_like;
    private TextView ic_my_like;
    private TextView ic_into_my_like;
    private LinearLayout my_auction;
    private TextView ic_my_auction;
    private TextView ic_into_my_auction;
    private LinearLayout my_have;
    private TextView ic_my_have;
    private TextView ic_into_my_have;
    private LinearLayout my_setting;
    private TextView ic_my_setting;
    private TextView ic_into_my_setting;
    private LinearLayout login_linear;
    private Button login_now;
    private RelativeLayout login_relative;
    private TextView user_name;
    private TextView user_sign;
    private ImageView user_head;
    private TextView ic_my_detail;
    private LinearLayout linear_number;
    private LinearLayout linear_be_praised_number;
    private LinearLayout linear_pay_attention_to_the_number;
    private LinearLayout linear_fan_number;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v_my= inflater.inflate(R.layout.my_fragment,container,false);
        initView();
        return v_my;
    }

    private void initView() {
        login_relative= (RelativeLayout) v_my.findViewById(R.id.login_relative);
        user_name= (TextView) v_my.findViewById(R.id.user_name);
        user_sign= (TextView) v_my.findViewById(R.id.user_sign);
        ic_my_detail= (TextView) v_my.findViewById(R.id.ic_my_detail);
        user_head= (ImageView) v_my.findViewById(R.id.user_head);

        linear_number= (LinearLayout) v_my.findViewById(R.id.linear_number);
        linear_be_praised_number= (LinearLayout) v_my.findViewById(R.id.linear_be_praised_number);
        linear_pay_attention_to_the_number= (LinearLayout) v_my.findViewById(R.id.linear_pay_attention_to_the_number);
        linear_fan_number= (LinearLayout) v_my.findViewById(R.id.linear_fan_number);
        login_linear= (LinearLayout) v_my.findViewById(R.id.login_linear);
        login_now= (Button) v_my.findViewById(R.id.login_now);

        my_release= (LinearLayout) v_my.findViewById(R.id.my_release);
        ic_my_release= (TextView) v_my.findViewById(R.id.ic_my_release);
        ic_into_my_release= (TextView) v_my.findViewById(R.id.ic_into_my_release);

        my_sold= (LinearLayout) v_my.findViewById(R.id.my_sold);
        ic_my_sold= (TextView) v_my.findViewById(R.id.ic_my_sold);
        ic_into_my_sold= (TextView) v_my.findViewById(R.id.ic_into_my_sold);

        my_buy= (LinearLayout) v_my.findViewById(R.id.my_buy);
        ic_my_buy= (TextView) v_my.findViewById(R.id.ic_my_buy);
        ic_into_my_buy= (TextView) v_my.findViewById(R.id.ic_into_my_buy);

        my_like= (LinearLayout) v_my.findViewById(R.id.my_like);
        ic_my_like= (TextView) v_my.findViewById(R.id.ic_my_like);
        ic_into_my_like= (TextView) v_my.findViewById(R.id.ic_into_my_like);

        my_auction= (LinearLayout) v_my.findViewById(R.id.my_auction);
        ic_my_auction= (TextView) v_my.findViewById(R.id.ic_my_auction);
        ic_into_my_auction= (TextView) v_my.findViewById(R.id.ic_into_my_auction);

        my_have= (LinearLayout) v_my.findViewById(R.id.my_have);
        ic_my_have= (TextView) v_my.findViewById(R.id.ic_my_have);
        ic_into_my_have= (TextView) v_my.findViewById(R.id.ic_into_my_have);

        my_setting= (LinearLayout) v_my.findViewById(R.id.my_setting);
        ic_my_setting= (TextView) v_my.findViewById(R.id.ic_my_setting);
        ic_into_my_setting= (TextView) v_my.findViewById(R.id.ic_into_my_setting);
        Typeface iconfont = Typeface.createFromAsset(getActivity().getAssets(), "ic_my/iconfont.ttf");
        ic_my_release.setTypeface(iconfont);ic_into_my_release.setTypeface(iconfont);
        ic_my_sold.setTypeface(iconfont);ic_into_my_sold.setTypeface(iconfont);
        ic_my_buy.setTypeface(iconfont);ic_into_my_buy.setTypeface(iconfont);
        ic_my_like.setTypeface(iconfont);ic_into_my_like.setTypeface(iconfont);
        ic_my_auction.setTypeface(iconfont);ic_into_my_auction.setTypeface(iconfont);
        ic_my_have.setTypeface(iconfont);ic_into_my_have.setTypeface(iconfont);
        ic_my_setting.setTypeface(iconfont);ic_into_my_setting.setTypeface(iconfont);
        ic_my_detail.setTypeface(iconfont);

        if(isLogin.isLogin()){
            login_now.setVisibility(View.GONE);
        }
        else {
            linear_number.setVisibility(View.GONE);
            login_relative.setVisibility(View.GONE);
        }
        my_release.setOnClickListener(this);
        my_sold.setOnClickListener(this);
        my_buy.setOnClickListener(this);
        my_like.setOnClickListener(this);
        my_auction.setOnClickListener(this);
        my_have.setOnClickListener(this);
        my_setting.setOnClickListener(this);
        login_linear.setOnClickListener(this);
        login_now.setOnClickListener(this);
        login_relative.setOnClickListener(this);
        linear_be_praised_number.setOnClickListener(this);
        linear_fan_number.setOnClickListener(this);
        linear_pay_attention_to_the_number.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_release:
                if (!isLogin.isLogin()){
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(),SMS_Login.class));
                }
                break;
            case R.id.my_sold:
                if (!isLogin.isLogin()){
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(),SMS_Login.class));
                }
                break;
            case R.id.my_buy:
                if (!isLogin.isLogin()){
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(),SMS_Login.class));
                }
                break;
            case R.id.my_like:
                if (!isLogin.isLogin()){
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(),SMS_Login.class));
                }
                break;
            case R.id.my_auction:
                if (!isLogin.isLogin()){
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(),SMS_Login.class));
                }
                break;
            case R.id.my_have:
                if (!isLogin.isLogin()){
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(),SMS_Login.class));
                }
                break;
            case R.id.my_setting:
                startActivity(new Intent(getActivity(),My_Setting.class));
                break;
            case R.id.login_linear:
                if (!isLogin.isLogin()){
                    startActivity(new Intent(getActivity(),SMS_Login.class));
                }
                break;
            case R.id.login_now:
                if (!isLogin.isLogin()){
                    startActivity(new Intent(getActivity(),SMS_Login.class));
                }
                break;
            case R.id.login_relative:
                Toast.makeText(getActivity(),"详情",Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_be_praised_number:
                Toast.makeText(getActivity(),"被赞数",Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_pay_attention_to_the_number:
                Toast.makeText(getActivity(),"关注数",Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_fan_number:
                Toast.makeText(getActivity(),"粉丝数",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
