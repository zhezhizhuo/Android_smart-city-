package com.qgj.juan_05.netwok.model;

import com.google.gson.annotations.SerializedName;

public class LoginModel {


    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private int code;
    @SerializedName("token")
    private String token;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", token='" + token + '\'' +
                '}';
    }
}
