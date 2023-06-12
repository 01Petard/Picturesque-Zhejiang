package com.qingbai.idylls.wode;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.qingbai.idylls.MainActivity;
import com.qingbai.idylls.R;
import com.qingbai.idylls.SelectCity;
import com.qingbai.idylls.wode.Chat.ChatActivity;
import com.qingbai.idylls.wode.Gongneng.Gongneng;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WodeFragment extends Fragment implements View.OnClickListener {

    private WodeViewModel notificationsViewModel;
    private List<Gongneng> gongnengList;
    public Context mContext;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        notificationsViewModel = new ViewModelProvider(this).get(WodeViewModel.class);
//        View view = inflater.inflate(R.layout.fragment_wode, container, false);
//        final TextView textView = view.findViewById(R.id.text_notifications);
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        View view = inflater.inflate(R.layout.fragment_wode, container, false);
        initGongneng();
//        ListView listView = view.findViewById(R.id.wode_listview);
//        ListViewAdapter adapter = new ListViewAdapter(getActivity(),gongnengList);
//        listView.setAdapter(adapter);

        LinearLayout wode_shoucang = view.findViewById(R.id.ll_wode_shoucang);
        LinearLayout wode_liuyan = view.findViewById(R.id.ll_wode_liuyan);
        LinearLayout wode_fenxiang = view.findViewById(R.id.ll_wode_fenxiang);
        LinearLayout wode_liulan = view.findViewById(R.id.ll_wode_liulan);
        RelativeLayout wode_vr = view.findViewById(R.id.rl_wode_vr);
        LinearLayout wode_shuoming = view.findViewById(R.id.ll_wode_shuoming);
        LinearLayout wode_lianjie = view.findViewById(R.id.ll_wode_lianjie);
        LinearLayout wode_guanyu = view.findViewById(R.id.ll_wode_guanyu);
        LinearLayout wode_fankui = view.findViewById(R.id.ll_wode_fankui);
        Button to_login = view.findViewById(R.id.login);
        TextView userinfo = view.findViewById(R.id.wode_userpage);


        wode_shoucang.setOnClickListener(this);
        wode_liuyan.setOnClickListener(this);
        wode_fenxiang.setOnClickListener(this);
        wode_liulan.setOnClickListener(this);
        wode_vr.setOnClickListener(this);
        wode_shuoming.setOnClickListener(this);
        wode_lianjie.setOnClickListener(this);
        wode_guanyu.setOnClickListener(this);
        wode_fankui.setOnClickListener(this);
        to_login.setOnClickListener(this);
        userinfo.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.ll_wode_shoucang:
                intent = new Intent(getActivity(),WodeShoucangActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_wode_liuyan:
                intent = new Intent(getActivity(),WodeLiuyanActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_wode_fenxiang:
                intent = new Intent(getActivity(),WodeFenxiangActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_wode_liulan:
                intent = new Intent(getActivity(),WodeLiulanActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_wode_shuoming:
                intent = new Intent(getActivity(),WodeShuomingActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_wode_vr:
                intent = new Intent(getActivity(),WodeVrActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_wode_lianjie:
                intent = new Intent(getActivity(),WodeLianjieActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_wode_guanyu:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("关于应用")
                        .setMessage("已经是最新版本")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .create().show();
                break;
            case R.id.ll_wode_fankui:
                intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.wode_userpage:
                intent = new Intent(getActivity(), UserInfoAcivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(getContext(),"未知Onclick点击事件",Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void initGongneng() {
        gongnengList = new ArrayList<>();
        gongnengList.add(new Gongneng("消息",R.drawable.xiaoxi));
        gongnengList.add(new Gongneng("账号安全",R.drawable.zhanghaoanquan));
        gongnengList.add(new Gongneng("设置",R.drawable.shezhi));
        gongnengList.add(new Gongneng("客服",R.drawable.kefu));
    }



}
