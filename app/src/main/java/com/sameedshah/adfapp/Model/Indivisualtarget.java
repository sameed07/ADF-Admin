package com.sameedshah.adfapp.Model;

public class Indivisualtarget {


    String done, less_value, location, target, vs_target, targetperday;


    public Indivisualtarget() {
    }


    public Indivisualtarget(String done, String less_value, String location, String target, String vs_target, String targetperday) {
        this.done = done;
        this.less_value = less_value;
        this.location = location;
        this.target = target;
        this.vs_target = vs_target;
        this.targetperday = targetperday;
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

    public String getVs_target() {
        return vs_target;
    }

    public void setVs_target(String vs_target) {
        this.vs_target = vs_target;
    }

    public String getTargetperday() {
        return targetperday;
    }

    public void setTargetperday(String targetperday) {
        this.targetperday = targetperday;
    }
}