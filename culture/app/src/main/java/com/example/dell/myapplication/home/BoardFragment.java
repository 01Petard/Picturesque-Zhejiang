package com.example.dell.myapplication.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.myapplication.BaseFragment;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.adapter.ChatAdapter;
import com.example.dell.myapplication.dao.MsgDaoUtil;
import com.example.dell.myapplication.listener.OnDbUpdateListener;
import com.example.dell.myapplication.module.Msg;
import com.example.dell.myapplication.module.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.*;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class BoardFragment extends BaseFragment {

    private List<Msg> mMsgs;
    private MsgDaoUtil mMsgDaoUtil;
    private ChatAdapter mAdapter;
    private User user;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @BindView(R.id.rv_chatList)
    RecyclerView mRvChatList;
    @BindView(R.id.et_content)
    EditText mEtContent;


//    Handler handler = new Handler();
//    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            // TODO Auto-generated method stub
//            addMsg(new Msg(null, "来数据了！", Msg.TYPE_BLE, df.format(new Date())));
//            handler.postDelayed(this, 5000);
//        }
//    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_board, container, false);

        ButterKnife.bind(this,view);

        mMsgDaoUtil = new MsgDaoUtil(mContext);

        mMsgs = mMsgDaoUtil.queryAllMsg();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRvChatList.setLayoutManager(linearLayoutManager);
        mAdapter = new ChatAdapter(mContext, mActivity, mMsgs, user);
        mRvChatList.setAdapter(mAdapter);
        mRvChatList.scrollToPosition(mAdapter.getItemCount() - 1);

        mMsgDaoUtil.setUpdateListener(new OnDbUpdateListener() {
            @Override
            public void onUpdate(Msg msg) {
                mAdapter.addItem(msg);
                mRvChatList.scrollToPosition(mAdapter.getItemCount() - 1);
            }
        });

        mRvChatList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < -10) {
                    InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mEtContent.getWindowToken(), 0);
                }
            }
        });

//        handler.postDelayed(runnable, 5000);

        final TextFieldBoxes textFieldBoxes = view.findViewById(R.id.text_field_boxes);
        textFieldBoxes.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = mEtContent.getText().toString();
                addMsg(new Msg(null, content, df.format(new Date()),user.getNickName(),user.getId()));
                mEtContent.setText("");
            }
        });
        
        return view;
    }

    private boolean addMsg(Msg msg) {

        boolean flag = mMsgDaoUtil.insertMsg(msg);
        return flag;
    }

    public void setUser(User user){
        this.user = user;
    }

}
