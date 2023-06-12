package com.example.dell.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.myapplication.adapter.ViewPagerAdapter;
import com.example.dell.myapplication.home.BoardFragment;
import com.example.dell.myapplication.home.HomeFragment;
import com.example.dell.myapplication.home.SetupFragment;
import com.example.dell.myapplication.home.WeatherFragement;
import com.example.dell.myapplication.module.User;
import com.example.dell.myapplication.tool.ToolIntroduce;
import com.example.dell.myapplication.tool.ToolLanguage;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private LinearLayout fmain;
    private LinearLayout fweather;
    private LinearLayout fforum;
    private LinearLayout fsetup;
    private ViewPager mViewpage;
    //fragment管理器
    private FragmentManager mFragmentManager;
    //四个fragment的list集合
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    //适配器，配合ViewPage使用
    private ViewPagerAdapter mAdpter;

    private File cameraSavePath;//拍照照片路径
    private Uri uri;

    NavigationView navigationView;
    View headerLayout;
    TextView nickName;
    ImageView headP;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        headerLayout = navigationView.getHeaderView(0);

        nickName = headerLayout.findViewById(R.id.nickNake);
        headP = headerLayout.findViewById(R.id.imageView);
        headerLayout.findViewById(R.id.user_title).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        adminLogin();

        // 获取系统的fragment管理器
        mFragmentManager = getSupportFragmentManager();
        //初始化4个fragment对象，并且放在一容器中的list
        initFragmentList();

        //适配器
        mAdpter = new ViewPagerAdapter(mFragmentManager, mFragmentList);
        //初始化view（界面容器）

        initView();

        //初始化ViewPager
        initViewpage();

        nickName = headerLayout.findViewById(R.id.nickNake);
        headP = headerLayout.findViewById(R.id.imageView);
        headerLayout.findViewById(R.id.user_title).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adminLogin();
    }

    public void initFragmentList() {
        Fragment mf = new HomeFragment();
        Fragment ff = new WeatherFragement();
        Fragment sf = new BoardFragment();
        Fragment wf = new SetupFragment();
        ((BoardFragment) sf).setUser(user);

        mFragmentList.add(mf);
        mFragmentList.add(ff);
        mFragmentList.add(sf);
        mFragmentList.add(wf);
    }


    public void initViewpage() {
//        设置pageview监听
        mViewpage.addOnPageChangeListener(new ViewPageOnPagerChangeListener());
//        设置适配器
        mViewpage.setAdapter(mAdpter);
//        默认productLL是选中状态，显示productfragment
        mViewpage.setCurrentItem(0);
//        updateBottomLinerLayoutSelect(true,false,false,false);
    }

    //初始化4个导航栏的按钮和ViewPage，并且设置监听
    public void initView() {
        fmain = (LinearLayout) findViewById(R.id.layout_main);
        fforum = (LinearLayout) findViewById(R.id.layout_forum);
        fweather = (LinearLayout) findViewById(R.id.layout_weather);
        fsetup = (LinearLayout) findViewById(R.id.layout_setup);
        mViewpage = (ViewPager) findViewById(R.id.mViewpage);


        fmain.setOnClickListener((View.OnClickListener) this);
        fforum.setOnClickListener((View.OnClickListener) this);
        fweather.setOnClickListener((View.OnClickListener) this);
        fsetup.setOnClickListener((View.OnClickListener) this);
    }


    class ViewPageOnPagerChangeListener implements ViewPager.OnPageChangeListener {
        @Override//帮助自己检查是否正确的复写了父类中已有的方法，告诉读代码的人，这是一个复写的方法
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            boolean[] state = new boolean[mFragmentList.size()];
            state[position] = true;
            updateBottomLinerLayoutSelect(state[0], state[1], state[2], state[3]);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

    //控制在点击导航栏的图标后，选中对应图标且使得其他图标处于非选中状态
    public void updateBottomLinerLayoutSelect(boolean p, boolean o, boolean s, boolean m) {
        fmain.setSelected(p);
        fweather.setSelected(o);
        fforum.setSelected(s);
        fsetup.setSelected(m);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            goCamera();
        } else if (id == R.id.nav_gallery) {
            goPhotoAlbum();
        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(MainActivity.this,ToolIntroduce.class));
        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(MainActivity.this,ToolLanguage.class));
        } else if (id == R.id.nav_share) {
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
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_main:
                updateBottomLinerLayoutSelect(true, false, false, false);
                mViewpage.setCurrentItem(0);
                break;
            case R.id.layout_weather:
                updateBottomLinerLayoutSelect(false, true, false, false);
                mViewpage.setCurrentItem(1);
                break;
            case R.id.layout_forum:
                updateBottomLinerLayoutSelect(false, false, true, false);
                mViewpage.setCurrentItem(2);
                break;
            case R.id.layout_setup:
                updateBottomLinerLayoutSelect(false, false, false, true);
                mViewpage.setCurrentItem(3);
                break;
        }
    }

    //激活相册操作
    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }

    //激活相机操作
    private void goCamera() {
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        MainActivity.this.startActivityForResult(intent, 1);
    }

    /**
     * 初始化登录信息
     */
    private void adminLogin(){
        SharedPreferences sp = getSharedPreferences("config", this.MODE_PRIVATE);

        String code = sp.getString("user","");

        if(!code.equals("")){
            user = new User(code);
            nickName.setText(user.getNickName());
            headP.setImageBitmap(getRes("head_" + user.getId()));

        }else{
            nickName.setText(R.string.nav_header_title);
            headP.setImageResource(R.mipmap.ic_launcher_round);
        }

    }

    private void adminOut(){
        SharedPreferences sp = getSharedPreferences("config", this.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        editor.remove("user");
        editor.commit();
        onResume();
    }

    //获取Res下的drawable文件夹下图片资源
    private Bitmap getRes(String imageName) {
        ApplicationInfo appInfo = this.getApplicationInfo();
        int resID = getResources().getIdentifier(imageName, "drawable", appInfo.packageName);
        return BitmapFactory.decodeResource(getResources(), resID);
    }
}
