package com.qingbai.idylls.shilu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qingbai.idylls.MainActivity;
import com.qingbai.idylls.R;
import com.qingbai.idylls.SelectCity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CityHomeRvAdapter extends RecyclerView.Adapter<CityHomeRvAdapter.ViewHolder> {
    private static final String TAG = "spotAdapter";

    private Context mContext;
    private ViewHolder.OnItemClickListener mListener;
    private List<Part> mCityHomeRvList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView CityHomeRvImage;
        TextView CityHomeRvName;

        ViewHolder(View view){
            super(view);
            CityHomeRvImage = view.findViewById(R.id.city_home_rv_img);
            CityHomeRvName = view.findViewById(R.id.city_home_rv_text);
        }
        //实现短按的接口
        public interface OnItemClickListener{
            void onClick(int pos);
        }

    }

    CityHomeRvAdapter(Context mContext,ViewHolder.OnItemClickListener mListener,List<Part> cityhomervList){
        this.mContext = mContext;
        this.mListener = mListener;
        this.mCityHomeRvList = cityhomervList;
    }

    /**
     * 返回视图，并处理点击事件
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public CityHomeRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_city_home_part,parent,false);

        final CityHomeRvAdapter.ViewHolder holder = new CityHomeRvAdapter.ViewHolder(view);


        holder.CityHomeRvImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                switch (position){
                    case 0:
                        Intent intent2=new Intent(mContext,shiluwenhua.class);
                        mContext.startActivity(intent2);
                        break;
                    case 1:
//                        Intent intent = new Intent("android.intent.action.VIEW");
//                        intent.setData(Uri.parse("https://vr.kan3721.com/tour/a39d1de35142c464"));


//                        Bundle bundle = new Bundle();
//                        bundle.putString("URL","https://vr.kan3721.com/tour/a39d1de35142c464");//西湖
//                        bundle.putString("URL","https://720yun.com/t/3f122xffq1f?scene_id=1130557");//演示资源
//                        bundle.putString("URL","http://www.expoon.com/e/dxd94yu4jto/");//演示资源
//                        Intent intent = new Intent(mContext,WebActivity.class);
//                        intent.putExtras(bundle);

                        Intent intent = new Intent(mContext,CityVrActivity.class);
                        mContext.startActivity(intent);
                        break;
                    case 2:
//                        Intent intent3=new Intent(mContext,quanjingtu.class);
//                        mContext.startActivity(intent3);
                        showProgressBar();
                        break;
                }
//                Spot spot = mSpotList.get(position);
//                Log.e(TAG,"------------------------------");
//                Log.e(TAG,"position:"+position);
//                Log.e(TAG,"name:"+spot.getName());
//                Log.e(TAG,"imageId:"+spot.getImageId());
//                Log.e(TAG,"------------------------------");
//                Intent intent = new Intent(mContext, SpotActivity.class);
//                intent.putExtra(SpotActivity.SPOT_NAME, spot.getName());
//                intent.putExtra(SpotActivity.SPOT_IMAGE_ID, spot.getImageId());
//                mContext.startActivity(intent);
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
    public void onBindViewHolder(@NonNull CityHomeRvAdapter.ViewHolder holder, int position) {
        Part part = mCityHomeRvList.get(position);
        holder.CityHomeRvName.setText(part.getName());
        //加载网络图片
//        Glide.with(mContext).load("http://img4.imgtn.bdimg.com/it/u=2738531068,2088586606&fm=26&gp=0.jpg").into(holder.CityHomeRvImage);
        //加载本地图片
        Glide.with(mContext).load(part.getImageId()).into(holder.CityHomeRvImage);
    }

    @Override
    public int getItemCount() {
        return mCityHomeRvList.size();
    }

    public void showProgressBar(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_progressbar, null);
        ProgressBar pb = view.findViewById(R.id.pb_custome);
        TextView title = view.findViewById(R.id.tv_custome);
        title.setText("正在打开地图……");
        //给ProgressBar添加动画效果
        pb.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.icon_progress));
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
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
                intent.setClass(mContext, quanjingtu.class);
                mContext.startActivity(intent);
//                finish();
                dialog.dismiss();
                t.cancel();
            }
        },800);
    }

}