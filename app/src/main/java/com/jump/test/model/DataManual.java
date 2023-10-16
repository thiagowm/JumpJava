package com.jump.test.model;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DataManual {
    @SerializedName("establishmentSettings")
    public Establishment establishmentSettings;
    @SerializedName("paymentMethods")
    public ArrayList<PaymentMethods> paymentMethods;
    @SerializedName("prices")
    public ArrayList<Prices> prices;

}
