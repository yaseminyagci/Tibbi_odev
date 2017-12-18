package com.mathheals.tibbi_odev;

/**
 * Created by user on 30.11.2017.
 */

public class head {
private String headID;
    private String derece;
    private int sure;
    private String date;

    public head(){}
    public head(String headID,String date,int sure,String derece){
        this.date=date;
        this.headID=headID;
        this.sure=sure;
        this.derece=derece;

    }

    public String getHeadID() {
        return headID;
    }

    public void setHeadID(String headID) {
        this.headID = headID;
    }

    public String getDerece() {
        return derece;
    }

    public void setDerece(String derece) {
        this.derece = derece;
    }

    public int getSure() {
        return sure;
    }

    public void setSure(int sure) {
        this.sure = sure;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
