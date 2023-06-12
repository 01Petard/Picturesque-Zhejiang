package com.qingbai.idylls.faxian;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.qingbai.idylls.R;
import com.qingbai.idylls.faxian.Spot.Spot;
import com.qingbai.idylls.faxian.Spot.SpotAdapter;
import com.qingbai.idylls.shilu.ContentFragment;
import com.qingbai.idylls.shilu.ShiluFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FaxianFragment extends Fragment {

    private FaxianViewModel dashboardViewModel;
    private static final String TAG = "DashboardFragment";

    private Spot[][] spots ={{
            new Spot("西湖",R.drawable.xihu ), new Spot("天目山",R.drawable.tianmushan),
            new Spot("曲院风荷",R.drawable.fenghe),new Spot("灵隐寺",R.drawable.hangzhou1),new Spot("湿地公园",R.drawable.hangzhou2),new Spot("雷锋塔",R.drawable.hangzhou3)
            ,new Spot("宋城",R.drawable.hangzhou4),new Spot("六和塔",R.drawable.hangzhou5),new Spot("大运河",R.drawable.hangzhou6)},{new Spot("外滩",R.drawable.waitan ), new Spot("三江口",R.drawable.sanjiang),
            new Spot("天一阁",R.drawable.tianyige),new Spot("天童寺",R.drawable.ningbo1),new Spot("溪口",R.drawable.ningbo2),new Spot("大觉山",R.drawable.ningbo3),new Spot("凤凰山",R.drawable.ningbo4),new Spot("五龙潭",R.drawable.ningbo5),new Spot("天宫庄园",R.drawable.ningbo6)}};
    private List<Spot> spotList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SpotAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;

    private Context mContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
//        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        View view = inflater.inflate(R.layout.fragment_faxian, container, false);
        initSpot();
        recyclerView = view.findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SpotAdapter(spotList);
        recyclerView.setAdapter(adapter);
        swipeRefresh = view.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshSpots();
            }
        });

//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return view;
    }

    private void refreshSpots() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(300);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initSpot();//重载加载水果图片
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);//表示刷新事件结束，隐藏刷新进度条
                    }
                });
            }
        }).start();

    }

    private void initSpot() {
        spotList.clear();
        for(int i=0;i<9;i++){
            spotList.add(spots[ShiluFragment.city][i]);
        }
    }


}
