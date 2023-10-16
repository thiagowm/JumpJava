package com.jump.test.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {
//    @SerializedName("response")
    public String response;
//    @SerializedName("data")
    public Data data;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
