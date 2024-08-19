package com.example.clientsearchdemo.models;

public class SearchResults {

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public SearchResults(String fName, String lName, String srf) {
        this.fName = fName;
        this.lName = lName;
        this.srf = srf;
    }

    String fName;
    String lName;

    public String getSrf() {
        return srf;
    }

    public void setSrf(String srf) {
        this.srf = srf;
    }

    String srf;
}
