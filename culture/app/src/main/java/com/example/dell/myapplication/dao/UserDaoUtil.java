package com.example.dell.myapplication.dao;

import android.content.Context;
import android.util.Log;

import com.example.dell.myapplication.db.DaoSession;
import com.example.dell.myapplication.listener.OnDbUpdateListener;
import com.example.dell.myapplication.module.Msg;
import com.example.dell.myapplication.module.User;

import java.util.List;

public class UserDaoUtil {
    private static final String TAG = UserDaoUtil.class.getSimpleName();
    private DaoManager mManager;
    private OnDbUpdateListener mUpdateListener;

    public void setUpdateListener(OnDbUpdateListener updateListener) {
        mUpdateListener = updateListener;
    }

    public UserDaoUtil(Context context) {
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    public boolean insertUser(User user) {
        boolean flag = false;
        flag = mManager.getDaoSession().getUserDao().insert(user) == -1 ? false : true;

        Log.i(TAG, "insert Msg :" + flag + "-->" + user.toString());
        return flag;
    }

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<User> queryAllUser() {
        return mManager.getDaoSession().loadAll(User.class);
    }

    /***
     * 修改数据
     */
    public void updataUser(User user){
        mManager.getDaoSession().getUserDao().update(user);
    }

}
