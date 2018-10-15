package com.yamin.session1.models;

/**
 * Created by Yamin on 10/15/2018.
 */

public class CreditCard {

    String name ;
    String family;

    public CreditCard(String name, String family) {
        this.name = name;
        this.family = family;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}
