package com.qgj.juan_05.netwok.model;

import com.google.gson.annotations.SerializedName;

public class BaoMingModel {

    @SerializedName("msg")
    private String msg;
    @SerializedName("isSignup")
    private boolean isSignup;
    @SerializedName("code")
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isIsSignup() {
        return isSignup;
    }

    public void setIsSignup(boolean isSignup) {
        this.isSignup = isSignup;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
