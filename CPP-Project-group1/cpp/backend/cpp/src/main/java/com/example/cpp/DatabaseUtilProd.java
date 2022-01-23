package com.example.cpp;

public class DatabaseUtilProd {

    private String url = "jdbc:mysql://studmysql01.fhict.local/dbi454066";
    private String user = "dbi454066";
    private String password = "L-khb}_2EVng+*SG";

    public String GetUrl(){
        return this.url;
    }

    public String GetUser(){
        return this.user;
    }

    public String GetPassword(){
        return this.password;
    }

    public DatabaseUtilProd(){}
}
