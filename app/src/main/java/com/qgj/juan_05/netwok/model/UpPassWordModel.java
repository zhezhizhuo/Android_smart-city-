package com.qgj.juan_05.netwok.model;

import com.google.gson.annotations.SerializedName;

public class UpPassWordModel {
    @Override
    public String toString() {
        return "UpPassWordModel{" +
                "newPassword='" + newPassword + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                '}';
    }

    public UpPassWordModel() {
    }

    @SerializedName("newPassword")
    private String newPassword;
    @SerializedName("oldPassword")
    private String oldPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
