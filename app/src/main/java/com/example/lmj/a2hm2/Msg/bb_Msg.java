package com.example.lmj.a2hm2.Msg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lmj.a2hm2.R;

/**
 * Created by lmj on 2016/9/11.
 */
public class bb_Msg extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.msg_fragment,container,false);
    }
}
