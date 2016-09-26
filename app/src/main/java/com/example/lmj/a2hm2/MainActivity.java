package com.example.lmj.a2hm2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lmj.a2hm2.Community.BB_Community;
import com.example.lmj.a2hm2.Msg.bb_Msg;
import com.example.lmj.a2hm2.My.bb_My;
import com.example.lmj.a2hm2.Myhome.bb_MyHome;
import com.example.lmj.a2hm2.Release.Release;
import com.example.lmj.a2hm2.Release.bb_Relase;

import java.util.ArrayList;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class MainActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private TextView ic_myhome;
    private TextView ic_community;
    private TextView ic_release;
    private TextView ic_msg;
    private TextView ic_my;
    public static ViewPager viewPager;
    public static ArrayList<Fragment> fragmentList;
    private LinearLayout bb_myhome;
    private LinearLayout bb_community;
    private LinearLayout bb_release;
    private LinearLayout bb_msg;
    private LinearLayout bb_my;
    TextView bb_myhome_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "c6e1df6871ef4b437d5624f77c3ff91c");
        if(isLogin.isLogin()){
            String username = (String) BmobUser.getObjectByKey("username");
        }
        initView();
        viewPager= (ViewPager) findViewById(R.id.viewPage);
        viewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.setOnPageChangeListener(this);
        setTabSelection(0);
    }

    private void initView() {
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new bb_MyHome());
        fragmentList.add(new BB_Community());
        fragmentList.add(new bb_Relase());
        fragmentList.add(new bb_Msg());
        fragmentList.add(new bb_My());


        Typeface iconfont = Typeface.createFromAsset(getAssets(), "ic_bottombar/iconfont.ttf");
        ic_myhome= (TextView) findViewById(R.id.ic_myhome);
        ic_community= (TextView) findViewById(R.id.ic_community);
        ic_release= (TextView) findViewById(R.id.ic_release);
        ic_msg= (TextView) findViewById(R.id.ic_msg);
        ic_my= (TextView) findViewById(R.id.ic_my);
        ic_myhome.setTypeface(iconfont);
        ic_community.setTypeface(iconfont);
        ic_release.setTypeface(iconfont);
        ic_msg.setTypeface(iconfont);
        ic_my.setTypeface(iconfont);

        bb_myhome_text= (TextView) findViewById(R.id.bb_myhome_text);

        bb_myhome= (LinearLayout) findViewById(R.id.bb_myhome);
        bb_community= (LinearLayout) findViewById(R.id.bb_community);
        bb_release= (LinearLayout) findViewById(R.id.bb_release);
        bb_msg= (LinearLayout) findViewById(R.id.bb_msg);
        bb_my= (LinearLayout) findViewById(R.id.bb_my);

        bb_myhome.setOnClickListener(this);
        bb_community.setOnClickListener(this);
        bb_release.setOnClickListener(this);
        bb_msg.setOnClickListener(this);
        bb_my.setOnClickListener(this);


    }

    private void setTabSelection(int i) {
        clearSelection();
        switch (i){
            case 0:
                ic_myhome.setTextColor(Color.parseColor("#FEDB43"));
                break;
            case 1:
                ic_community.setTextColor(Color.parseColor("#FEDB43"));
                break;
            case 2:
                ic_release.setTextColor(Color.parseColor("#FEDB43"));
                break;
            case 3:
                ic_msg.setTextColor(Color.parseColor("#FEDB43"));
                break;
            case 4:
                ic_my.setTextColor(Color.parseColor("#FEDB43"));
                break;

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bb_myhome:
                setTabSelection(0);
                viewPager.setCurrentItem(0);
                break;
            case R.id.bb_community:
                setTabSelection(1);
                viewPager.setCurrentItem(1);
                break;
            case R.id.bb_release:
                startActivity(new Intent(MainActivity.this, Release.class));
                setTabSelection(2);
                viewPager.setCurrentItem(2);
                break;
            case R.id.bb_msg:
                setTabSelection(3);
                viewPager.setCurrentItem(3);
                break;
            case R.id.bb_my:
                setTabSelection(4);
                viewPager.setCurrentItem(4);
                break;

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTabSelection(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private void clearSelection(){
        ic_myhome.setTextColor(Color.parseColor("#767676"));
        ic_community.setTextColor(Color.parseColor("#767676"));
        ic_release.setTextColor(Color.parseColor("#767676"));
        ic_msg.setTextColor(Color.parseColor("#767676"));
        ic_my.setTextColor(Color.parseColor("#767676"));
    }

}
