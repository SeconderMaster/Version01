package com.example.lmj.a2hm2.Community;

import com.example.lmj.a2hm2.My.My_User;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by wfh on 2016/9/16.
 */
public class Community_article extends BmobObject {
    private My_User article;
    private String article_name;


    private BmobFile pic1;
    private BmobFile pic2;
    private BmobFile pic3;
    private BmobFile pic4;
    private BmobFile pic5;
    private String des;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    private String article_title;
    private String comment_num;
    private String thumb_num;

    public My_User getArticle() {
        return article;
    }

    public void setArticle(My_User article) {
        this.article = article;
    }

    public BmobFile getPic1() {
        return pic1;
    }

    public void setPic1(BmobFile pic1) {
        this.pic1 = pic1;
    }

    public BmobFile getPic2() {
        return pic2;
    }

    public void setPic2(BmobFile pic2) {
        this.pic2 = pic2;
    }

    public BmobFile getPic3() {
        return pic3;
    }

    public void setPic3(BmobFile pic3) {
        this.pic3 = pic3;
    }

    public String getArticle_name() {
        return article_name;
    }

    public void setArticle_name(String article_name) {
        this.article_name = article_name;
    }


    public BmobFile getPic4() {
        return pic4;
    }

    public void setPic4(BmobFile pic4) {
        this.pic4 = pic4;
    }

    public BmobFile getPic5() {
        return pic5;
    }

    public void setPic5(BmobFile pic5) {
        this.pic5 = pic5;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getComment_num() {
        return comment_num;
    }

    public void setComment_num(String comment_num) {
        this.comment_num = comment_num;
    }

    public String getThumb_num() {
        return thumb_num;
    }

    public void setThumb_num(String thumb_num) {
        this.thumb_num = thumb_num;
    }
}
