package com.qingbai.idylls.wode.Chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.qingbai.idylls.R;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private List<Msg> mMsgList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout leftlayout;
        RelativeLayout rightlayout;
        ImageView leftIcon;
        ImageView rightIcon;
        TextView leftName;
        TextView rightName;
        TextView leftMsg;
        TextView rightMsg;
        public ViewHolder(View view){
            super(view);
            leftlayout = view.findViewById(R.id.left_layout);
            rightlayout = view.findViewById(R.id.right_layout);
            leftIcon = view.findViewById(R.id.left_icon);
            rightIcon = view.findViewById(R.id.right_icon);
            leftName = view.findViewById(R.id.left_name);
            rightName = view.findViewById(R.id.right_name);
            leftMsg = view.findViewById(R.id.left_msg);
            rightMsg = view.findViewById(R.id.right_msg);
        }
    }
    public MsgAdapter(List<Msg> msgList){
        mMsgList = msgList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_msg_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Msg msg = mMsgList.get(position);
        if(msg.getType() == Msg.TYPE_RECEIVED){
            //如果是收到的消息，则显示左边的消息布局，将右边的消息布局隐藏
            holder.leftlayout.setVisibility(View.VISIBLE);
            holder.rightlayout.setVisibility(View.GONE);
            holder.leftIcon.setImageResource(R.drawable.kefu_wentifankui);
            holder.leftName.setText(msg.getName());
            holder.leftMsg.setText(msg.getContent());
        }else{
            //如果是发出的消息，则显示右边的消息布局，将左边的消息布局隐藏
            holder.leftlayout.setVisibility(View.GONE);
            holder.rightlayout.setVisibility(View.VISIBLE);
            holder.rightIcon.setImageResource(R.drawable.img01);
            holder.rightName.setText(msg.getName());
            holder.rightMsg.setText(msg.getContent());
        }
    }
    @Override
    public int getItemCount() {
        return mMsgList.size();
    }

}
