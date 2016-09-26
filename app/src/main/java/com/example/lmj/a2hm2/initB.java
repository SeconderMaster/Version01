package com.example.lmj.a2hm2;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import cn.bmob.sms.BmobSMS;
import cn.bmob.v3.Bmob;

/**
 * Created by lmj on 2016/9/13.
 */
public class initB extends Application {
    ImageLoaderConfiguration select_picture_configuration;
   public static  DisplayImageOptions select_picture_options;
    public static ImageLoader imageLoader=ImageLoader.getInstance();
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "c6e1df6871ef4b437d5624f77c3ff91c");
        BmobSMS.initialize(this,"c6e1df6871ef4b437d5624f77c3ff91c");
        initImageLoader();
    }
    private void initImageLoader() {
        //创建默认的ImageLoader配置参数
//        select_picture_configuration = ImageLoaderConfiguration
        select_picture_configuration = new ImageLoaderConfiguration
                .Builder(this)
                .memoryCacheExtraOptions(480, 480)
                .threadPoolSize(4)
                .discCacheFileCount(50)
                .memoryCache(new WeakMemoryCache())
                .build()
                .createDefault(this);
        //初始化ImageLoader
//        ImageLoader.getInstance().init(select_picture_configuration);
        imageLoader.init(select_picture_configuration);
        //显示图片配置
        select_picture_options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.user_head)
                .showImageOnFail(R.drawable.clear)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();
    }//初始化imageloader配置

}
