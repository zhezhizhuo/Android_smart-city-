package com.qgj.juan_05.netwok.model;

import com.google.gson.annotations.SerializedName;

public class AddCardModel {

    @SerializedName("address")
    private String address;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("cardId")
    private String cardId;
    @SerializedName("name")
    private String name;
    @SerializedName("sex")
    private int sex;
    @SerializedName("tel")
    private String tel;

    public AddCardModel() {
    }

    public AddCardModel(String address, String birthday, String cardId, String name, int sex, String tel) {
        this.address = address;
        this.birthday = birthday;
        this.cardId = cardId;
        this.name = name;
        this.sex = sex;
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
