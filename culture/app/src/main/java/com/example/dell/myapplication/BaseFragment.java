package com.example.dell.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment extends Fragment {
    protected Activity mActivity;
    protected Context mContext;

    public BaseFragment(){
        super();
    }// 调用父类具有相同形参的构造方法，//构造方法：new（实例化）时初始化的方法（最开始执行的）

    @Override//让编译器检查是否正确的复写了父类中已有的方法,告诉读代码的人，这是一个复写的方法
    public void onCreate(@Nullable Bundle savedInstanceState) {//@Nullable参数可为空
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
