package com.example.dell.myapplication.home;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.dell.myapplication.BaseFragment;
import com.example.dell.myapplication.FunctionActivity;
import com.example.dell.myapplication.R;

public class HomeFragment extends BaseFragment {

    private View view;
    private GridView gridView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        SliderLayout mDemoSlider=(SliderLayout)view.findViewById(R.id.home_slider_ad);

        gridView = (GridView) view.findViewById(R.id.gridView);

        List<Map<String, Object>> item = getData();
        //SimpleAdapter对象，匹配ArrayList中的元素
        SimpleAdapter simpleAdapter = new SimpleAdapter(mContext, item, R.layout.item_figure,
                new String[] { "itemImage", "itemName" },
                new int[] { R.id.itemImage, R.id.itemName });


        if(gridView != null)
            gridView.setAdapter(simpleAdapter);

        // 添加点击事件
        gridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int index = arg2 + 1;// id是从0开始的，所以需要+1
//                Toast.makeText(mContext, "你按下了选项：" + index,Toast.LENGTH_SHORT).show();

                Intent i = new Intent(mActivity, FunctionActivity.class);
                i.putExtra("index",arg2);

                startActivity(i);
            }
        });

        TextSliderView textSliderView1 = new TextSliderView(this.getActivity());
        textSliderView1
                .description("天一阁")
                .image(R.drawable.background);

        TextSliderView textSliderView2 = new TextSliderView(this.getActivity());
        textSliderView2
                .description("鼓楼")
                .image(R.drawable.tyg3);

        TextSliderView textSliderView3 = new TextSliderView(this.getActivity());
        textSliderView3
                .description("街区")
                .image(R.drawable.tyg6);

        TextSliderView textSliderView4 = new TextSliderView(this.getActivity());
        textSliderView4
                .description("天一阁")
                .image(R.drawable.tyg7);

        mDemoSlider.addSlider(textSliderView1);
        mDemoSlider.addSlider(textSliderView2);
        mDemoSlider.addSlider(textSliderView3);
        mDemoSlider.addSlider(textSliderView4);

        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);//使用默认指示器，在底部显示
        mDemoSlider.setDuration(2000);//停留时间


        // Inflate the layout for this fragment
        return view;
    }

    /**
     * 将图标图片和图标名称存入ArrayList中
     *
     * @return
     */
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

        int[] listImg = new int[] { R.drawable.home_introduce , R.drawable.home_link, R.drawable.home_video, R.drawable.home_summary, R.drawable.home_rote, R.drawable.home_map };
        String[] listName = new String[] { getString(R.string.home_introduce), getString(R.string.home_link), getString(R.string.home_video), getString(R.string.home_summary), getString(R.string.home_rote), getString(R.string.home_map)};
        for (int i = 0; i < listImg.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("itemImage", listImg[i]);
            item.put("itemName", listName[i]);
            items.add(item);
        }
        return items;

    }

}
