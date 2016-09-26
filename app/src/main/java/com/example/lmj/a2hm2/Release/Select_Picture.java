
package com.example.lmj.a2hm2.Release;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lmj.a2hm2.R;
import com.example.lmj.a2hm2.initB;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import java.util.ArrayList;
import java.util.Collections;

public class Select_Picture extends AppCompatActivity implements View.OnClickListener{

    private TextView ic_tb_select_picture_back;
    private TextView select_picture_select_album;
    private RelativeLayout select_picture_confirm_relative;
    private GridView mGridView;
    private GridView msmall_select;
    ArrayList<String> ArrayList_imageUrls;//所有图片URL
    ArrayList<String> select_imageUrls=new ArrayList<>();//所有图片URL
    private int screenWidth;
    private int screenHeight;
    private static final int MENU_SELECT_ALL = 0;//选了几张
    private static final int MENU_UNSELECT_ALL = MENU_SELECT_ALL + 1;
    private TextView mActionText;
    ImageAdapter imageAdapter;
    SelectAdapter mSelectAdapter;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mSelectAdapter.notifyDataSetChanged();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__picture);
        getAndroiodScreenProperty();
        initImageUrls();//获得所有图片的url
        initView();
        imageAdapter=new ImageAdapter(mGridView,this,ArrayList_imageUrls,select_imageUrls,mHandler);
        mSelectAdapter=new SelectAdapter(msmall_select,this,select_imageUrls);
        mGridView.setAdapter(imageAdapter);
        msmall_select.setAdapter(mSelectAdapter);
        mGridView.setOnScrollListener(new PauseOnScrollListener(
                new initB().imageLoader, true, true));
        msmall_select.setOnScrollListener(new PauseOnScrollListener(
                new initB().imageLoader, true, true));
        Toast.makeText(this,ArrayList_imageUrls.size()+"",Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        ic_tb_select_picture_back= (TextView) findViewById(R.id.ic_tb_select_picture_back);
        select_picture_select_album= (TextView) findViewById(R.id.select_picture_select_album);
        select_picture_confirm_relative= (RelativeLayout) findViewById(R.id.select_picture_confirm_relative);

        mGridView= (GridView) findViewById(R.id.select_picture_gridView);
        msmall_select= (GridView) findViewById(R.id.small_select);
//        mGridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
//        mGridView.setMultiChoiceModeListener(this);

        Typeface iconfont = Typeface.createFromAsset(getAssets(), "ic_search/iconfont.ttf");
        ic_tb_select_picture_back.setTypeface(iconfont);

        ic_tb_select_picture_back.setOnClickListener(this);
        select_picture_select_album.setOnClickListener(this);
        select_picture_confirm_relative.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ic_tb_select_picture_back:
                Select_Picture.this.finish();
                break;
            case R.id.select_picture_select_album:
                Toast.makeText(Select_Picture.this,"选择相册",Toast.LENGTH_SHORT).show();
                break;
            case R.id.select_picture_confirm_relative:
                Toast.makeText(Select_Picture.this,"确定",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void initImageUrls() {
        ArrayList_imageUrls=new ArrayList<>();
        String[] proj = {MediaStore.Images.Media.DATA};
        ContentResolver contentResolver=this.getContentResolver();
        Cursor cursor=contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                proj,null,null, null);
        cursor.getCount();
        while(cursor.moveToNext()) {
            ArrayList_imageUrls.add(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
            System.out.println(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
        }
        Collections.reverse(ArrayList_imageUrls);
        cursor.close();
    }//获取所有图片url
    public void getAndroiodScreenProperty(){
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;// 屏幕宽度（像素）
        int height= dm.heightPixels; // 屏幕高度（像素）
        float density = dm.density;//屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;//屏幕密度dpi（120 / 160 / 240）
        //屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        screenWidth = (int) (width/density);//屏幕宽度(dp)
        screenHeight = (int)(height/density);//屏幕高度(dp)
    }

}
