package com.qingbai.idylls.shilu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingbai.idylls.R;

public class CityVrGridAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public CityVrGridAdapter(Context context){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 3;
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
                holder.textView.setText("灵隐寺");
                holder.imageView.setImageResource(R.drawable.lingyinsi);
                break;
            default:
                holder.textView.setText("VR/AR景点或航拍的名字");
                holder.imageView.setImageResource(R.drawable.hua);
                break;
        }
        return convertView;
    }
}
