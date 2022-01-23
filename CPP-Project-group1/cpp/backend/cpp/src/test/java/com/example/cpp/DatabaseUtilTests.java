package com.example.cpp;

public class DatabaseUtilTests {

    private String url = "jdbc:mysql://studmysql01.fhict.local/dbi453277";
    private String user = "dbi453277";
    private String password = "lazytown";

    public String GetUrl(){
        return this.url;
    }

    public String GetUser(){
        return this.user;
    }

    public String GetPassword(){
        return this.password;
    }

    public DatabaseUtilTests(){}
}
