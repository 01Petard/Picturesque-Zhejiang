package com.example.dell.myapplication.tool;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.dell.myapplication.MainActivity;
import com.example.dell.myapplication.R;

import java.util.Locale;

public class ToolLanguage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_language);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    Locale locale = Locale.getDefault();
    String language = locale.getLanguage();
}
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chinese:
                Resources resources1 = getResources();
                Configuration config1 = resources1.getConfiguration();
                DisplayMetrics dm = resources1.getDisplayMetrics();
                config1.locale = Locale.SIMPLIFIED_CHINESE;
                resources1.updateConfiguration(config1, dm);
                freshView();
                break;
            case R.id.english:
                Resources resources = getResources();
                Configuration config = resources.getConfiguration();
                DisplayMetrics dm1 = resources.getDisplayMetrics();
                config.locale = Locale.ENGLISH;
                resources.updateConfiguration(config, dm1);
                freshView();
                break;
            case R.id.Japanese:
                Resources resources2 = getResources();
                Configuration config2 = resources2.getConfiguration();
                DisplayMetrics dm2 = resources2.getDisplayMetrics();
                config2.locale = Locale.JAPANESE;
                resources2.updateConfiguration(config2, dm2);
                freshView();
            default:
                break;
        }
    }
    private void freshView(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
