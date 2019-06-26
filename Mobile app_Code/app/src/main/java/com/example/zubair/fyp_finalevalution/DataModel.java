package com.example.zubair.fyp_finalevalution;

public class DataModel {

    private String LocationPoint;
    private boolean Status;        //false not checked in else checked in
    private String name;


    public DataModel(String LocationPoint, boolean Status,String name ) {
        this.setLocationPoint(LocationPoint);
        this.setStatus(Status);
        this.setName(name);


    }

    public String getLocationPoint() {
        return LocationPoint;
    }

    public boolean getStatus() {
       return Status;
    }


    public void setLocationPoint(String locationPoint) {
        LocationPoint = locationPoint;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}