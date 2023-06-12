package com.example.dell.myapplication.function;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dell.myapplication.BaseFragment;
import com.example.dell.myapplication.R;

import java.io.ByteArrayOutputStream;

import butterknife.*;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;


public class FuntionIntroduce extends BaseFragment {

    View view;
    private Bitmap bitmap;
    byte buff[] = new byte[125*250];
    @BindView(R.id.jump1)
    Button jump;
    SharedPreferences sp;
    public void onActivityCreate(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button bt1 = (Button)getActivity().findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();
                mIntent.putExtra("image", buff);
                mIntent.setClass(getActivity(), activity_dainping.class);
                startActivity(mIntent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_funtion_introduce, container, false);

        ButterKnife.bind(this,view);
        ImageView mImageView = (ImageView) view.findViewById(R.id.image);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star5);
        buff = Bitmap2Bytes(bitmap);
        BitmapDrawable mBitmapDrawable = new BitmapDrawable(bitmap);
        mImageView.setBackgroundDrawable(mBitmapDrawable);
        Button bt1 = (Button)view.findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();
                mIntent.putExtra("image", buff);
                mIntent.setClass(getActivity(), activity_dainping.class);
                startActivity(mIntent);
            }
        });
        TextView content=(TextView)view.findViewById(R.id.content);
        sp=getActivity().getSharedPreferences("mr",MODE_PRIVATE);
        String content1=sp.getString("content","mr");
        content.setText(content1);
        // Inflate the layout for this fragment
        return view;
    }

    @OnClick(R.id.jump1)
    public void onViewClicked(){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.function_box, new FuntionIntroduce2()).commit();
    }
    private byte[] Bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
