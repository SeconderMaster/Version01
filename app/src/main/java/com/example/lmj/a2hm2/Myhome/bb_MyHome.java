package com.example.lmj.a2hm2.Myhome;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lmj.a2hm2.R;
import com.youth.banner.Banner;

/**
 * Created by lmj on 2016/9/11.
 */
public class bb_MyHome extends Fragment implements View.OnClickListener {
    private View v_myhome;
    private TextView tb_scan;
    private TextView tb_list;
    private TextView tb_search;
    private Banner banner;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v_myhome=inflater.inflate(R.layout.myhome_fragment,container,false);
        initView();
        banner= (Banner) v_myhome.findViewById(R.id.banner_myhome);
        String[] images = getResources().getStringArray(R.array.url);
        banner.setImages(images);
        return v_myhome;
    }

    private void initView() {
        tb_scan= (TextView) v_myhome.findViewById(R.id.tb_scan);
        tb_list= (TextView) v_myhome.findViewById(R.id.tb_list);
        tb_search= (TextView) v_myhome.findViewById(R.id.tb_search);
        Typeface iconfont = Typeface.createFromAsset(getActivity().getAssets(), "ic_myhome_topbar/iconfont.ttf");
        tb_scan.setTypeface(iconfont);
        tb_list.setTypeface(iconfont);
        tb_search.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tb_search:
                Intent i=new Intent(getActivity(),MH_SearchView.class);
                i.putExtra("searched_key","");
                startActivity(i);
                break;
        }
    }
}
