package com.example.lmj.a2hm2.Community;

import com.example.lmj.a2hm2.My.My_User;

import cn.bmob.v3.BmobObject;

/**
 * Created by wfh on 2016/9/16.
 */
public class Article_comment extends BmobObject {
    private My_User article;
    private String artivle_name;
    private String article_id;

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getArtivle_name() {
        return artivle_name;
    }

    public void setArtivle_name(String artivle_name) {
        this.artivle_name = artivle_name;
    }

    private String comment_content;

    public My_User getArticle() {
        return article;
    }

    public void setArticle(My_User article) {
        this.article = article;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }
}
