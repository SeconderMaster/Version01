package com.example.lmj.a2hm2;

import cn.bmob.v3.BmobUser;

/**
 * Created by lmj on 2016/9/13.
 */
public class LogOut {
    public static void logout(){
        BmobUser.logOut();   //清除缓存用户对象
        BmobUser currentUser = BmobUser.getCurrentUser(); // 现在的currentUser是null了

    }
}
