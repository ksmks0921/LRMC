package com.ahmedradwan.mylrmc;

import android.support.annotation.NonNull;

public class DrugSample {
    private String name;
    private String quantity_1;
    private String pay_1;
    private String quantity_2;
    private String pay_2;

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPay_1() {
        return pay_1;
    }

    public void setPay_1(String pay_1) {
        this.pay_1 = pay_1;
    }

    public String getPay_2() {
        return pay_2;
    }

    public void setPay_2(String pay_2) {
        this.pay_2 = pay_2;
    }

    public String getQuantity_1() {
        return quantity_1;
    }

    public void setQuantity_1(String quantity_1) {
        this.quantity_1 = quantity_1;
    }

    public String getQuantity_2() {
        return quantity_2;
    }

    public void setQuantity_2(String quantity_2) {
        this.quantity_2 = quantity_2;
    }

    @NonNull
    @Override
    public String toString() {

        return "";
    }
}
