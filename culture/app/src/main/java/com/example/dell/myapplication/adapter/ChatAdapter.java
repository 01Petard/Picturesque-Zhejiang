package com.example.dell.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bottomsheet.BottomSheetBean;
import com.dou361.dialogui.listener.DialogUIItemListener;
import com.dou361.dialogui.listener.DialogUIListener;
import com.example.dell.myapplication.MainActivity;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.home.BoardFragment;
import com.example.dell.myapplication.module.Msg;
import com.example.dell.myapplication.module.User;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr.sorrow on 2017/5/6.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private Activity mActivity;
    private List<Msg> mDatas;
    List<String> strings;
    User user;

    public ChatAdapter(Context context, Activity activity, List<Msg> datas, User user) {
        mContext = context;
        mActivity = activity;
        mLayoutInflater = LayoutInflater.from(mContext);
        mDatas = datas;
        this.user = user;
        strings = new ArrayList<>();
        strings.add("回复");
        strings.add("删除");


//                    mDatas.clear();
    }

    public void addItem(Msg msg) {
        mDatas.add(msg);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = mLayoutInflater.inflate(R.layout.item_chat_left, parent, false);
            return new ChatLeftViewHolder(view);
        } else {
            View view = mLayoutInflater.inflate(R.layout.item_chat_right, parent, false);
            return new ChatRightViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Msg msg = mDatas.get(position);
        int id = msg.getId().intValue();
        String time = msg.getTime();
        String content = msg.getContent();
        if(holder instanceof ChatLeftViewHolder){
            ((ChatLeftViewHolder) holder).mTvLeftTime.setText(time);
            ((ChatLeftViewHolder) holder).mTvMsgLeft.setText(content);
            ((ChatLeftViewHolder) holder).mImMsgLeft.setImageBitmap(getRes("head_" + id));
        }else if(holder instanceof ChatRightViewHolder){
            ((ChatRightViewHolder) holder).mTvRightTime.setText(time);
            ((ChatRightViewHolder) holder).mTvMsgRight.setText(content);
            ((ChatRightViewHolder) holder).mImMsgRight.setImageBitmap(getRes("head_" + id));
        }


        holder.itemView.findViewById(R.id.bubble).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "我被点击了" + position, Toast.LENGTH_SHORT).show();



            }
        });
        holder.itemView.findViewById(R.id.bubble).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //触发自定义监听的长按事件
                DialogUIUtils.showBottomSheetAndCancel(mContext, strings, "取消", new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                    }

                    @Override
                    public void onBottomBtnClick() {
                    }
                }).show();

                return  true;//表示此事件已经消费，不会触发单击事件
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class ChatLeftViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_left_time)
        TextView mTvLeftTime;
        @BindView(R.id.tv_msg_left)
        TextView mTvMsgLeft;
        @BindView(R.id.img_ble)
        ImageView mImMsgLeft;

        ChatLeftViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ChatRightViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_right_time)
        TextView mTvRightTime;
        @BindView(R.id.tv_msg_right)
        TextView mTvMsgRight;
        @BindView(R.id.img_phone)
        ImageView mImMsgRight;

        ChatRightViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    //获取Res下的drawable文件夹下图片资源
    private Bitmap getRes(String imageName) {
        ApplicationInfo appInfo = mContext.getApplicationInfo();
        int resID = mActivity.getResources().getIdentifier(imageName, "drawable", appInfo.packageName);
        return BitmapFactory.decodeResource(mActivity.getResources(), resID);
    }

}
