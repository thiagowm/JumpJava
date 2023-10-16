package com.jump.test.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Items {
    @SerializedName("itemId")
    public Integer itemId;
    @SerializedName("price")
    public String price;
    @SerializedName("period")
    public Integer period;
    @SerializedName("since")
    public Integer since;
    @SerializedName("establishmentId")
    public Integer establishmentId;
    @SerializedName("typePrice")
    public String typePrice;

    public Items(int itemId, String price, int period, int since, int establishmentId, String typePrice) {
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getSince() {
        return since;
    }

    public void setSince(Integer since) {
        this.since = since;
    }

    public Integer getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(Integer establishmentId) {
        this.establishmentId = establishmentId;
    }

    public String getTypePrice() {
        return typePrice;
    }

    public void setTypePrice(String typePrice) {
        this.typePrice = typePrice;
    }
}
