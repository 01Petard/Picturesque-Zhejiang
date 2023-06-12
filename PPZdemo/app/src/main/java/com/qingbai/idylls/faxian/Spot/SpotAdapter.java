package com.qingbai.idylls.faxian.Spot;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qingbai.idylls.R;
import com.qingbai.idylls.faxian.SpotActivity;

import java.util.List;

public class SpotAdapter extends RecyclerView.Adapter<SpotAdapter.ViewHolder> {
    private static final String TAG = "spotAdapter";

    private Context mContext;
    private List<Spot> mSpotList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView spotImage;
        TextView spotName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            spotImage = view.findViewById(R.id.spot_image);
            spotName = view.findViewById(R.id.spot_name);
        }
    }

    public SpotAdapter(List<Spot> spotList){
        mSpotList = spotList;
    }

    /**
     * 返回视图，并处理点击事件
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public SpotAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_spot,parent,false);

        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Spot spot = mSpotList.get(position);
//                Log.e(TAG,"------------------------------");
//                Log.e(TAG,"position:"+position);
//                Log.e(TAG,"name:"+spot.getName());
//                Log.e(TAG,"imageId:"+spot.getImageId());
//                Log.e(TAG,"------------------------------");
                Intent intent = new Intent(mContext, SpotActivity.class);
                intent.putExtra(SpotActivity.SPOT_NAME, spot.getName());
                intent.putExtra(SpotActivity.SPOT_IMAGE_ID, spot.getImageId());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    /**
     * 给水果赋值
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull SpotAdapter.ViewHolder holder, int position) {
        Spot spot = mSpotList.get(position);
        holder.spotName.setText(spot.getName());
//        Glide.with(mContext).load("http://img4.imgtn.bdimg.com/it/u=2738531068,2088586606&fm=26&gp=0.jpg").into(holder.imageView);
//        Glide.with(mContext).load("http://img4.imgtn.bdimg.com/it/u=2738531068,2088586606&fm=26&gp=0.jpg").into(holder.spotImage);
        Glide.with(mContext).load(spot.getImageId()).into(holder.spotImage);
    }

    @Override
    public int getItemCount() {
        return mSpotList.size();
    }

}
