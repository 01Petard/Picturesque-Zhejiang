package com.example.dell.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.module.VideoClass;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private Context context;

    private List<VideoClass> mvideos;

    public VideoAdapter(List<VideoClass> videos) {
      mvideos = videos;
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView image;
        TextView type;


        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            image = (ImageView) itemView.findViewById(R.id.video_image);
            type = (TextView) itemView.findViewById(R.id.video_type);
        }


    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {//设置上下文环境
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VideoClass video = mvideos.get(position);
        holder.type.setText(video.getType());
        Glide.with(context).load(video.getImgId()).into(holder.image);
        holder.image.setBackgroundResource(mvideos.get(position).getImgId());
        holder.type.setText(mvideos.get(position).getType());
        final int i = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClick != null){
                    onItemClick.onItemClick(view,i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mvideos.size();
    }
    public interface OnItemClick {
        void onItemClick(View view, int position);
    }
    private OnItemClick onItemClick;
    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

}


