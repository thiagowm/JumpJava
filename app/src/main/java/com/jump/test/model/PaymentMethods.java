package com.jump.test.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PaymentMethods {

    @SerializedName("establishmentPaymentMethodId")
    public Integer establishmentPaymentMethodId;
    @SerializedName("paymentMethodName")
    public String paymentMethodName;
    @SerializedName("primitivePaymentMethodId")
    public Integer primitivePaymentMethodId;
    @SerializedName("receivingDays")
    public Integer receivingDays;
    @SerializedName("receivingFee")
    public String receivingFee;
    @SerializedName("accountId")
    public Integer accountId;

    public PaymentMethods(Integer establishmentPaymentMethodId, String paymentMethodName, Integer primitivePaymentMethodId, Integer receivingDays, String receivingFee, Integer accountId) {
    }

    public Integer getEstablishmentPaymentMethodId() {
        return establishmentPaymentMethodId;
    }

    public void setEstablishmentPaymentMethodId(Integer establishmentPaymentMethodId) {
        this.establishmentPaymentMethodId = establishmentPaymentMethodId;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public Integer getPrimitivePaymentMethodId() {
        return primitivePaymentMethodId;
    }

    public void setPrimitivePaymentMethodId(Integer primitivePaymentMethodId) {
        this.primitivePaymentMethodId = primitivePaymentMethodId;
    }

    public Integer getReceivingDays() {
        return receivingDays;
    }

    public void setReceivingDays(Integer receivingDays) {
        this.receivingDays = receivingDays;
    }

    public String getReceivingFee() {
        return receivingFee;
    }

    public void setReceivingFee(String receivingFee) {
        this.receivingFee = receivingFee;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
