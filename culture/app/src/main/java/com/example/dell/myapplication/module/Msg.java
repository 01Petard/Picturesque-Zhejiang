package com.example.dell.myapplication.module;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by Mr.sorrow on 2017/5/6.
 */

@Entity
public class Msg {

    public static final int TYPE_BLE = 0;
    public static final int TYPE_PHONE = 1;

    @Id(autoincrement = true)
    private Long _id;
    @NotNull
    private String content;
    @NotNull
    private String time;
    @NotNull
    private String nickName;
    @NotNull
    private Long id;
    @NotNull
    int type = 0;


    @Generated(hash = 1099719101)
    public Msg(Long _id, @NotNull String content, @NotNull String time,
            @NotNull String nickName, @NotNull Long id, int type) {
        this._id = _id;
        this.content = content;
        this.time = time;
        this.nickName = nickName;
        this.id = id;
        this.type = type;
    }


    @Generated(hash = 23037457)
    public Msg() {
    }

    public Msg(Long _id, @NotNull String content, @NotNull String time,
               @NotNull String nickName, @NotNull Long id) {
        this._id = _id;
        this.content = content;
        this.time = time;
        this.nickName = nickName;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "_id=" + _id +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }


    public Long get_id() {
        return this._id;
    }


    public void set_id(Long _id) {
        this._id = _id;
    }


    public String getContent() {
        return this.content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public String getTime() {
        return this.time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public String getNickName() {
        return this.nickName;
    }


    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public int getType() {
        return this.type;
    }


    public void setType(int type) {
        this.type = type;
    }
}
