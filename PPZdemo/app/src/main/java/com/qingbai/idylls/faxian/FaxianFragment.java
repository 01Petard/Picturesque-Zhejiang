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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FaxianFragment extends Fragment {

    private FaxianViewModel dashboardViewModel;
    private static final String TAG = "DashboardFragment";

    private Spot[] spots ={
            new Spot("Apple",R.drawable.apple), new Spot("Banana",R.drawable.banana),
            new Spot("Orange",R.drawable.orange),new Spot("Grape",R.drawable.grape),
    };
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
        for(int i=0;i<50;i++){
            Random random = new Random();
            int index = random.nextInt(spots.length);
            spotList.add(spots[index]);
        }
    }


}
