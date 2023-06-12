package com.qingbai.idylls.shilu;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.qingbai.idylls.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ContentFragment extends Fragment {

    private static final String TAG = "ContentFragment";

    //    private HomeViewModel contentViewModel;
    Context mContext;

    private final String title;
    private final int xml;

    private SliderLayout mDemoSlider;
    private Part[] parts = {
            new Part("诗路文化", R.drawable.shiluwenhua),
            new Part("VR漫步", R.drawable.vrarmanbu),
            new Part("城市全览", R.drawable.chenshiquanlan)
    };
    private List<Part> CityHomeRvList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CityHomeRvAdapter adapter;

    boolean imageMeasured = false;
    TextView tv_right;
    TextView tv_bottom;


    static final String[] text = {"\t\t\t\t杭州，简称“杭”，古称临安、钱塘，是浙江省省会、副省级市、" +
            "杭州都市圈核心城市，国务院批复确定的中国浙江省省会和全省经济、" +
            "文化、科教中心、长江三角洲中心城市之一。截至2019年，全市下辖" +
            "10个区、2个县、代管1个县级市，总面积16853.57平方千米，常住人口1036万人，" +
            "城镇人口813.26万人，城镇化率78.5%，常住外来人口达450.44万人。杭州地处中国华东地区" +
            "、钱塘江下游、东南沿海、浙江北部、京杭大运河南端，是环杭州湾大湾区核心城市、" +
            "沪嘉杭G60科创走廊中心城市、国际重要的电子商务中心。杭州人文古迹众多，西湖及" +
            "其周边有大量的自然及人文景观遗迹，具代表性的有西湖文化、良渚文化、丝绸文化、茶文化，" +
            "以及流传下来的许多故事传说。"
            , "\t\t\t\t宁波，简称“甬”，是浙江省副省级市、计划单列市，国务院批复确定的中国东南沿海重要的港口城" +
            "市、长江三角洲南翼经济中心。截至2019年，全市下辖6个区、2个县、代管2个县级市，总面" +
            "积9816平方千米，常住人口854.2万人。" +
            "宁波地处中国华东地区、东南沿海，大陆海岸线中段，长江三角洲" +
            "南翼，东有舟山群岛为天然屏障，宁波属于典型的江南水乡兼海港城市，是中" +
            "国大运河南端出海口、“海上丝绸之路”东方始发港。宁波舟山港年货物吞吐量位居" +
            "全球第一，集装箱量位居世界前三，是一个集内河港、河口港和海港于一体的多功能、综合性的现" +
            "代化深水大港。"};

    static String cityText = null;
    //主页文字
    // 屏幕的高度
    int screenWidth = 0;
    // 总共可以放多少个字
    int count = 10000;
    // textView全部字符的宽度
    float textTotalWidth = 0.0f;
    // textView一个字的宽度
    float textWidth = 0.0f;
    Paint paint = new Paint();

    private ImageButton t1;
    private ImageButton t2;
    private ImageButton t3;
    private Dialog dia1;
    private Dialog dia2;
    private Dialog dia3;
    private Dialog dia4;
    private int city;


    private ScrollView mScrollView;

    ContentFragment(String title, int xml, int city) {
        super();
        this.title = title;
        this.xml = xml;
        this.city = city;
    }

    String getTitle() {
        return title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        contentViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        cityText = text[city];

        View view = inflater.inflate(xml, container, false);

        //RecyclerView
        switch (xml) {
            case R.layout.viewpage_item1:
                //SliderLayout

                mDemoSlider = view.findViewById(R.id.slider);
                HashMap<String, Integer> urlMaps = new HashMap<>();
                String[][] di = {{"西湖", "天目山", "曲院风荷"}, {"外滩", "三江口", "天一阁"}};
                int[] image = {R.drawable.xihu2, R.drawable.tianyige2};
                ImageView imageViewc = view.findViewById(R.id.test_image);
                imageViewc.setImageResource(image[city]);
                int[][] p = {{R.drawable.xihu, R.drawable.tianmushan, R.drawable.fenghe}, {R.drawable.waitan, R.drawable.sanjiang, R.drawable.tianyige}};
                urlMaps.put(di[city][0], p[city][0]);
                urlMaps.put(di[city][1], p[city][1]);
                urlMaps.put(di[city][2], p[city][2]);
                for (String name : urlMaps.keySet()) {
                    TextSliderView textSliderView = new TextSliderView(getActivity());
                    textSliderView
                            .description(name)//描述
//                    .image(urlMaps.get(name))//image方法可以传入图片url、资源id、File
                            .image(urlMaps.get(name))
                            .setScaleType(BaseSliderView.ScaleType.CenterCrop)//图片缩放类型
                            .setOnSliderClickListener(onSliderClickListener);//图片点击
                    textSliderView.bundle(new Bundle());
                    textSliderView.getBundle().putString("extra", name);//传入参数
                    mDemoSlider.addSlider(textSliderView);//添加一个滑动页面
                }
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);//滑动动画
                mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);//默认指示器样式
                mDemoSlider.setCustomIndicator((PagerIndicator) view.findViewById(R.id.custom_indicator2));//自定义指示器
                mDemoSlider.setCustomAnimation(new DescriptionAnimation());//设置图片描述显示动画
                mDemoSlider.setDuration(3000);//设置滚动时间，也是计时器时间
                mDemoSlider.addOnPageChangeListener(onPageChangeListener);
                initCityHomeRv();
                recyclerView = view.findViewById(R.id.city_home_rv);
                GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(new CityHomeRvAdapter(
                        mContext,
                        new CityHomeRvAdapter.ViewHolder.OnItemClickListener() {
                            @Override
                            public void onClick(int pos) {

                            }
                        },
                        CityHomeRvList));
                //山的图片是  text_image

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
                        if (!imageMeasured) {
                            imageMeasured = true;
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
                int[][] images = {
                        {R.drawable.hangzhou1, R.drawable.hangzhou2, R.drawable.hangzhou3, R.drawable.hangzhou4, R.drawable.hangzhou5, R.drawable.hangzhou6, R.drawable.hangzhou7, R.drawable.hangzhou8, R.drawable.hangzhou9},
                        {R.drawable.ningbo1, R.drawable.ningbo2, R.drawable.ningbo3, R.drawable.ningbo4, R.drawable.ningbo5, R.drawable.ningbo6, R.drawable.ningbo7, R.drawable.ningbo8, R.drawable.ningbo9}
                };
                ImageView imageView3 = view.findViewById(R.id.t3);
                ImageView imageView4 = view.findViewById(R.id.t4);
                ImageView imageView5 = view.findViewById(R.id.t5);
                ImageView imageView6 = view.findViewById(R.id.t6);
                ImageView imageView7 = view.findViewById(R.id.t7);
                ImageView imageView8 = view.findViewById(R.id.t8);
                ImageView imageView9 = view.findViewById(R.id.t9);

                //点击图片显示大小
                t1 = view.findViewById(R.id.t1);
                t1.setImageResource(images[city][0]);
                t2 = view.findViewById(R.id.t2);
                t2.setImageResource(images[city][1]);
//                t3 = view.findViewById(R.id.t3);
//                t3.setImageResource(images[city][2]);

                imageView3.setImageResource(images[city][2]);
                imageView4.setImageResource(images[city][3]);
                imageView5.setImageResource(images[city][4]);
                imageView6.setImageResource(images[city][5]);
                imageView7.setImageResource(images[city][6]);
                imageView8.setImageResource(images[city][7]);
                imageView9.setImageResource(images[city][8]);


                Context context = getContext();
                dia1 = new Dialog(context, R.style.edit_AlertDialog_style);
                dia2 = new Dialog(context, R.style.edit_AlertDialog_style);
                dia3 = new Dialog(context, R.style.edit_AlertDialog_style);
                dia4 = new Dialog(context, R.style.edit_AlertDialog_style);
                dia1.setContentView(R.layout.item_dialog1);
                dia2.setContentView(R.layout.item_dialog2);
                dia3.setContentView(R.layout.item_dialog3);
                dia4.setContentView(R.layout.item_dialog4);
                dia1.setCanceledOnTouchOutside(true);//选择true的话点击其他地方可以使dialog消失，为false的话不会消失
                dia2.setCanceledOnTouchOutside(true);
                dia3.setCanceledOnTouchOutside(true);
                dia4.setCanceledOnTouchOutside(true);
                t1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(city == 0){
                            dia1.show();
                        }else{
                            dia3.show();
                        }
                    }
                });
                t2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(city == 0){
                            dia2.show();
                        }else{
                            dia4.show();
                        }
                    }
                });
                ImageView iv1 = dia1.findViewById(R.id.tt1);
                iv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dia2.dismiss();
                    }
                });
                ImageView iv2 = dia2.findViewById(R.id.tt1);
                iv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dia3.dismiss();
                    }
                });
                break;
            case R.layout.viewpage_item3:
                String[] cityname = {"杭州", "宁波"};
                String[][] fengjingname = {{"西湖", "宋城"}, {"天一阁", "东钱湖"}};
                String[][] jieshao = {{"\t\t\t\t西湖古称“钱塘湖”，又名“西子湖”，古代诗人苏轼就对它评价道：“欲把西湖比西子" +
                        "，淡妆浓抹总相宜。”西湖形态为近于等轴的多边形，湖面被孤山及苏堤、白堤两条人工堤分割为5个" +
                        "子湖区，子湖区间由桥孔连通，各部分的湖水不能充分掺混，造成各湖区水质差异，大部分径流补给" +
                        "先进入西侧3个子湖区，再进入外西湖；湖水总面积5.593平方公里;，总容积1．10亿立方米，平均水" +
                        "深1．97 米；"
                        , "\t\t\t\t西湖底质是一种有机质含量特别高的湖沼相沉积，属于粉砂质粘土或粉砂质亚粘土，最上层皆为藻骸腐" +
                        "泥层（黑色有机质粘土），中层泥炭层或沼泽土，最下层为基底粉砂层；入湖河流部是短小的溪涧，主" +
                        "要补水河流为金沙涧、龙泓涧和长桥溪泄流。西湖，是一首诗，一幅天然图画，一个美丽动人的故事，不" +
                        "论是多年居住在这里的人还是匆匆而过的旅人，无不为这天下无双的美景所倾倒。 阳春三月，莺飞草长。" +
                        "苏白两堤，桃柳夹岸。两边是水波潋滟，游船点点，远处是山色空蒙，青黛含翠。此时走在堤上，你会被眼" +
                        "前的景色所惊叹，甚至心醉神驰，怀疑自己是否进入了世外仙境。 "
                        , "\t\t\t\t宋城以“建筑为形，文化为魂”为经营理念，仿宋代风格建造，主体建筑依据北宋画家张择端的长卷《清明上河" +
                        "图》而建，并按照宋书《营造法式》建造，还原了宋代都市风貌，是杭州第一个反映两宋文化内涵的主题公园，年接" +
                        "待游客超过1000万。大型歌舞《宋城千古情》是宋城的灵魂，与拉斯维加斯的O秀、巴黎红磨坊并称“世界三大名秀” 。"
                        , "\t\t\t\t宋城1997年被浙江省旅游局定为“97中国旅游年欢乐浙江游”的王牌景点，并获得“浙江十佳美景乐园”；1998年成为" +
                        "中国国家旅游局“98华夏乡游”的首选地；1999年4月获得“世界娱乐与主题公园协会会员”；2000年获得" +
                        "国家“AAAA级”旅游景区证书。"},
                        {"\t\t\t\t天一阁，位于浙江省宁波市海曙区，建于明嘉靖四十年至四十五年（1561年—1566年），由当时退隐的明朝兵部右侍郎范" +
                                "钦主持建造，占地面积2.6万平方米  ，已有400多年的历史。 "
                                , "\t\t\t\t天一阁藏书楼坐北朝南，为两层砖木结构的硬山顶重楼式建筑，通高8.5米，斜坡屋顶，青瓦覆上。   一层面阔、" +
                                "进深各六间，二层除楼梯间外为一大通间，以书橱间隔。阁前凿“天一池”通月湖，园林以“福、禄、寿”作总体造" +
                                "型，用山石堆成“九狮一象”等景点。  天一阁及其周围园林具有江南庭院式园林特色。 天一阁" +
                                "的藏书和建筑为研究书法、地方史、石刻、石构建筑和浙东民居建筑提供了实物资料。  "
                                , "\t\t\t\t东钱湖又称钱湖、万金湖，是浙江省著名的风景名胜区，距宁波城东15公里，湖的东南背依青山，湖的西北紧依平原" +
                                "即东经121°34′，北纬28°52′，是闽浙地质的一部分，系远古时期地质运动形成的天然泻湖。被郭沫若先生誉" +
                                "“西湖风光，太湖气魄”。东钱湖由谷子湖、梅湖和外湖三部分组成，南北长8.5公里，东西宽6.5公里，环湖周长45" +
                                "公里，面积22平方公里，是浙江省最大的（天然）淡水湖，面积为杭州西湖的3倍，平均水深2.2米，总蓄水量3390万" +
                                "立方米。"
                                , "\t\t\t\t东钱湖开凿至今已有1200多年历史，经历代开浚更具风采。唐天宝年间（744年）鄮县县令陆南金率众修筑坝堤，这以后王安" +
                                "石、李夷庚、吕献之等历代地方官除葑清界、增筑设施，使之成为综合利用的水域。"}};
                TextView texttitle = view.findViewById(R.id.title);
                texttitle.setText(cityname[city]);
                TextView textViewfengj = view.findViewById(R.id.fengjingname);
                textViewfengj.setText(fengjingname[city][0]);
                TextView textViewfengj2 = view.findViewById(R.id.fengjingname2);
                textViewfengj2.setText(fengjingname[city][1]);
                TextView text = view.findViewById(R.id.text);
                TextView text1 = view.findViewById(R.id.text1);
                TextView text2 = view.findViewById(R.id.t1);
                TextView text3 = view.findViewById(R.id.t2);
                text.setText(jieshao[city][0]);
                text1.setText(jieshao[city][1]);
                text2.setText(jieshao[city][2]);
                text3.setText(jieshao[city][3]);
                int[][] imagess = {{R.drawable.xihu, R.drawable.hangzhou7}, {R.drawable.tianyige, R.drawable.dongqianhu}};
                ImageView imageView1 = view.findViewById(R.id.pic0);
                imageView1.setImageResource(imagess[city][0]);
                ImageView imageViewse = view.findViewById(R.id.pic1);
                imageViewse.setImageResource(imagess[city][1]);
                break;
            case R.layout.viewpage_item4:
                String[] shi = {
                        "忆江南\n" +
                        "唐·白居易\n" +
                        "江南好，风景旧曾谙。日出江花红胜火，春来江水绿如蓝。能不忆江南。\n" +
                        "江南忆，最忆是杭州。山寺月中寻桂子，郡亭枕上看潮头。何日更重游。\n" +
                        "江南忆，其次忆吴宫。吴酒一杯春竹叶，吴娃双舞醉芙蓉。早晚复相逢。\n" +
                        "题临安邸\n" +
                        "[宋] 林升\n" +
                        "山外青山楼外楼，西湖歌舞几时休？\n" +
                        "暖风熏得游人醉，直把杭州作汴州！\n" +
                        "饮湖上初晴后雨\n" +
                        "[宋] 苏轼\n" +
                        "水光潋滟晴方好，山色空蒙雨亦奇。\n" +
                        "欲把西湖比西子，淡妆浓抹总相宜。\n" +
                        "晓出净慈寺送林子方\n" +
                        "[宋] 杨万里\n" +
                        "毕竟西湖六月中，风光不与四时同。\n" +
                        "接天莲叶无穷碧，映日荷花别样红。\n" +
                        "六月二十七日望湖楼醉书\n" +
                        "[宋] 苏轼\n" +
                        "黑云翻墨未遮山，白雨跳珠乱入船。\n" +
                        "卷地风来忽吹散，望湖楼下水如天。\n" +
                        "钱塘湖春行\n" +
                        "[唐] 白居易\n" +
                        "孤山寺北贾亭西，水面初平云脚低。\n" +
                        "几处早莺争暖树，谁家新燕啄春泥？\n" +
                        "乱花渐欲迷人眼，浅草才能没马蹄。\n" +
                        "最爱湖东行不足，绿杨阴里白沙堤。"
                        , "天童育王道中\n" +
                        "[宋] 史浩\n" +
                        "迸云佛塔金千寻，傍耸滴翠玲zhi珑岑。\n" +
                        "春供万象当远目，响答两地纷鸣禽。\n" +
                        "风摇野帻去复去，雨浥乳窦深复深。\n" +
                        "寄声俊逸鲍夫子，莲社不挂渊明心。\n" +
                        "游东钱湖\n " +
                        "[宋] 苏轼\n　　" +
                        "行李萧萧一担秋，浪头始得见渔舟。\n" +
                        "晓烟笼树鸦还集，碧水连天鸥自浮。\n" +
                        "十字港通霞屿寺，二灵山对月波楼。\n" +
                        "于今幸遂归湖愿，长忆当年贺监游。\n" +
                        "天童寺 二首\n" +
                        "[宋] 王安石\n" +
                        "天童溪溪水清涟树老苍，行穿溪树踏春阳。\n" +
                        "溪深树密无人处，惟有幽花度水香。\n" +
                        "虎跑泉供厨煮浴方成沼,转磨鸣春始到田。\n" +
                        "还了山中清净债，却来人间作丰年。\n"};
//                int[][] imagess2 = {{R.drawable.xihu, R.drawable.hangzhou7}, {R.drawable.tianyige, R.drawable.dongqianhu}};
                ImageView imagesw = view.findViewById(R.id.pic);
//                imagesw.setImageResource(imagess2[city][0]);
                if(city == 0){
                    imagesw.setImageResource(R.drawable.hangzhou6);
                }else{
                    imagesw.setImageResource(R.drawable.ningbo5);
                }
                TextView textshi = view.findViewById(R.id.text);
                textshi.setText(shi[city]);
                String[] cityname2 = {"杭州", "宁波"};
                TextView texttitle2 = view.findViewById(R.id.title2);
                texttitle2.setText(cityname2[city]);
                String[][] fengjingname2 = {{"西湖", "宋城"}, {"天一阁", "东钱湖"}};
                TextView textViewfengj3 = view.findViewById(R.id.fengjingname3);
                textViewfengj3.setText(fengjingname2[city][0]);
                break;
            default:
                break;
        }

        return view;
    }


    private BaseSliderView.OnSliderClickListener onSliderClickListener = new BaseSliderView.OnSliderClickListener() {
        @Override
        public void onSliderClick(BaseSliderView slider) {
            Toast.makeText(getActivity(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
        }
    };

    //页面改变监听
    private ViewPagerEx.OnPageChangeListener onPageChangeListener = new ViewPagerEx.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            Log.d("ContentFragment", "页面发生改变: " + position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
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
        tv_right.setText(cityText.substring(0, count));
        // 检查行数是否大于设定的行数，如果大于的话，就每次减少一个字符，重新计算行数与设定的一致
        while (tv_right.getLineCount() > lineCount) {
            count -= 1;
            tv_right.setText(cityText.substring(0, count));
        }
        tv_bottom.setPadding(0, lineCount * lineHeight - height, 0, 0);
        tv_bottom.setText(cityText.substring(count));
    }

    /**
     * 测量已经填充的长度，计算其剩下的长度
     */
    private void measureText() {
        Log.i(TAG, "cityText:" + cityText);
        String string = cityText.substring(0, count);
        float size = paint.measureText(string);
        int remainCount = (int) ((textTotalWidth - size) / textWidth);
        if (remainCount > 0) {
            count += remainCount;
            measureText();
        }
    }


}
