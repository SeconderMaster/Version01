package com.example.lmj.a2hm2.Myhome;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lmj.a2hm2.R;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity implements View.OnClickListener {
    private String search_Key;
    private RecyclerView sr_recyclerview;
    private ArrayList<Product> productArrayList;
    private SR_Adapter sr_adapter;
    private TextView tb_sr_back;
    private TextView tb_sr_filter;
    private TextView tb_sr_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Intent i=getIntent();
        search_Key=i.getStringExtra("search_Key");
        Toast.makeText(this,search_Key,Toast.LENGTH_SHORT).show();
        initView();

    }

    private void initView() {
        tb_sr_back= (TextView) findViewById(R.id.tb_sr_back);
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "ic_search/iconfont.ttf");
        tb_sr_back.setTypeface(iconfont);
        tb_sr_filter= (TextView) findViewById(R.id.tb_sr_filter);
        tb_sr_search= (TextView) findViewById(R.id.tb_sr_search);
        tb_sr_search.setText(search_Key);

        productArrayList=new ArrayList<Product>();
        for (int i=0;i<5;i++) {
            Product product1 = new Product("http://b.hiphotos.baidu.com/image/pic/item/810a19d8bc3eb135aa449355a21ea8d3fc1f4458.jpg", "闲置衣服", "135.00", "厦门集美区");
            productArrayList.add(product1);
        }



        sr_recyclerview= (RecyclerView) findViewById(R.id.sr_recyclerview);
        sr_recyclerview.setLayoutManager(new GridLayoutManager(this,2));
        sr_adapter= new SR_Adapter(SearchResult.this , productArrayList );
        sr_recyclerview.setAdapter(sr_adapter);
//        SpacesItemDecoration decoration=new SpacesItemDecoration(35);
//        sr_recyclerview.addItemDecoration(decoration);

        tb_sr_back.setOnClickListener(this);
        tb_sr_filter.setOnClickListener(this);
        tb_sr_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tb_sr_back:
                finish();
                break;
            case R.id.tb_sr_filter:

                break;
            case R.id.tb_sr_search:
                Intent i=new Intent(SearchResult.this,MH_SearchView.class);
                i.putExtra("searched_key",search_Key);
                startActivity(i);
                break;
        }
    }
}
