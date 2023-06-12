package com.qingbai.idylls.zhejiang;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.qingbai.idylls.ImageActivity;
import com.qingbai.idylls.MainActivity;
import com.qingbai.idylls.R;
import com.qingbai.idylls.wode.Chat.ChatActivity;
import com.qingbai.idylls.SelectCity;
import com.qingbai.idylls.wode.LoginActivity;
import com.qingbai.idylls.wode.WodeShuomingActivity;

import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class ZhejiangActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private static final String TAG = "ZhejiangActivity";

    private DrawerLayout mDrawerLayout;

    private static final int toLogin = 0;

    CircleImageView login_icon;
    TextView username;
    TextView mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhejiang);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        //设置DawerLayout的弹出事件
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //设置NavigationView中的点击事件
        navView.setNavigationItemSelectedListener(this);

        LinearLayout ll1 = findViewById(R.id.ll_zhejiang_1);
        LinearLayout ll2 = findViewById(R.id.ll_zhejiang_2);
        LinearLayout ll3 = findViewById(R.id.ll_zhejiang_3);
        LinearLayout ll4 = findViewById(R.id.ll_zhejiang_4);

        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        ll4.setOnClickListener(this);


        //做用户登录的操作
        RelativeLayout headView = (RelativeLayout) navView.getHeaderView(0);
        login_icon = headView.findViewById(R.id.login_icon);
        username = headView.findViewById(R.id.username);
        mail = headView.findViewById(R.id.mail);
        login_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZhejiangActivity.this, LoginActivity.class);
                startActivityForResult(intent,toLogin);
            }
        });
        int open_from_drawer = getIntent().getIntExtra("open_zhejiangyilan",0);
        if(open_from_drawer != 0){
            open_from_drawer();
        }
        Log.i(TAG,"数值是："+open_from_drawer);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.ll_zhejiang_1:
                intent = new Intent(ZhejiangActivity.this,ZhejiangKongjianbantuActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_zhejiang_2:
                intent = new Intent(ZhejiangActivity.this,ZhejiangShilujiaotongtuActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_zhejiang_3:
                intent = new Intent(ZhejiangActivity.this,ZhejiangShirenxingjituActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_zhejiang_4:
                intent = new Intent(ZhejiangActivity.this,ZhejiangWenhuamingchenfenbuActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }

    /***
     * 侧滑栏的菜单点击事件
     * @param menuItem
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent = null;
        switch(menuItem.getItemId()){
            case R.id.nav_camera:
                intent = new Intent(ZhejiangActivity.this, ImageActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_gallery:
                intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                startActivity(intent);
                break;
            case R.id.zhejiang_intro:
                intent = new Intent(ZhejiangActivity.this, ZhejiangActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_intro:
                intent = new Intent(ZhejiangActivity.this, WodeShuomingActivity.class);//跳到“使用说明”页面
                startActivity(intent);
                break;
            case R.id.nav_kefu:
                intent = new Intent(ZhejiangActivity.this, ChatActivity.class);//跳到“问题反馈”页面
                startActivity(intent);
                break;
            case R.id.nav_share:
                //这里改成那个apk的路径
                Uri uri = Uri.parse("/storage/emulated/0/DCIM/Screenshots/shareAppImg.png");
                Intent it = new Intent("android.intent.action.SEND");
                it.putExtra("sms_body", "some text");
                it.putExtra("android.intent.extra.STREAM", uri);
                it.setType("image/png/jpg");
                startActivity(it);
                break;
            default:
                Toast.makeText(this, "无效的NagationItem菜单选项", Toast.LENGTH_SHORT).show();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.exchange_city:
                showProgressBar();
                break;
        }
        return true;
    }

    public void showProgressBar(){
        View view = LayoutInflater.from(ZhejiangActivity.this).inflate(R.layout.layout_progressbar, null);
        ProgressBar pb = view.findViewById(R.id.pb_custome);
        TextView title = view.findViewById(R.id.tv_custome);
        title.setText("正在打开地图……");
        //给ProgressBar添加动画效果
        pb.setAnimation(AnimationUtils.loadAnimation(ZhejiangActivity.this, R.anim.icon_progress));
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view)
//                .setTitle("正在打开地图……")
//                .setPositiveButton(" ", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SelectCity.this, "确定加载", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setNegativeButton(" ", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SelectCity.this, "取消加载", Toast.LENGTH_SHORT).show();
//                    }
//                })
                .setCancelable(true);
        final AlertDialog dialog = builder.create();
        dialog.show();
        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(ZhejiangActivity.this, SelectCity.class);
                startActivity(intent);
                finish();
                dialog.dismiss();
                t.cancel();
            }
        },1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case toLogin:
                int login_success = data.getIntExtra("login_success",1);
                if(resultCode == RESULT_OK){
                    login_icon.setImageResource(R.drawable.img01);
                    username.setText(R.string.username);
                    mail.setText(R.string.mail);
                    Log.i(TAG,"您已登录！");
                }
                break;
        }
    }

    public void open_from_drawer(){
        login_icon.setImageResource(R.drawable.img01);
        username.setText(R.string.username);
        mail.setText(R.string.mail);
    }


}