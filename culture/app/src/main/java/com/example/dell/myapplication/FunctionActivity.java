package com.example.dell.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.dell.myapplication.function.FunctionLink;
import com.example.dell.myapplication.function.FunctionMap;
import com.example.dell.myapplication.function.FunctionRoute;
import com.example.dell.myapplication.function.FunctionVideo;
import com.example.dell.myapplication.function.FuntionIntroduce;
import com.example.dell.myapplication.function.FunctionSummary;
import com.example.dell.myapplication.function.FuntionIntroduce2;

public class FunctionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);

        //添加返回按钮
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        switch (getIntent().getIntExtra("index",0)){
            case 0:  getSupportFragmentManager().beginTransaction()
                    .replace(R.id.function_box, new FuntionIntroduce())
                    .commit();
                break;
            case 1:  getSupportFragmentManager().beginTransaction()
                    .replace(R.id.function_box, new FunctionLink())
                    .commit();
                    break;
            case 2:  getSupportFragmentManager().beginTransaction()
                    .replace(R.id.function_box, new FunctionVideo())
                    .commit();
                break;
            case 3:  getSupportFragmentManager().beginTransaction()
                    .replace(R.id.function_box, new FunctionSummary())
                    .commit();
                break;
            case 4:  getSupportFragmentManager().beginTransaction()
                    .replace(R.id.function_box, new FunctionRoute())
                    .commit();
                break;
            case 5:  getSupportFragmentManager().beginTransaction()
                    .replace(R.id.function_box, new FunctionMap())
                    .commit();
                break;
        }



    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
