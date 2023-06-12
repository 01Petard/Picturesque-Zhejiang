package com.qingbai.idylls.faxian;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.qingbai.idylls.R;

/***
 * 这个Activity显示水果（景点）的详细信息，上面是一个可折叠式标题栏，下面是视频+图+文字，在下面是留言栏+评论区
 */
public class SpotActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "SpotActivity";
    public static final String SPOT_NAME = "spot_name";
    public static final String SPOT_IMAGE_ID = "friut_image_id";
    private VideoView videoView;

//    private wuliuyanFragment wuliuyanFragment;
//    private liuyanFragment liuyanFragment;
    private FrameLayout frameLayout;
    private EditText ed_liuyankang;
    private TextView tv_user_comment;
    private ImageView im_comment_liuyan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot);
        //Intent获取从spotAdapter传递的水果Name和ImageId
        Intent intent = getIntent();
        String spotName = intent.getStringExtra(SPOT_NAME);
        int spotImageId = intent.getIntExtra(SPOT_IMAGE_ID,0);
//        Log.i(TAG,"------------------------------");
//        Log.i(TAG,"name:"+spotName);
//        Log.i(TAG,"imageId:"+spotImageId);
//        Log.i(TAG,"------------------------------");
        Toolbar toolbar = findViewById(R.id.toolbar);
        //可折叠式标题栏
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(spotName);
        //水果的图片和文字描述
        ImageView spotImageView = findViewById(R.id.spot_image_view);
        TextView spotContentText = findViewById(R.id.spot_content_text);
        videoView = findViewById(R.id.spot_video);
        //用Glide加载水果图片到ImageView上
        Glide.with(this).load(spotImageId).into(spotImageView);
        //给TextView设置文字内容
        String spotContent = generatespotContent(spotName);
        spotContentText.setText(spotContent);
        //播放video
        //判断Manifest是否给了权限
        if(ContextCompat.checkSelfPermission(SpotActivity.this, Manifest.permission.WRITE_APN_SETTINGS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SpotActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else{
            initVideoPath();//初始化VideoView
        }
        videoView.start();

        wuliuyanFragment wuliuyanFragment;
        final liuyanFragment liuyanFragment;
        //评论区+留言栏
//        LinearLayout wuliuyanLayout = findViewById(R.id.user_wuliuyan);
//        LinearLayout liuyanLayout = findViewById(R.id.user_liuyan);
//        wuliuyanLayout.setVisibility(View.VISIBLE);
//        liuyanLayout.setVisibility(View.GONE);
        wuliuyanFragment = new wuliuyanFragment();
        liuyanFragment = new liuyanFragment();
        frameLayout = findViewById(R.id.fl_comment);
        ed_liuyankang = findViewById(R.id.liuyankuang);
        tv_user_comment = findViewById(R.id.user_comment);
        im_comment_liuyan = findViewById(R.id.liuyan);

//        final FragmentManager fm = getSupportFragmentManager();
//        final FragmentTransaction ft = fm.beginTransaction();
//        ft.add(R.id.fl_comment,new wuliuyanFragment(),"wuliuyan").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_comment,wuliuyanFragment).commitAllowingStateLoss();
        im_comment_liuyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                String liuyan = ed_liuyankang.getText().toString();
                bundle.putString("data",liuyan);
                Log.i(TAG,liuyan);
                liuyanFragment.setArguments(bundle);//数据传递到fragment中
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_comment,liuyanFragment).commitAllowingStateLoss();
                ed_liuyankang.setText("");
                ed_liuyankang.clearFocus();
                Toast.makeText(getApplicationContext(),"留言成功",Toast.LENGTH_SHORT).show();

            }
        });
        final ImageView shoucang = findViewById(R.id.shoucang);
        ImageView fenxiang = findViewById(R.id.fenxiang);
        shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoucang.setImageResource(R.drawable.star22);
                Toast.makeText(SpotActivity.this,"收藏成功",Toast.LENGTH_SHORT).show();
            }
        });
        fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里改成那张图片的路径
                Uri uri = Uri.parse("/storage/emulated/0/DCIM/Screenshots/shareSpotImg.png");
                Intent it = new Intent("android.intent.action.SEND");
                it.putExtra("sms_body", "some text");
                it.putExtra("android.intent.extra.STREAM", uri);
                it.setType("image/png/jpg");
                startActivity(it);
                Toast.makeText(SpotActivity.this,"分享成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /***
     * 初始化VideoView
     */
    private void initVideoPath(){
//        File file = new File(Environment.getExternalStorageDirectory(),"movie.mp4");
        //不推荐，指定视频文件的路径
//        videoView.setVideoPath(file.getPath());

        //推荐，播放raw文件里的视频，但是不会有视频播放条
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.ningbo_waitan));

        //较推荐，播放网络视频，但是前提是视频网站没有将视频切片，或者你成功拿到了视频的URI
//        String uri = "https://vdept.bdstatic.com/6e67525159424b386e396e644457426c/39787447474c4177/45465e92eeea86b1040117b2e243eb2fed7379a23487c6b4e58d5ea5c5b553f442808b122900eb4c09a60d7acf473892.mp4?auth_key=1593695031-0-0-529c2afdb77ce1af3d0ebf88837c4b48";
//        spotvideo.setVideoURI(Uri.parse(uri));

        MediaController mediaController=new MediaController(this);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);

    }

    /***
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                if(!videoView.isPlaying()){
                    videoView.start();//开始播放
                }
                break;
            case R.id.pause:
                if(videoView.isPlaying()){
                    videoView.pause();//暂停播放
                }
                break;
            case R.id.replay:
                if(videoView.isPlaying()){
                    videoView.resume();//重新播放
                }
                break;
        }
    }

    /***
     * Activity销毁时，终止视频的播放
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoView != null){
            videoView.suspend();//终止播放
        }
    }

    /***
     * 如果用户拒绝授予权限的话
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initVideoPath();
                } else {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    private String generatespotContent(String spotName) {

        return "\t\t\t\t宁波老外滩，坐落于浙江省宁波市三江口（甬江、奉化江和余姚江的三江汇流之地）北岸江北区，是进入宁波古城的门户。" +
                "这里在唐朝为中国四大港口之一，并成为鉴真东渡的起点；在南宋为中国三大港口之一，并设立市舶司专门负责管理对外贸易；" +
                "当《南京条约》签订后，宁波便成为“五口通商”口岸之一，并于1844年正式开埠。\n" +
                "\t\t\t\t宁波老外滩于1992年后作为商业旅游项目开发，宁波老外滩已经成为宁波的一个景观。";
    }

    /***
     * 设置ToolBar上的home按钮作用，很简单，就是关闭这个Activity
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
