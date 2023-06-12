package com.qingbai.idylls;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener{

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoView = findViewById(R.id.video_view);
        Button play = findViewById(R.id.play);
        Button pause = findViewById(R.id.pause);
        Button replay = findViewById(R.id.replay);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        replay.setOnClickListener(this);
        //判断Manifest是否给了权限
        if(ContextCompat.checkSelfPermission(VideoActivity.this, Manifest.permission.WRITE_APN_SETTINGS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(VideoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
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
        //横屏视频
//        String uri = "https://vdept.bdstatic.com/6e67525159424b386e396e644457426c/39787447474c4177/45465e92eeea86b1040117b2e243eb2fed7379a23487c6b4e58d5ea5c5b553f442808b122900eb4c09a60d7acf473892.mp4?auth_key=1593695031-0-0-529c2afdb77ce1af3d0ebf88837c4b48";
        //竖屏视频
//        String uri = "https://vdept.bdstatic.com/61785078675556313758455937657a46/547062786143355a/a664528ffc7792dcd37be0556a7fa001f8e243899058cda2d879960dc130daedf7767af8345b13eb9dfbf0772c91c54f.mp4?auth_key=1593694909-0-0-5ac46ff8610f6ff1e907ca3da8d40ad8";
//        videoView.setVideoURI(Uri.parse(uri));

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
        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initVideoPath();
                }else{
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
