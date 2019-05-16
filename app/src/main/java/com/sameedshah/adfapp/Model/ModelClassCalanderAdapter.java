package com.sameedshah.adfapp.Model;

public class ModelClassCalanderAdapter {

    String id, calanderTitle, calanderSubTitle,date, dayofmonth, month, time;

    public ModelClassCalanderAdapter() {

    }

    public ModelClassCalanderAdapter(String calanderTitle, String calanderSubTitle, String date, String dayofmonth, String month, String time) {
        this.calanderTitle = calanderTitle;
        this.calanderSubTitle = calanderSubTitle;
        this.date = date;
        this.dayofmonth = dayofmonth;
        this.month = month;
        this.time = time;
    }

    public ModelClassCalanderAdapter(String id, String calanderTitle, String calanderSubTitle, String date, String dayofmonth, String month, String time) {
        this.id = id;
        this.calanderTitle = calanderTitle;
        this.calanderSubTitle = calanderSubTitle;
        this.date = date;
        this.dayofmonth = dayofmonth;
        this.month = month;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCalanderTitle() {
        return calanderTitle;
    }

    public void setCalanderTitle(String calanderTitle) {
        this.calanderTitle = calanderTitle;
    }

    public String getCalanderSubTitle() {
        return calanderSubTitle;
    }

    public void setCalanderSubTitle(String calanderSubTitle) {
        this.calanderSubTitle = calanderSubTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayofmonth() {
        return dayofmonth;
    }

    public void setDayofmonth(String dayofmonth) {
        this.dayofmonth = dayofmonth;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}