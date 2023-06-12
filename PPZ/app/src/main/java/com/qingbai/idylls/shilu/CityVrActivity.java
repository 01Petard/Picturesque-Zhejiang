package com.qingbai.idylls.shilu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qingbai.idylls.R;
import com.qingbai.idylls.wode.WodeVrActivity;
import com.qingbai.idylls.wode.WodeVrGridAdapter;
import com.qingbai.idylls.wode.WodeVrMoreActivity;

public class CityVrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_vr);

        GridView mGv = findViewById(R.id.wode_vr_gridview);
        mGv.setAdapter(new CityVrGridAdapter(getApplicationContext()));
        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(CityVrActivity.this, "你按的是："+position, Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                switch (position){
                    case 0:
                        bundle.putString("URL","https://vr.kan3721.com/tour/a39d1de35142c464");//西湖
                        break;
                    case 1:
                        bundle.putString("URL","http://www.expoon.com/e/dxd94yu4jto/");//千岛湖
                        break;
                    case 2:
                        bundle.putString("URL","https://720yun.com/t/de328ur6cts?scene_id=1773977");//灵隐寺
                        break;
                    default:
                        break;
                }
                Intent intent = new Intent(CityVrActivity.this, WebActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        TextView tv = findViewById(R.id.wode_vr_more);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CityVrActivity.this, WodeVrMoreActivity.class);
                startActivity(intent);

//                Intent intent = new Intent("android.intent.action.VIEW");
////                intent.setData(Uri.parse("http://www.expoon.com/"));
//                intent.setData(Uri.parse("https://720yun.com/search/0/%E6%B5%99%E6%B1%9F/0/1"));//浙江省相关VR地图网站
//                startActivity(intent);
            }
        });
        ImageView iv = findViewById(R.id.iv_back);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return true;
    }
}