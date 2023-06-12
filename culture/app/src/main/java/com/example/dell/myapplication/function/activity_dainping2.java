package com.example.dell.myapplication.function;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dell.myapplication.FunctionActivity;
import com.example.dell.myapplication.MainActivity;
import com.example.dell.myapplication.R;

public class activity_dainping2 extends AppCompatActivity{

    ImageView star1,star2,star3,star4,star5;
    boolean isChanged = false;
    Button button1;

    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dainping2);

        ImageView mImageView = (ImageView) findViewById(R.id.image2);


        Intent mIntent = getIntent();
        byte buff[]=mIntent.getByteArrayExtra("image");
        bitmap = BitmapFactory.decodeByteArray(buff, 0, buff.length);

        BitmapDrawable mBitmapDrawable = new BitmapDrawable(bitmap);
        mImageView.setBackgroundDrawable(mBitmapDrawable);

        final EditText content1=(EditText) findViewById(R.id.content);

        star1 = (ImageView)findViewById(R.id.iv_comment_star_1);
        star2 = (ImageView)findViewById(R.id.iv_comment_star_2);
        star3 = (ImageView)findViewById(R.id.iv_comment_star_3);
        star4 = (ImageView)findViewById(R.id.iv_comment_star_4);
        star5 = (ImageView)findViewById(R.id.iv_comment_star_5);
        button1=(Button)findViewById(R.id.dpublic);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content=content1.getText().toString();
                SharedPreferences sp=getSharedPreferences("mr",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("content",content);
                editor.commit();
                Intent intent=new Intent(activity_dainping2.this, MainActivity.class);
                startActivity(intent);

            }
        });

        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(v == star1)
                {
                    if(isChanged){
                        star1.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star2.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
                        star3.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
                        star4.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
                        star5.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
                    }else
                    {
                        star1.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
                    }
                    isChanged = !isChanged;

                }
            }
        });
        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(v == star2)
                {
                    if(isChanged){
                        star1.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star2.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star3.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
                        star4.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
                        star5.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
                    }else
                    {
                        star1.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star2.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
                    }
                    isChanged = !isChanged;

                }
            }
        });
        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(v == star3)
                {
                    if(isChanged){
                        star1.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star2.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star3.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star4.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
                        star5.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
                    }else
                    {
                        star1.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star2.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star3.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
                    }
                    isChanged = !isChanged;

                }
            }
        });
        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(v == star4)
                {
                    if(isChanged){
                        star1.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star2.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star3.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star4.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star5.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
                    }else
                    {
                        star1.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star2.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star3.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star4.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
                    }
                    isChanged = !isChanged;

                }
            }
        });
        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(v == star5)
                {
                    if(isChanged){
                        star1.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star2.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star3.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star4.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star5.setImageDrawable(getResources().getDrawable(R.drawable.star));
                    }else
                    {
                        star5.setImageDrawable(getResources().getDrawable(R.drawable.star_empty));
                        star1.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star2.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star3.setImageDrawable(getResources().getDrawable(R.drawable.star));
                        star4.setImageDrawable(getResources().getDrawable(R.drawable.star));
                    }
                    isChanged = !isChanged;

                }
            }
        });
    }

}
