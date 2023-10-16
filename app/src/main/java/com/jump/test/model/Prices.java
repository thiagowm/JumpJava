package com.jump.test.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Prices {
    @SerializedName("establishmentId")
    public Integer establishmentId;
    @SerializedName("typePrice")
    public String typePrice;
    @SerializedName("covenant")
    public Integer covenant;
    @SerializedName("invisible")
    public Integer invisible;
    @SerializedName("major")
    public Integer major;
    @SerializedName("tolerance")
    public Integer tolerance;
    @SerializedName("maximumPeriod")
    public Integer maximumPeriod;
    @SerializedName("maximumValue")
    public String maximumValue;
    @SerializedName("items")
    public ArrayList<Items> items;

}
