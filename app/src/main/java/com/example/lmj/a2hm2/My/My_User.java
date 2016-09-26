package com.example.lmj.a2hm2.My;

import cn.bmob.v3.BmobUser;

/**
 * Created by lmj on 2016/9/13.
 */
public class My_User extends BmobUser {

    public String getname(){
        return getUsername();
    }
}
