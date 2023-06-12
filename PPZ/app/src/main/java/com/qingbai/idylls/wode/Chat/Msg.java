package com.qingbai.idylls.wode.Chat;

public class Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;

    private String content;
    private int Icon;
    private String name;
    private int type;

    public Msg(int Icon,String name,String content,int type){
        this.Icon = Icon;
        this.name = name+"：";
        this.content = content;
        this.type = type;
    }
    public String getContent() {
        return content;
    }

    public int getIcon() {
        return Icon;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

}
