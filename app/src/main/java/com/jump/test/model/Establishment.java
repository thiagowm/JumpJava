package com.jump.test.model;

import com.google.gson.annotations.SerializedName;

public class Establishment {

    @SerializedName("establishmentId")
    public Integer establishmentId;
    @SerializedName("cashReserve")
    public String cashReserve;
    @SerializedName("recurrentClientEntryOption")
    public Integer recurrentClientEntryOption;
    @SerializedName("withdrawal")
    public Integer withdrawal;
    @SerializedName("serviceOnly")
    public Integer serviceOnly;
    @SerializedName("print")
    public Integer print;
    @SerializedName("manualCovenant")
    public Integer manualCovenant;
    @SerializedName("manualTime")
    public Integer manualTime;
    @SerializedName("accountId")
    public Integer accountId;
    @SerializedName("prePaidExit")
    public Integer prePaidExit;
    @SerializedName("requireReceiptCovantTypePrice")
    public Integer requireReceiptCovantTypePrice;
    @SerializedName("requireReceiptExpense")
    public Integer requireReceiptExpense;
    @SerializedName("pathLogo")
    public String pathLogo;
    @SerializedName("reserveActivation")
    public Integer reserveActivation;

}
