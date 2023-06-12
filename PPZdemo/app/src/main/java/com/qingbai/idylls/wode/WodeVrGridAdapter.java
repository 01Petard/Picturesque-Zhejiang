package com.qingbai.idylls.wode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qingbai.idylls.R;

public class WodeVrGridAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public WodeVrGridAdapter(Context context){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 9;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        public ImageView imageView;
        public TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView =  mLayoutInflater.inflate(R.layout.layout_wode_vr_gridview_item,null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.iv_grid);
            holder.textView = convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //赋值
        switch (position){
            case 0:
                holder.textView.setText("VR/AR景点或航拍的名字00000");
                holder.imageView.setImageResource(R.drawable.hua);
                break;
            case 1:
                holder.textView.setText("VR/AR景点或航拍的名字11111");
                holder.imageView.setImageResource(R.drawable.shan);
                break;
            case 2:
                holder.textView.setText("VR/AR景点或航拍的名字22222");
                holder.imageView.setImageResource(R.drawable.hua);
                break;
            case 3:
                //从网上获取图片
                holder.textView.setText("VR/AR景点或航拍的名字33333");
                Glide.with(mContext).load("http://img4.imgtn.bdimg.com/it/u=2738531068,2088586606&fm=26&gp=0.jpg").into(holder.imageView);
//            case 4:
            default:
                holder.textView.setText("VR/AR景点或航拍的名字");
                holder.imageView.setImageResource(R.drawable.hua);
                break;
        }
        return convertView;
    }
}
