package com.qgj.juan_05.netwok.model;

import com.google.gson.annotations.SerializedName;

public class SendMoviePlModel {

    @SerializedName("content")
    private String content;
    @SerializedName("movieId")
    private int movieId;
    @SerializedName("score")
    private Float score;

    @Override
    public String toString() {
        return "SendMoviePlModel{" +
                "content='" + content + '\'' +
                ", movieId=" + movieId +
                ", score=" + score +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
