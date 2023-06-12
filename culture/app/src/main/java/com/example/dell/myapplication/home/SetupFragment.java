package com.example.dell.myapplication.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.myapplication.BaseFragment;
import com.example.dell.myapplication.MainActivity;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.function.FunctionLink;
import com.example.dell.myapplication.function.FunctionWeb;
import com.example.dell.myapplication.tool.ToolIntroduce;
import com.example.dell.myapplication.tool.ToolLanguage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SetupFragment extends BaseFragment{

    private View view;
    private TextView refresh_data_text,basic_lang_text,nearwifi_text,app_way_text,web_text,permit_text,version_text;
    private RelativeLayout refreshlayout,languagelayout,wifilayout,aplayout,off_weblayout,perlayout,verlayout;

    @BindView(R.id.wifi)
    RelativeLayout wifiL;

    @BindView(R.id.ap)
    RelativeLayout apL;

    @BindView(R.id.off_web)
    RelativeLayout webL;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setup, container, false);

        ButterKnife.bind(this,view);

        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }

    @OnClick({ R.id.wifi, R.id.ap, R.id.off_web })
    public void onViewClicked(RelativeLayout button) {
        switch (button.getId()) {
            case R.id.wifi:
              //  Toast.makeText(mContext, "已经是最新版本!", Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(getActivity())
                        .setTitle("提示")
                        .setMessage("已经是最新版本")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        }).create().show();
                break;
            case R.id.ap:
                startActivity(new Intent(mActivity,ToolIntroduce.class));
                break;
            case R.id.off_web:
                startActivity(new Intent(mActivity, FunctionWeb.class));

                break;
        }
    }

}
