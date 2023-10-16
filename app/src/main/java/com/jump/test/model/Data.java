package com.jump.test.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

//    @SerializedName("user")
    public User user;
//    @SerializedName("session")
    public Session session;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
