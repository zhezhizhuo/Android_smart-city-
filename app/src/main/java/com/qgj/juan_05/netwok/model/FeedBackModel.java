package com.qgj.juan_05.netwok.model;

import com.google.gson.annotations.SerializedName;

public class FeedBackModel {
    @Override
    public String toString() {
        return "FeedBackModel{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @SerializedName("content")
    private String content;
    @SerializedName("title")
    private String title;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
