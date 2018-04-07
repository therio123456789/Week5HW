package com.example.rio.week5hw;


import com.example.rio.week5hw.Interface.IJson;

import io.realm.RealmObject;

public class Act extends RealmObject implements IJson {
    public static final String NAME = "name";
    public static final String DEATHLINE = "deathline";
    public static final String PRIORITY = "priority";
    public static final String STATUS = "status";
    private String name;
    private NDate  deathLine;
    private String priority;
    private String status;
    public Act() { }
    public Act(String name, String day, String month, String year, String priority, String status) {
        super();
        this.name = name;
        this.deathLine = new NDate(day,month,year);
        this.priority = priority;
        this.status = status;
    }

    public String getDeathLineString() {
        return String.format("%s/%s/%s",deathLine.getDay(),deathLine.getMonth(),deathLine.getYear());
    }

    public NDate getDeathLine() {
        return deathLine;
    }

    public void setDeathLine(NDate deathLine) {
        this.deathLine = deathLine;
    }
    public void setDeathline(String day, String month, String year) {
        deathLine.setDay(day);
        deathLine.setMonth(month);
        deathLine.setYear(year);
    }
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toJSONString() throws Exception {
        return IOJson.toJSONString(NAME,getName(),DEATHLINE,deathLine.toJSONString(),PRIORITY,priority,STATUS,status);
    }
}
