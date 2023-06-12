package com.example.dell.myapplication.function;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dell.myapplication.BaseFragment;
import com.example.dell.myapplication.FunctionActivity;
import com.example.dell.myapplication.R;



public class FunctionLink extends BaseFragment {

    View view;
    Button s1,s2,s3;
    Dialog dia;
    private ImageButton t1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_function_link, container, false);
        // Inflate the layout for this fragment
        t1 = (ImageButton) view.findViewById(R.id.t1);
        t1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dia.show();
                Toast.makeText(  getActivity(),"鼓楼建筑", Toast.LENGTH_SHORT).show();
            }
        });

        Context context = getContext();
        dia = new Dialog(context, R.style.edit_AlertDialog_style);
        dia.setContentView(R.layout.activity_wangge1);
        ImageView imageView = (ImageView) dia.findViewById(R.id.tt1);
        //选择true的话点击其他地方可以使dialog消失，为false的话不会消失
        dia.setCanceledOnTouchOutside(true); // Sets whether this dialog is


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dia.dismiss();
            }
        });
        return view;
    }

}
