package com.qingbai.idylls;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.qingbai.idylls.zhejiang.ZhejiangActivity;

/**
 * 启动页面
 *
 * 3秒钟后进入登录界面
 */

public class SplashActivity extends BaseActivity {
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //这个方法在主线程
            super.handleMessage(msg);
            next();
        }
    };

    private void next() {
//        Intent intent;
//        if(sp.isLogin()) {
//            //已经登录，跳转到首页
//            intent = new Intent(this, TabBarActivity.class);
//        }else {
//            //否则就跳转到登录界面
//            intent = new Intent(this, LoginActivity.class);
//        }
        Intent intent = new Intent(this, ZhejiangActivity.class);
        startActivity(intent);
        //关闭当前界面
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //去掉状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //3秒钟后调用这里
                //这里调用是在子线程，不能直接操作UI，需要用handler切换到主线程
                //用多个线程的目的解决，如果有耗时任务，那么就会卡界面
                //而用了多线程后，将耗时任务放到子线程，这样主线程（UI线程）就不会卡住
                handler.sendEmptyMessage(0);
            }
        },1500);//在进入页面停1秒钟
    }
}

