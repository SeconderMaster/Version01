package com.example.lmj.a2hm2.Release;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.lmj.a2hm2.R;

import java.util.ArrayList;

public class Release_Classify_List extends AppCompatActivity {
    private TextView ic_tb_release_classify_back;
    private RecyclerView rc_recyclerView;
    private ArrayList<String> release_Classify_ListView;
    private RC_Adapter rc_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release__classify__list);
        initView();
    }
    private void initView(){
        rc_recyclerView= (RecyclerView) findViewById(R.id.rc_recyclerView);
        ic_tb_release_classify_back= (TextView) findViewById(R.id.ic_tb_release_classify_back);
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "ic_search/iconfont.ttf");
        ic_tb_release_classify_back.setTypeface(iconfont);

        release_Classify_ListView=new ArrayList<String>();
        release_Classify_ListView.add("女装");
        release_Classify_ListView.add("男装");
        release_Classify_ListView.add("手机");
        release_Classify_ListView.add("数码");
        release_Classify_ListView.add("化妆品");
        release_Classify_ListView.add("房屋租赁");

        rc_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rc_adapter= new RC_Adapter(this,release_Classify_ListView );
        rc_recyclerView.setAdapter(rc_adapter);
    }
}

