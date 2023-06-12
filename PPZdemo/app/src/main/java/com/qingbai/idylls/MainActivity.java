package com.qingbai.idylls;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    private static final String TAG = "MainActivity";

    private DrawerLayout mDrawerLayout;

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

    }


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
        switch(menuItem.getItemId()){
            case R.id.nav_camera:
                Log.i(TAG,"nav_image被点击");
                Toast.makeText(this, "nav_image", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(MainActivity.this,ImageActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_gallery:
                Toast.makeText(this, "nav_mail", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_slideshow:
                Toast.makeText(this, "nav_open", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_manage:
                Log.i(TAG,"nav_robot被点击");
                Toast.makeText(this, "nav_robot", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_share:
                //这里改成那个apk的路径
                Uri uri = Uri.parse("/storage/emulated/0/DCIM/Screenshots/IMG_20191222_172336.jpg");
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
                Toast.makeText(this, "切换城市", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
