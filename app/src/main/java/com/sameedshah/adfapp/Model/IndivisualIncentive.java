package com.sameedshah.adfapp.Model;

public class IndivisualIncentive {



    String incentiveno,location,quantity,user,value;

    public IndivisualIncentive(){}

    public IndivisualIncentive(String incentiveno, String location, String quantity, String user, String value) {
        this.incentiveno = incentiveno;
        this.location = location;
        this.quantity = quantity;
        this.user = user;
        this.value = value;
    }

    public String getIncentiveno() {
        return incentiveno;
    }

    public void setIncentiveno(String incentiveno) {
        this.incentiveno = incentiveno;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
