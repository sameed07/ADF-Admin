package com.sameedshah.adfapp.Model;

public class Individual_Brands {
    private String brand_name;
    private String less_value;
    private String location;
    private String logged;
    private String target;
    private String vstarget;

    public Individual_Brands() {
    }

    public Individual_Brands(String brand_name, String less_value, String location, String logged, String target, String vstarget) {
        this.brand_name = brand_name;
        this.less_value = less_value;
        this.location = location;
        this.logged = logged;
        this.target = target;
        this.vstarget = vstarget;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getLess_value() {
        return less_value;
    }

    public void setLess_value(String less_value) {
        this.less_value = less_value;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLogged() {
        return logged;
    }

    public void setLogged(String logged) {
        this.logged = logged;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getVstarget() {
        return vstarget;
    }

    public void setVstarget(String vstarget) {
        this.vstarget = vstarget;
    }
}
