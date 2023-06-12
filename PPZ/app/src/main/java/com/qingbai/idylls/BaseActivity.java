package com.qingbai.idylls;

import androidx.appcompat.app.AppCompatActivity;

import com.qingbai.idylls.util.SharedPreferencesUtil;


public class BaseActivity extends AppCompatActivity {

    protected SharedPreferencesUtil sp;

    /**
     * 重写了setContentView方法
     * 因为在子类调用了setContentView设置布局
     * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        //配置文件
        sp = SharedPreferencesUtil.getInstance(getApplicationContext());
    }
    //其他的公共逻辑也可以这样重构

}
