package com.qgj.juan_05.netwok.model;

import com.google.gson.annotations.SerializedName;

public class DataModel {
    @Override
    public String toString() {
        return "DataModel{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }

    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private int code;

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
}
