package com.example.dell.myapplication.module;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class User {
    @Id(autoincrement = true)
    Long id;
    @Unique
    String phone;
    String nickName;
    String password;
    @Generated(hash = 1560258038)
    public User(Long id, String phone, String nickName, String password) {
        this.id = id;
        this.phone = phone;
        this.nickName = nickName;
        this.password = password;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public User(String code){
        String[] element = code.split(",");
        this.id = Long.parseLong(element[0]) ;
        this.phone = element[1];
        this.nickName = element[2];
        this.password = element[3];
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
        return id +","+ phone +","+ nickName +","+ password;
    }

}
