package com.qingbai.idylls.wode;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.qingbai.idylls.R;
import com.qingbai.idylls.wode.Gongneng.Gongneng;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private List<Gongneng> mData = new ArrayList<>();
    private LayoutInflater inflater;
    ListViewAdapter(Context context, List<Gongneng> list){
        mData.addAll(list);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if(null != mData){
            return mData.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(null != mData && position < mData.size()){
            return mData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null==convertView){
            convertView = inflater.inflate(R.layout.item_wode_listview,null);
        }
        ImageView img = convertView.findViewById(R.id.listview_img_left);
        TextView name = convertView.findViewById(R.id.listview_text);
        Gongneng gongneng = (Gongneng)getItem(position);
        if(gongneng!=null){
            img.setImageResource(gongneng.getImgId());
            name.setText(gongneng.getName());
        }
        return  convertView;
    }
}
