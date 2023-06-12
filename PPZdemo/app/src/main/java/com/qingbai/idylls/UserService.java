package com.qingbai.idylls;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserService {
    private DbHelper dbHelper;
    public UserService(Context context){
        dbHelper = new DbHelper(context);
    }

    //用户登录
    public boolean Login(String username,String password){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        String sql = "Select * from user where username = ? and password = ?";
        Cursor rawQuery = sqLiteDatabase.rawQuery(sql, new String[]{username,password});
        if (rawQuery.moveToFirst()==true){
            String sql1 = "update user set situation=1 where username = ? and password = ?";
            Cursor raw = sqLiteDatabase.rawQuery(sql1,new String[]{username,password});
            rawQuery.close();
            return true;
        }
        return false;
    }

    //用户注册
    public boolean Register(UserDate user){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        String sql = "insert into user (username,password,email,address,situation) values (?,?,?,?,0)";
        Object obj[] = { user.getUsername(), user.getPassword(),user.getEmail(),user.getAddress(),user.getSituation() };
        sqLiteDatabase.execSQL(sql, obj);
        return true;
    }
}
