package com.example.lmj.a2hm2;

import cn.bmob.v3.BmobUser;

/**
 * Created by lmj on 2016/9/13.
 */
public class isLogin {
    private static boolean isLogin;
    public static boolean isLogin(){
        BmobUser bmobUser=BmobUser.getCurrentUser();
        if(bmobUser!=null){
            isLogin=true;
        }
        else isLogin=false;
        return isLogin;
    }
}
