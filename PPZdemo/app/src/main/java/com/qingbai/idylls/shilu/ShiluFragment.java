package com.qingbai.idylls.shilu;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.qingbai.idylls.R;

import java.util.ArrayList;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class ShiluFragment extends Fragment {
    private static final String TAG = "HomeFragment";

//    private HomeViewModel homeViewModel;
    CoordinatorLayout frameLayout;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewGroup mFragmentView;
    ArrayList<ContentFragment> fragments = new ArrayList<>();
    ViewPageAdapter viewPageAdapter;
    ShiluFragment homeFragment;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG,"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
    }

    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        if (null == view) {
            view = inflater.inflate(R.layout.fragment_shilu, container, false);
            frameLayout = view.findViewById(R.id.home_fl);
            tabLayout = view.findViewById(R.id.tabLayout);
            viewPager = view.findViewById(R.id.viewPager);
            init();
        }

        //设置适配器
        viewPageAdapter = new ViewPageAdapter(getParentFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,fragments);
        viewPager.setAdapter(viewPageAdapter);
//        viewPager.setOffscreenPageLimit(2);

        //关联ViewPager
        tabLayout.setupWithViewPager(viewPager);

        //改变tabLayout的滑动方式
        //推荐item较多时使用，宽度固定，自动水平排列
//        tabLayout.setTabMode(TabLayout.MODE_AUTO);
        //推荐item较少时使用，平分宽度，所有item都在屏幕上
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        //tablayout的点击事件
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(getActivity(), "当前板块是："+tab.getText()+"，position是:"+tab.getPosition(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    private void init(){
        //初始化数据
        fragments.clear();
        fragments.add(new ContentFragment("主页",R.layout.viewpage_item1));
        fragments.add(new ContentFragment("风景介绍",R.layout.viewpage_item2));
        fragments.add(new ContentFragment("历史建筑",R.layout.viewpage_item3));
        fragments.add(new ContentFragment("自然山水",R.layout.viewpage_item4));
    }


//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        Log.i(TAG,"onViewCreated");
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        Log.i(TAG,"onActivityCreated");
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        Log.i(TAG,"onStart");
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        Log.i(TAG,"onResume");
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        Log.i(TAG,"onPause1");
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        Log.i(TAG,"onStop1");
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        Log.i(TAG,"onDestroyView1");
////        if (null != homeFragment) {
////            ((ViewGroup) view.getParent()).removeView(view);
////        }
//    }
//
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Log.i(TAG,"onDestroy");
//    }
//
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        init();
//        Log.i(TAG,"onDetach");
//    }
}
