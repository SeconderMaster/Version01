package com.example.lmj.a2hm2.Community;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lmj.a2hm2.Adapter.Community_article_adapter;
import com.example.lmj.a2hm2.R;
import com.example.lmj.a2hm2.Release.Release_essary;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by lmj on 2016/9/11.
 */
public class BB_Community extends Fragment implements View.OnClickListener {
    private TextView community_add_btn;
    private View ic_community_add;
    private ArrayList<Community_article> marticle=new ArrayList<>();
    private Community_article_adapter madapter;
    private ListView community_view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ic_community_add = inflater.inflate(R.layout.community_fragment, container, false);
        initview();
        initlistener();
        initgetdata();
        return ic_community_add;


    }

    private void initgetdata() {
        new getdate().execute();

    }

    private void initlistener() {
        community_add_btn.setOnClickListener(this);
    }

    private void initview() {
        community_view= (ListView) ic_community_add.findViewById(R.id.lv_community);
        Typeface iconfont = Typeface.createFromAsset(getActivity().getAssets(), "ic_community_add/iconfont.ttf");
        community_add_btn = (TextView) ic_community_add.findViewById(R.id.tv_community_add);
        community_add_btn.setTypeface(iconfont);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_community_add:
                Intent it=new Intent(getActivity(), Release_essary.class);
                startActivity(it);
                break;
        }
    }
    class getdate extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            BmobQuery<Community_article> query=new BmobQuery<>();
            query.setLimit(9);
            query.findObjects(new FindListener<Community_article>() {
                @Override
                public void done(List<Community_article> list, BmobException e) {
                    if (e == null) {
                        Toast.makeText(getActivity(), "下载成功", Toast.LENGTH_SHORT).show();
                        if (list != null) {
                            marticle.clear();
                            marticle.addAll(list);
                        }
                        madapter=new Community_article_adapter(marticle,getContext());
                        community_view.setAdapter(madapter);
                    } else {
                        Log.i("wfh", "失败：" + e.getMessage() + "," + e.getErrorCode());
                    }
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }
}
