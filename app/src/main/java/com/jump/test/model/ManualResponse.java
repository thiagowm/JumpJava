package com.jump.test.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ManualResponse {
    @SerializedName("response")
    public String response;
    @SerializedName("data")
    public DataManual data;

}
