package com.qingbai.idylls;

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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.qingbai.idylls.wode.Chat.ChatActivity;
import com.qingbai.idylls.wode.LoginActivity;
import com.qingbai.idylls.wode.WodeShuomingActivity;
import com.qingbai.idylls.zhejiang.ZhejiangActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    private static final String TAG = "MainActivity";

    private DrawerLayout mDrawerLayout;

    private static final int toLogin = 0;


    CircleImageView login_icon;
    TextView username;
    TextView mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
//        ActionBar actionBar = getSupportActionBar();
//        if(actionBar != null){
//            actionBar.setDisplayHomeAsUpEnabled(false);
            //修改HomeAsUp按钮的图标
//            actionBar.setHomeAsUpIndicator(R.drawable.list);
//        }

        //设置DawerLayout的弹出事件
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
//        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
//        drawerToggle.setDrawerIndicatorEnabled(true);
//        drawerToggle.setHomeAsUpIndicator(R.mipmap.ic_launcher);//channge the icon,改变图标
//        drawerToggle.syncState();////show the default icon and sync the DrawerToggle state,如果你想改变图标的话，这句话要去掉。这个会使用默认的三杠图标
//        mDrawerLayout.setDrawerListener(drawerToggle);//关联 drawerlayout
//        mDrawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));//设置状态栏颜色

        //设置NavigationView中的点击事件
        navView.setNavigationItemSelectedListener(this);

        //底部的导航按钮NavigationButton
        BottomNavigationView bottomnavView = findViewById(R.id.bottom_nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications).build();
        //设置Navi控制器
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //切换ActionBar的文字，这里已经没ActionBar，所以不用
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //通过按不同的NavigationButton切换不同的界面
        NavigationUI.setupWithNavController(bottomnavView, navController);

        //做用户登录的操作，在ZhejiangActivity已经做过了，这里不再做，直接设置图片和名字、邮箱
        RelativeLayout headView = (RelativeLayout) navView.getHeaderView(0);
        login_icon = headView.findViewById(R.id.login_icon);
        username = headView.findViewById(R.id.username);
        mail = headView.findViewById(R.id.mail);
//        login_icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                startActivityForResult(intent,toLogin);
//            }
//        });
        login_icon.setImageResource(R.drawable.img01);
        username.setText(R.string.username);
        mail.setText(R.string.mail);
    }

    /**
     * 从LoginActivity返回来的结果，这里因为在ZhejiangActivity已经演示登录了，所以不再做，直接设置图片和名字、邮箱
     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case toLogin:
//                int login_success = data.getIntExtra("login_success",1);
//                if(resultCode == RESULT_OK){
//                    login_icon.setImageResource(R.drawable.img01);
//                    username.setText(R.string.username);
//                    mail.setText(R.string.mail);
//                    Log.i(TAG,"您已登录！");
//                }
//                break;
//        }
//    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        //FloatingActionButton的点击事件
//        Snackbar.make(v,"删除数据",Snackbar.LENGTH_SHORT)
//                .setAction("撤销", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(MainActivity.this, "恢复数据", Toast.LENGTH_SHORT).show();
//                    }
//                }).show();
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
                intent = new Intent(MainActivity.this,ImageActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_gallery:
                intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                startActivity(intent);
                break;
            case R.id.zhejiang_intro:
                intent = new Intent(MainActivity.this, ZhejiangActivity.class);
                intent.putExtra("open_zhejiangyilan",1);
                startActivity(intent);
                break;
            case R.id.nav_intro:
                intent = new Intent(MainActivity.this, WodeShuomingActivity.class);//跳到“使用说明”页面
                startActivity(intent);
                break;
            case R.id.nav_kefu:
                intent = new Intent(MainActivity.this, ChatActivity.class);//跳到“问题反馈”页面
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
                /*
                OnekeyShare oks = new OnekeyShare();
                // title标题，微信、QQ和QQ空间等平台使用
                oks.setTitle(getString(R.string.share));
                // titleUrl QQ和QQ空间跳转链接
                oks.setTitleUrl("http://sharesdk.cn");
                // text是分享文本，所有平台都需要这个字段
                oks.setText("我是分享文本");
                // imagePath是图片的本地路径，确保SDcard下面存在此张图片
                oks.setImagePath("/sdcard/test.jpg");
//                // url在微信、Facebook等平台中使用
//            oks.setUrl("http://sharesdk.cn");
                // 启动分享GUI
                oks.show(this);
                */
                break;
            default:
                Toast.makeText(this, "无效的NagationItem菜单选项", Toast.LENGTH_SHORT).show();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /***
     * 创建OptionMenu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    /**
     * 处理OptionMenu的点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.exchange_city:
                showProgressBar();
                break;
        }
        return true;
    }

    public void showProgressBar(){
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_progressbar, null);
        ProgressBar pb = view.findViewById(R.id.pb_custome);
        TextView title = view.findViewById(R.id.tv_custome);
        title.setText("正在打开地图……");
        //给ProgressBar添加动画效果
        pb.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.icon_progress));
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
                intent.setClass(MainActivity.this, SelectCity.class);
                startActivity(intent);
                finish();
                dialog.dismiss();
                t.cancel();
            }
        },500);
    }
}
