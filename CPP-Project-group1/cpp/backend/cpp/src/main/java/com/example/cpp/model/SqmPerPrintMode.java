package com.example.cpp.model;

import java.sql.Date;

public class SqmPerPrintMode {

    private int machine_id;
    private Date date;
    private float pass_highDensity_1;
    private float pass_1;
    private float pass_2;
    private float pass_4;
    private float pass_8;
    private float pass_highDensity_8;
    private float pass_16;
    private float other;

    public int getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(int machine_id) {
        this.machine_id = machine_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPass_highDensity_1() {
        return pass_highDensity_1;
    }

    public void setPass_highDensity_1(float pass_highDensity_1) {
        this.pass_highDensity_1 = pass_highDensity_1;
    }

    public float getPass_1() {
        return pass_1;
    }

    public void setPass_1(float pass_1) {
        this.pass_1 = pass_1;
    }

    public float getPass_2() {
        return pass_2;
    }

    public void setPass_2(float pass_2) {
        this.pass_2 = pass_2;
    }

    public float getPass_4() {
        return pass_4;
    }

    public void setPass_4(float pass_4) {
        this.pass_4 = pass_4;
    }

    public float getPass_8() {
        return pass_8;
    }

    public void setPass_8(float pass_8) {
        this.pass_8 = pass_8;
    }

    public float getPass_highDensity_8() {
        return pass_highDensity_8;
    }

    public void setPass_highDensity_8(float pass_highDensity_8) {
        this.pass_highDensity_8 = pass_highDensity_8;
    }

    public float getPass_16() {
        return pass_16;
    }

    public void setPass_16(float pass_16) {
        this.pass_16 = pass_16;
    }

    public float getOther() {
        return other;
    }

    public void setOther(float other) {
        this.other = other;
    }

    public SqmPerPrintMode(int machine_id, Date date, float pass_highDensity_1, float pass_1, float pass_2, float pass_4, float pass_8, float pass_highDensity_8, float pass_16, float other) {
        this.machine_id = machine_id;
        this.date = date;
        this.pass_highDensity_1 = pass_highDensity_1;
        this.pass_1 = pass_1;
        this.pass_2 = pass_2;
        this.pass_4 = pass_4;
        this.pass_8 = pass_8;
        this.pass_highDensity_8 = pass_highDensity_8;
        this.pass_16 = pass_16;
        this.other = other;
    }
}
