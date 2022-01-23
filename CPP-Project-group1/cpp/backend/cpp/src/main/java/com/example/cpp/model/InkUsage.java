package com.example.cpp.model;

import java.sql.Date;


public class InkUsage {

    private String machine_id;
    private Date date;
    private float color_black;
    private float color_cyan;
    private float color_magenta;
    private float color_yellow;


    public String getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(String machine_id) {
        this.machine_id = machine_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getColor_black() {
        return color_black;
    }

    public void setColor_black(float color_black) {
        this.color_black = color_black;
    }

    public float getColor_cyan() {
        return color_cyan;
    }

    public void setColor_cyan(float color_cyan) {
        this.color_cyan = color_cyan;
    }

    public float getColor_magenta() {
        return color_magenta;
    }

    public void setColor_magenta(float color_magenta) {
        this.color_magenta = color_magenta;
    }

    public float getColor_yellow() {
        return color_yellow;
    }

    public void setColor_yellow(float color_yellow) {
        this.color_yellow = color_yellow;
    }

    public InkUsage(String machine_id, Date date, float color_black, float color_cyan, float color_magenta, float color_yellow) {
        this.machine_id = machine_id;
        this.date = date;
        this.color_black = color_black;
        this.color_cyan = color_cyan;
        this.color_magenta = color_magenta;
        this.color_yellow = color_yellow;
    }

    public InkUsage(){

    }

    @Override
    public String toString() {
        return "InkUsage{" +
                "machine_id=" + machine_id +
                ", date=" + date +
                ", color_black=" + color_black +
                ", color_cyan=" + color_cyan +
                ", color_magenta=" + color_magenta +
                ", color_yellow=" + color_yellow +
                '}';
    }
}

