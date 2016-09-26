package com.example.lmj.a2hm2.Release;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lmj.a2hm2.MainActivity;
import com.example.lmj.a2hm2.R;

public class Release extends AppCompatActivity implements View.OnClickListener {
    private TextView ic_tb_release_cancel;
    private TextView ic_tb_release_more;
    private TextView ic_release_add_picture;
    private TextView ic_release_classify;
    private LinearLayout add_picture_linear;
    private RelativeLayout release_classify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        initView();
    }

    private void initView() {
        ic_tb_release_cancel= (TextView) findViewById(R.id.ic_tb_release_cancel);
        ic_tb_release_more= (TextView) findViewById(R.id.ic_tb_release_more);
        ic_release_add_picture= (TextView) findViewById(R.id.ic_release_add_picture);
        ic_release_classify= (TextView) findViewById(R.id.ic_release_classify);
        add_picture_linear= (LinearLayout) findViewById(R.id.add_picture_linear);
        release_classify= (RelativeLayout) findViewById(R.id.release_classify);

        Typeface iconfont = Typeface.createFromAsset(getAssets(), "ic_release/iconfont.ttf");
        ic_tb_release_cancel.setTypeface(iconfont);ic_tb_release_more.setTypeface(iconfont);
        ic_release_add_picture.setTypeface(iconfont);
        Typeface into = Typeface.createFromAsset(getAssets(), "ic_my/iconfont.ttf");
        ic_release_classify.setTypeface(into);


        ic_tb_release_cancel.setOnClickListener(this);
        ic_tb_release_more.setOnClickListener(this);
        add_picture_linear.setOnClickListener(this);
        ic_release_classify.setOnClickListener(this);
        release_classify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ic_tb_release_cancel:
                startActivity(new Intent(Release.this, MainActivity.class));
                Release.this.finish();
                break;
            case R.id.ic_tb_release_more:
                break;
            case R.id.add_picture_linear:
                startActivity(new Intent(Release.this,Select_Picture.class));
                break;
            case R.id.release_classify:
                startActivity(new Intent(Release.this,Release_Classify_List.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Release.this,MainActivity.class));
    }
}
