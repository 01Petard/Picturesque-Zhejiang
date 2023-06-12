package com.example.dell.myapplication.function;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dell.myapplication.BaseFragment;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.adapter.VideoAdapter;
import com.example.dell.myapplication.module.VideoClass;
import com.example.dell.myapplication.tool.ToolPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FunctionVideo extends BaseFragment {

    View view;
    private DrawerLayout drawerLayout;

    private VideoClass[] videos = {new VideoClass("天一阁景点介绍", R.drawable.tyg1),
            new VideoClass("鼓楼景点介绍", R.drawable.tyg3),
            new VideoClass("天一阁历史", R.drawable.tyg2),
            new VideoClass("天一阁藏书楼", R.drawable.tyg4),
            new VideoClass("天一阁博物馆", R.drawable.tyg5),
            new VideoClass("鼓楼步行街", R.drawable.tyg6),
            new VideoClass("天一阁美景", R.drawable.tyg7),
            new VideoClass("天一阁壁画", R.drawable.tyg8),
            new VideoClass("天一阁宝书楼", R.drawable.tyg9),
            new VideoClass("鼓楼建筑", R.drawable.tyg0)};
    private List<VideoClass> videolist = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_function_video, container, false);

        initCats();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        int spanCount = 2;
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, spanCount);
        recyclerView.setLayoutManager(layoutManager);
        VideoAdapter adapter = new VideoAdapter(videolist);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClick(new VideoAdapter.OnItemClick(){
            @Override
            public void onItemClick(View view, int position) {
                if(position==0)
                {
                    //Toast.makeText(mContext, "你按下了选项：" + getActivity(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(),ToolPlayer.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }
    private void initCats() {
        videolist.clear();
        for (int i = 0; i < 10; i++) {//随机添加 100 只猫的信息
            videolist.add(videos[i]);
        }
    }

}
