package com.qingbai.idylls.wode.Chat;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.qingbai.idylls.R;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends Activity {

    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initMsgs();
        inputText = findViewById(R.id.input_text);
        send = findViewById(R.id.send);
        msgRecyclerView = findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(R.drawable.img01, "小团子", content, Msg.TYPE_SEND);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);//当有新消息时，刷新RecyclerView中的显示
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);//将RecyclerView定位到最后一行
                    inputText.setText("");//清空输入框中的内容
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        noteShow();
                    }
                }, 1000);//3秒后执行Runnable中的run方法
            }
        });
        ImageView iv = findViewById(R.id.iv_back);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initMsgs() {
        msgList.add(new Msg(R.drawable.kefu_wentifankui, "客服", "尊敬的用户，有什么可以帮您的吗？", Msg.TYPE_RECEIVED));
//    msgList.add(new Msg(R.drawable.img01,"小团子","（刚醒来……）",Msg.TYPE_SEND));
//    msgList.add(new Msg(R.drawable.kefu_wentifankui,"客服","您好，感谢反馈，我们会",Msg.TYPE_RECEIVED));

    }

    private void noteShow() {
        msgList.add(new Msg(R.drawable.kefu_wentifankui, "客服", "感谢反馈，我们会继续提高服务质量！", Msg.TYPE_RECEIVED));
        adapter.notifyItemInserted(msgList.size() - 1);//当有新消息时，刷新RecyclerView中的显示
        msgRecyclerView.scrollToPosition(msgList.size() - 1);//将RecyclerView定位到最后一行
    }
}
