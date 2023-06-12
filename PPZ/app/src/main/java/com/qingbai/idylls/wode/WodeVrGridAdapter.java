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
        return 14;
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
                holder.textView.setText("西湖");
                holder.imageView.setImageResource(R.drawable.xihu);
                break;
            case 1:
                holder.textView.setText("千岛湖");
                holder.imageView.setImageResource(R.drawable.qiandaohu);
                break;
            case 2:
                holder.textView.setText("大佛寺");
                holder.imageView.setImageResource(R.drawable.dafosi);
                break;
            case 3:
                holder.textView.setText("雁荡山");
                holder.imageView.setImageResource(R.drawable.yandangshan);
                //从网上获取图片
//                Glide.with(mContext).load("http://img4.imgtn.bdimg.com/it/u=2738531068,2088586606&fm=26&gp=0.jpg").into(holder.imageView);
                break;
            case 4:
                holder.textView.setText("乌镇");
                holder.imageView.setImageResource(R.drawable.wuzhen);
                break;
            case 5:
                holder.textView.setText("鲁迅故里");
                holder.imageView.setImageResource(R.drawable.luxunguli);
                break;
            case 6:
                holder.textView.setText("莫干山");
                holder.imageView.setImageResource(R.drawable.moganshan);
                break;
            case 7:
                holder.textView.setText("三衢石林");
                holder.imageView.setImageResource(R.drawable.sanqushilin);
                break;
            case 8:
                holder.textView.setText("神仙居");
                holder.imageView.setImageResource(R.drawable.shenxianju);
                break;
            case 9:
                holder.textView.setText("妙果寺");
                holder.imageView.setImageResource(R.drawable.miaoguosi);
                break;
            case 10:
                holder.textView.setText("天台山");
                holder.imageView.setImageResource(R.drawable.tiantaishan);
                break;
            case 11:
                holder.textView.setText("长屿硐天");
                holder.imageView.setImageResource(R.drawable.changyudongtian);
                break;
            case 12:
                holder.textView.setText("桃花岛");
                holder.imageView.setImageResource(R.drawable.taohuadao);
                break;
            case 13:
                holder.textView.setText("朱家尖");
                holder.imageView.setImageResource(R.drawable.zhujiajian);
                break;
            case 14:
                holder.textView.setText("青田石门洞");
                holder.imageView.setImageResource(R.drawable.qingtianshimengdong);
                break;
            default:
                holder.textView.setText("VR/AR景点或航拍的名字");
                holder.imageView.setImageResource(R.drawable.hua);
                break;
        }
        return convertView;
    }
}
