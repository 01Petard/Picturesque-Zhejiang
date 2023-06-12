package com.qingbai.idylls.shilu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.qingbai.idylls.R;

public class quanjingtu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanjingtu);
        LinearLayout lin1=findViewById(R.id.lin1);
        if(ShiluFragment.city!=0)
        {
           lin1.setBackgroundResource(R.drawable.ningboquanlan);
        }
    }
}