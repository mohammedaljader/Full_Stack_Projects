package com.example.cpp.model;

import java.sql.Date;

public class MediaCategory {

    private String machine_id;
    private Date date;
    private float film;
    private float lightPaper;
    private float heavyPaper;
    private float lightBanner;
    private float textile;
    private float monomericVinyl;
    private float canvas;
    private float polymericCastVinyl;
    private float heavyBanner;
    private float paper;
    private float thickFilm;

    public MediaCategory(String machine_id, Date date, float film, float lightPaper, float heavyPaper, float lightBanner, float textile, float monomericVinyl, float canvas, float polymericCastVinyl, float heavyBanner, float paper, float thickFilm) {
        this.machine_id = machine_id;
        this.date = date;
        this.film = film;
        this.lightPaper = lightPaper;
        this.heavyPaper = heavyPaper;
        this.lightBanner = lightBanner;
        this.textile = textile;
        this.monomericVinyl = monomericVinyl;
        this.canvas = canvas;
        this.polymericCastVinyl = polymericCastVinyl;
        this.heavyBanner = heavyBanner;
        this.paper = paper;
        this.thickFilm = thickFilm;
    }



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

    public float getFilm() {
        return film;
    }

    public void setFilm(float film) {
        this.film = film;
    }

    public float getLightPaper() {
        return lightPaper;
    }

    public void setLightPaper(float lightPaper) {
        this.lightPaper = lightPaper;
    }

    public float getHeavyPaper() {
        return heavyPaper;
    }

    public void setHeavyPaper(float heavyPaper) {
        this.heavyPaper = heavyPaper;
    }

    public float getLightBanner() {
        return lightBanner;
    }

    public void setLightBanner(float lightBanner) {
        this.lightBanner = lightBanner;
    }

    public float getTextile() {
        return textile;
    }

    public void setTextile(float textile) {
        this.textile = textile;
    }

    public float getMonomericVinyl() {
        return monomericVinyl;
    }

    public void setMonomericVinyl(float monomericVinyl) {
        this.monomericVinyl = monomericVinyl;
    }

    public float getCanvas() {
        return canvas;
    }

    public void setCanvas(float canvas) {
        this.canvas = canvas;
    }

    public float getPolymericCastVinyl() {
        return polymericCastVinyl;
    }

    public void setPolymericCastVinyl(float polymericCastVinyl) {
        this.polymericCastVinyl = polymericCastVinyl;
    }

    public float getHeavyBanner() {
        return heavyBanner;
    }

    public void setHeavyBanner(float heavyBanner) {
        this.heavyBanner = heavyBanner;
    }

    public float getPaper() {
        return paper;
    }

    public void setPaper(float paper) {
        this.paper = paper;
    }

    public float getThickFilm() {
        return thickFilm;
    }

    public void setThickFilm(float thickFilm) {
        this.thickFilm = thickFilm;
    }



}
