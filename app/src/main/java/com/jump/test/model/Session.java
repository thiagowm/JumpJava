package com.jump.test.model;

import com.google.gson.annotations.SerializedName;

public class Session {

    @SerializedName("sessionId")
    public Integer sessionId;
    @SerializedName("establishmentId")
    public Integer establishmentId;
    @SerializedName("startDateTime")
    public String startDateTime;
    @SerializedName("endDateTime")
    public String  endDateTime;
    @SerializedName("userId")
    public Integer userId;
    @SerializedName("active")
    public Integer active;
    @SerializedName("versionName")
    public String versionName;
    @SerializedName("status")
    public Integer status;
    @SerializedName("created_at")
    public String created_at;
    @SerializedName("updated_at")
    public String updated_at;
}
