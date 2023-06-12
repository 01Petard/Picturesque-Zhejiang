package com.qingbai.idylls;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.qingbai.idylls.shilu.ShiluFragment;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class SelectCity extends AppCompatActivity {
    int city=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        Button button=findViewById(R.id.button6);
        Button button2=findViewById(R.id.button7);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShiluFragment.city=1;
                showProgressBar("切换城市：宁波");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShiluFragment.city=0;
                showProgressBar("切换城市：杭州");
            }
        });
    }

    public void showProgressBar(String text){
        View view = LayoutInflater.from(SelectCity.this).inflate(R.layout.layout_progressbar, null);
        ProgressBar pb = view.findViewById(R.id.pb_custome);
        TextView title = view.findViewById(R.id.tv_custome);
        title.setText("正在加载城市……");
        //给ProgressBar添加动画效果
        pb.setAnimation(AnimationUtils.loadAnimation(SelectCity.this, R.anim.icon_progress));
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view)
//                .setTitle("正在加载城市……")
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
                intent.setClass(SelectCity.this,MainActivity.class);
                startActivity(intent);
                finish();
                dialog.dismiss();
                t.cancel();
            }
        },500);
        Toast.makeText(SelectCity.this, text, Toast.LENGTH_SHORT).show();
    }

}