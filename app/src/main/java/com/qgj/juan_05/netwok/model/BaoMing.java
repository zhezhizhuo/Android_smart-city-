package com.qgj.juan_05.netwok.model;

import com.google.gson.annotations.SerializedName;

public class BaoMing {

    @SerializedName("activityId")
    private int activityId;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
}
