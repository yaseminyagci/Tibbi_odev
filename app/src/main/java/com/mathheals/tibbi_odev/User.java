package com.mathheals.tibbi_odev;

/**
 * Created by user on 14.10.2017.
 */

public class User {
    private String userID;
    private String user_name;
    private String user_email;
    private String password;
    private String Doktor;
    private String hastane;
    private String Resim;

    public String userID(){return userID;};
    public String getUser_name(){
        return user_name;
}
    public String getUser_email(){
    return user_email;
}
    public String getPassword(){
    return password;
}
   // public String getResim(){return Resim;}

    public String getDoktor() {
        return Doktor;
    }

    public void setDoktor(String doktor) {
        Doktor = doktor;
    }

    public String getHastane() {
        return hastane;
    }

    public String getResim() {
        return Resim;
    }

    public void setResim(String resim) {
        Resim = resim;
    }

    public void setHastane(String hastane) {
        this.hastane = hastane;
    }

    public User(){}
    public void setUserID(String userID){this.userID=userID;}
    public void setUser_name(String user_name){this.user_name=user_name;}
    public void setUser_email(String user_email){this.user_email=user_email;}
    public void setPassword(String password){this.password=password;}
    //public void setResim(String Resim){this.Resim=Resim;}
    public User(String userID,String user_name, String user_email, String password,String Doktor,String hastane) {

        this.userID = userID;
        this.user_name = user_name;
        this.user_email = user_email;
        this.password = password;
        this.Doktor=Doktor;
        this.hastane=hastane;
       // this.Resim=Resim;
    }
}