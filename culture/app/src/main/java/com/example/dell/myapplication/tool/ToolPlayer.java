package com.example.dell.myapplication.tool;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.dell.myapplication.R;

public class ToolPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_player);

        //添加返回按钮
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        VideoView videoView = (VideoView)findViewById(R.id.videoView);
        TextView t1 = (TextView) findViewById(R.id.t1) ;
        TextView t = (TextView)findViewById(R.id.t);
        t.setText("天一阁简介");
        t1.setText("      天一阁，位于中国浙江省宁波市月湖西侧的天一街，是中国现存最古老的私家藏书楼。天一阁于明代嘉靖四十年（1561年），由当时的兵部右侍郎范钦主持建造，它不但收藏了大量珍贵的图书典籍，并且对后世其他藏书楼的兴修也产生过重大影响。天一阁曾有藏书7万余卷，但到了近代，由于吏治腐败、盗窃和自然损毁，书籍仅存1.3万余卷。中华人民共和国成立后，经过查访和募捐，书籍达到30万卷。1982年，天一阁被中华人民共和国国务院公布为第二批全国重点文物保护单位之一。" +
                "  天一阁占地面积2.6公顷，是一个以藏书文化为核心，集藏书的研究、保护、管理、陈列、社会教育、旅游观光于一体的专题性博物馆。现藏古籍达30余万卷，其中，珍椠善本8万余卷，除此，还收藏大量的字画、碑帖以及精美的地方工艺品。设有《天一阁发展史陈列》、《中国地方志珍藏馆》、《中国现存藏书楼陈列》、《明清法帖陈列》等陈列厅，书画馆常年开展各种临时展览和文化交流活动。天一阁分藏书文化区、园林休闲区、陈列展览区。以宝书楼为中心的藏书文化区有东明草堂、范氏故居、尊经阁、明州碑林、千晋斋和新建藏书库。以东园为中心的园林休闲区有明池、假山、长廊、碑林、百鹅亭、凝晖堂等景点。以近代民居建筑秦氏支祠为中心的陈列展览区，包括芙蓉洲、闻氏宗祠和新建的书画馆。书画馆在秦祠西侧，粉墙黛瓦、黑柱褐梁，有宅六栋，曰：“云在楼，博雅堂，昼锦堂，画帘堂，状元厅，南轩。”与金碧辉煌的秦祠相映照。");
        //加载指定的视频文件
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.tyg;
        videoView.setVideoURI(Uri.parse(uri));
        //创建MediaController对象
//        MediaController mediaController = new MediaController(this);
//        //VideoView与MediaController建立关联
//        videoView.setMediaController(mediaController);

        //让VideoView获取焦点
        //  videoView.requestFocus();
        videoView.start();
    }
}
