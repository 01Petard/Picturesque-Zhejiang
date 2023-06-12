package com.qingbai.idylls.wode;

public class UserDate {
    private String username;
    private String password;
    private int userId;
    private String address;
    private String situation;
    private String email;

    public UserDate(String username, String password, int userId, String address, String situation, String email) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.address = address;
        this.situation = situation;
        this.email = email;
    }
    public UserDate(){
        super();
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
