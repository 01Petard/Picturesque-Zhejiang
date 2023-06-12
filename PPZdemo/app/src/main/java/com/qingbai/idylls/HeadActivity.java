package com.qingbai.idylls;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;



public class HeadActivity extends AppCompatActivity {
    public int[] imageId=new int[]{R.drawable.amiya,
            R.drawable.img02,R.drawable.img03,R.drawable.img04,
            R.drawable.img05,R.drawable.img06,R.drawable.img07,
            R.drawable.img08
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);

        GridView gridView=(GridView)findViewById(R.id.gridView);
        BaseAdapter adapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return imageId.length;
            }
            @Override
            public Object getItem(int position) {
                return position;
            }
            @Override
            public long getItemId(int position) {
                return position;
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView;
                if(convertView==null){
                    imageView=new ImageView(HeadActivity.this);
                    imageView.setAdjustViewBounds(true);
                    imageView.setMaxHeight(250);
                    imageView.setMaxWidth(250);
                    imageView.setPadding(5,5,5,5);
                }else{
                    imageView=(ImageView)convertView;
                }
                imageView.setImageResource(imageId[position]);
                return imageView;
            }
        };
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=getIntent();
                Bundle bundle=new Bundle();
                bundle.putInt("imageId",imageId[position]);
                intent.putExtras(bundle);
                setResult(0x11,intent);
                finish();
            }
        });
    }
}
