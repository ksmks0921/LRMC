package com.ahmedradwan.mylrmc;

import android.support.annotation.NonNull;

public class DrugSampleFree {
    private String name;
    private String quantity_1;


    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity_1;
    }

    public void setQuantity(String quantity_1) {
        this.quantity_1 = quantity_1;
    }

    @NonNull
    @Override
    public String toString() {

        return "";
    }
}
