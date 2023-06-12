package com.qingbai.idylls.shilu;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.qingbai.idylls.R;
import com.qingbai.idylls.faxian.Spot.Spot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ContentFragment extends Fragment {

//    private HomeViewModel contentViewModel;
    Context mContext;

    private final String title;
    private final int xml;

    private SliderLayout mDemoSlider;
    private Part[] parts ={
            new Part("诗路文化",R.drawable.gongneng1), new Part("VR/AR漫步",R.drawable.gongneng2),
            new Part("城市全览",R.drawable.gongneng3)
    };
    private List<Part> CityHomeRvList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CityHomeRvAdapter adapter;

    boolean imageMeasured = false;
    TextView tv_right;
    TextView tv_bottom;
    static final String text = "叶凡：小说主角，与众老同学在泰山聚会时一同被九龙拉棺带离地球，" +
            "进入北斗星域，得知自己是荒古圣叶凡 叶凡体。历险禁地，习得源术，斗圣地世家，战太古生物，"+
            "重组天庭，叶凡辗转四方得到许多际遇和挑战，功力激增，眼界也渐渐开阔。一个浩大的仙侠世界，"+
            "就以他的视角在读者面前展开。姬紫月：姬家小姐，出场年龄十七岁。被叶凡劫持一同经历青铜古殿历险，"+
            "依靠碎裂的神光遁符解除禁制，反过来挟持叶凡一同进入太玄派寻找秘术。"+
            "在叶凡逃离太玄后姬紫月在孔雀王之乱中被华云飞追杀，又与叶凡[2]相遇，被叶凡护送回姬家"+
            "，渐渐对叶凡产生微妙感情。后成为叶凡的妻子，千载后于飞仙星成仙，在叶凡也进入仙路后再见庞博："+
            "叶凡大学时最好的朋友，壮硕魁伟，直率义气。到达北斗星域后因服用了圣果被灵墟洞天作为仙苗，"+
            "在青帝坟墓处为青帝十九代孙附体离去，肉身被锤炼至四极境界。后叶凡与黑皇镇压老妖神识，"+
            "庞博重新掌控自己身躯，取得妖帝古经和老妖本体祭炼成的青莲法宝，习得妖帝九斩和天妖八式，"+
            "但仍伪装成老妖留在妖族。出关后找上叶凡，多次与他共进退。星空古路开启后由此离开北斗，"+
            "被叶凡从妖皇墓中救出，得叶凡授予者字秘、一气化三清，与叶凡同闯试炼古路，一起建设天庭";
    // 屏幕的高度
    int screenWidth = 0;
    // 总共可以放多少个字
    int count = 0;
    // textView全部字符的宽度
    float textTotalWidth = 0.0f;
    // textView一个字的宽度
    float textWidth = 0.0f;
    Paint paint =new Paint();

    private ImageButton t1;
    private Dialog dia;

    private ScrollView mScrollView;

    ContentFragment(String title, int xml){
        super();
        this.title = title;
        this.xml = xml;
    }
    String getTitle(){
        return title;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        contentViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        View view = inflater.inflate(xml,container,false);

        //RecyclerView
        switch (xml){
            case R.layout.viewpage_item1:
                //SliderLayout
                mDemoSlider = view.findViewById(R.id.slider);
                HashMap<String,Integer> urlMaps = new HashMap<>();
                urlMaps.put("山", R.drawable.shan);
                urlMaps.put("湖", R.drawable.hu);
                urlMaps.put("亭子", R.drawable.tingzi);
                for(String name : urlMaps.keySet()){
                    TextSliderView textSliderView = new TextSliderView(getActivity());
                    textSliderView
                            .description(name)//描述
//                    .image(urlMaps.get(name))//image方法可以传入图片url、资源id、File
                            .image(urlMaps.get(name))
                            .setScaleType(BaseSliderView.ScaleType.CenterCrop)//图片缩放类型
                            .setOnSliderClickListener(onSliderClickListener);//图片点击
                    textSliderView.bundle(new Bundle());
                    textSliderView.getBundle().putString("extra",name);//传入参数
                    mDemoSlider.addSlider(textSliderView);//添加一个滑动页面
                }
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);//滑动动画
                mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);//默认指示器样式
                mDemoSlider.setCustomIndicator((PagerIndicator)view.findViewById(R.id.custom_indicator2));//自定义指示器
                mDemoSlider.setCustomAnimation(new DescriptionAnimation());//设置图片描述显示动画
                mDemoSlider.setDuration(3000);//设置滚动时间，也是计时器时间
                mDemoSlider.addOnPageChangeListener(onPageChangeListener);
                initCityHomeRv();
                recyclerView = view.findViewById(R.id.city_home_rv);
                GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(new CityHomeRvAdapter(
                        mContext,
                        new CityHomeRvAdapter.ViewHolder.OnItemClickListener(){
                            @Override
                            public void onClick(int pos) {

                            }
                        },
                        CityHomeRvList));

                //图文环绕的样式
                tv_right = view.findViewById(R.id.test_tv_right);
                tv_bottom = view.findViewById(R.id.test_tv_bottom);
                final ImageView imageView = view.findViewById(R.id.test_image);
//                imageView.setImageResource(R.drawable.ee);
                screenWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();
                /**
                 * 获取一个字的宽度
                 */
                textWidth = tv_right.getTextSize();
                paint.setTextSize(textWidth);
                /**
                 * 因为图片一开始的时候，高度是测量不出来的，通过增加一个监听器，即可获取其图片的高度和长度
                 */
                ViewTreeObserver vto = imageView.getViewTreeObserver();
                vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    if(!imageMeasured) {
                        imageMeasured =true;
                        int height = imageView.getMeasuredHeight();
                        int width = imageView.getMeasuredWidth();
                        drawImageViewDone(width, height);
                    }
                    return imageMeasured;
                }
//                contentViewModel.getText_right().observe(getViewLifecycleOwner(), new Observer<String>() {
//                        @Override
//                        public void onChanged(@Nullable String s) {
//                            tv_right.setText(s);
//                            tv_bottom.setText(s);
//                        }
//                    });
            });
                break;
            case R.layout.viewpage_item2:
                t1 = view.findViewById(R.id.t1);
                t1.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        dia.show();
                        Toast.makeText(  getActivity(),"鼓楼建筑", Toast.LENGTH_SHORT).show();
                    }
                });

                Context context = getContext();
                dia = new Dialog(context, R.style.edit_AlertDialog_style);
                dia.setContentView(R.layout.item_dialog);
                ImageView iv = dia.findViewById(R.id.tt1);
                //选择true的话点击其他地方可以使dialog消失，为false的话不会消失
                dia.setCanceledOnTouchOutside(true); // Sets whether this dialog is

                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dia.dismiss();
                    }
                });
                break;
            case R.layout.viewpage_item3:

                break;
            default:
                break;
        }



        return view;
    }

    private BaseSliderView.OnSliderClickListener onSliderClickListener=new BaseSliderView.OnSliderClickListener() {
        @Override
        public void onSliderClick(BaseSliderView slider) {
            Toast.makeText(getActivity(),slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
        }
    };

    //页面改变监听
    private ViewPagerEx.OnPageChangeListener onPageChangeListener=new ViewPagerEx.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {
            Log.d("ContentFragment", "页面发生改变: " + position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {}
    };

    private void initCityHomeRv() {
        CityHomeRvList.clear();
        CityHomeRvList.addAll(Arrays.asList(parts));
    }

    private void drawImageViewDone(int width, int height) {
        // 一行字体的高度
        int lineHeight = tv_right.getLineHeight();
        // 可以放多少行
        int lineCount = (int) Math.ceil((double) height / (double) lineHeight);
        // 一行的宽度
        float rowWidth = screenWidth - width - tv_right.getPaddingLeft() - tv_right.getPaddingRight();
        // 一行可以放多少个字
        int columnCount = (int) (rowWidth / textWidth);
        // 总共字体数等于 行数*每行个数
        count = lineCount * columnCount;
        // 一个TextView中所有字符串的宽度和（字体数*每个字的宽度）
        textTotalWidth = ((float) count * textWidth);
        measureText();
        tv_right.setText(text.substring(0, count));
        // 检查行数是否大于设定的行数，如果大于的话，就每次减少一个字符，重新计算行数与设定的一致
        while(tv_right.getLineCount() > lineCount) {
            count -=1;
            tv_right.setText(text.substring(0, count));
        }
        tv_bottom.setPadding(0, lineCount * lineHeight - height,0, 0);
        tv_bottom.setText(text.substring(count));
    }

    /**
     * 测量已经填充的长度，计算其剩下的长度
     */
    private void measureText() {
        String string = text.substring(0, count);
        float size = paint.measureText(string);
        int remainCount = (int) ((textTotalWidth - size) / textWidth);
        if(remainCount > 0) {
            count += remainCount;
            measureText();
        }
    }


}
