package com.qingbai.idylls.faxian.Spot;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.qingbai.idylls.R;

/***
 * 这个Activity显示水果（景点）的详细信息，上面是一个可折叠式标题栏，下面是视频+图+文字，后续考虑做留言栏+评论区
 */
public class SpotActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "SpotActivity";
    public static final String SPOT_NAME = "spot_name";
    public static final String SPOT_IMAGE_ID = "friut_image_id";
    private VideoView videoView;

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
    }

    /***
     * 初始化VideoView
     */
    private void initVideoPath(){
//        File file = new File(Environment.getExternalStorageDirectory(),"movie.mp4");
        //不推荐，指定视频文件的路径
//        videoView.setVideoPath(file.getPath());

        //推荐，播放raw文件里的视频，但是不会有视频播放条
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.pian));

        //推荐，播放网络视频，但是前提是视频网站没有将视频切片，或者你成功拿到了视频的URI
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
        StringBuilder spotContent = new StringBuilder();
        for(int i = 0;i < 150;i ++){
            spotContent.append(spotName);
        }
        return spotContent.toString();
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
