package com.example.rio.week5hw;

import com.example.rio.week5hw.Interface.IJson;

import io.realm.RealmObject;

public class NDate extends RealmObject implements IJson {
    public static final String DAY = "day";
    public static final String MONTH = "month";
    public static final String YEAR = "year";

    private String day;
    private String month;
    private String year;
    public NDate(){}
    public NDate(String day, String month, String year) {
        super();
        setDay(day);
        setMonth(month);
        setYear(year);
    }
    public enum Month {
        JANUARY("Jan","01"),
        FEBRUARY("Feb","02"),
        MARCH("Mar","03"),
        APRIL("Apr","04"),
        MAY("May","05"),
        JUNE("Jun","06"),
        JULY("Jul","07"),
        AUGUST("Aug","08"),
        SEPTEMBER("Sep","09"),
        OCTOBER("Oct","10"),
        NOVEMBER("Nov","11"),
        DECEMBER("Dec","12");

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }


        public String getNumber() {return number;}
        public void setNumber(String number) {this.number = number;}
        private String code;
        private String number;
        private Month(String code,String number) {
            this.code = code;
            this.number = number;
        }
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }
    public String getMothNumber() {
        for(Month mMonth : Month.values()) {
            if(mMonth.getCode() == month) {
                return mMonth.getNumber();
            }
        }
        return "";
    }
    public void setMonth(String mMonthNumber) {
        for(Month month : Month.values()) {
            if(month.getNumber().equalsIgnoreCase(mMonthNumber) ) {
                this.month = month.getCode();
            }
        }
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    @Override
    public String toJSONString() throws Exception {
        return "["+IOJson.toJSONString(DAY,getDay(),MONTH,getMonth(),YEAR,getYear())+"]";
    }
}
