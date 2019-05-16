package com.sameedshah.adfapp.Model;

public class TargetModel {
    String location;
    String target;
    String done;
    String less_value;
    String vs_target;
    String last_year;
    String trend;


    public TargetModel() {
    }

    public TargetModel(String location, String target, String done, String less_value, String vs_target, String last_year, String trend) {
        this.location = location;
        this.target = target;
        this.done = done;
        this.less_value = less_value;
        this.vs_target = vs_target;
        this.last_year = last_year;
        this.trend = trend;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getLess_value() {
        return less_value;
    }

    public void setLess_value(String less_value) {
        this.less_value = less_value;
    }

    public String getVs_target() {
        return vs_target;
    }

    public void setVs_target(String vs_target) {
        this.vs_target = vs_target;
    }

    public String getLast_year() {
        return last_year;
    }

    public void setLast_year(String last_year) {
        this.last_year = last_year;
    }

    public String getTrend() {
        return trend;
    }

    public void setTrend(String trend) {
        this.trend = trend;
    }
}
