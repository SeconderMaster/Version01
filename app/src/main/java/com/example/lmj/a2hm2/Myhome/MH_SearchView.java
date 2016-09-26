package com.example.lmj.a2hm2.Myhome;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lmj.a2hm2.R;

public class MH_SearchView extends AppCompatActivity implements View.OnClickListener {
    private View v_search;
    private TextView tb_back;
    private TextView tb_go;
    private EditText tb_et_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mh__search_view);
        initView();
    }
    private void initView() {
        tb_back= (TextView)findViewById(R.id.tb_back);
        tb_go= (TextView)findViewById(R.id.tb_go);
        tb_et_search= (EditText)findViewById(R.id.tb_et_search);
        Intent i=getIntent();
        if(i.getStringExtra("searched_key").toString()!=""){
            tb_et_search.setText(i.getStringExtra("searched_key").toString());
        }
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "ic_search/iconfont.ttf");
        tb_back.setTypeface(iconfont);
        tb_et_search.setSelected(true);
        tb_et_search.requestFocus();//获得焦点
        tb_back.setOnClickListener(this);
        tb_go.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tb_back:
                finish();
                break;
            case R.id.tb_go:
                if(TextUtils.isEmpty(tb_et_search.getText())){
                    Toast.makeText(MH_SearchView.this,"输入不能为空",Toast.LENGTH_SHORT).show();
                    break;
                }
                else {
                    Intent i=new Intent(MH_SearchView.this,SearchResult.class);
                    i.putExtra("search_Key",tb_et_search.getText().toString());
                    finish();
                    startActivity(i);
                    break;
                }

        }
    }
}
