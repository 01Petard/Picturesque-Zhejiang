package com.qingbai.idylls;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText rs_username;
    private EditText rs_password;
    private EditText rs_password2;
    private EditText rs_email;
    private EditText rs_address;
    private Button register;
    private Button to_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rs_username =(EditText)findViewById(R.id.reg_username);
        rs_password =(EditText)findViewById(R.id.reg_password);
        rs_password2 =(EditText)findViewById(R.id.reg_password2);
        rs_email = (EditText)findViewById(R.id.reg_email);
        rs_address=(EditText)findViewById(R.id.reg_address);
        register = (Button)findViewById(R.id.reg_btn_sure);
        to_login = (Button)findViewById(R.id.reg_btn_login);

        Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,HeadActivity.class);
                startActivityForResult(intent, 0x234);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = rs_username.getText().toString().trim();
                String password = rs_password.getText().toString().trim();
                String password2 = rs_password2.getText().toString().trim();
                String email = rs_email.getText().toString().trim();
                String address = rs_address.getText().toString().trim();

                Log.i("TAG",username);
                Log.i("TAG",password);
                Log.i("TAG",password2);
                Log.i("TAG",email);
                Log.i("TAG",address);

                UserDate user = new UserDate();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.setAddress(address);

                System.out.println(user.getUsername());
                System.out.println(user.getPassword());
                System.out.println(user.getEmail());
                System.out.println(user.getAddress());

                UserService userService = new UserService(getBaseContext());
                boolean flag = true;
                if (flag){
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent_home_to_shopcart = new Intent(RegisterActivity.this,LoginActivity.class) ;
                    startActivity(intent_home_to_shopcart);
                    finish();
                }else{
                    Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_home_to_shopcart = new Intent(RegisterActivity.this,LoginActivity.class) ;
                startActivity(intent_home_to_shopcart);
                finish();
            }
        });


    }
    @Override //onActivityResult方法：
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==0x234&&resultCode==0x234){
            Bundle bundle=data.getExtras();
            int imageId=bundle.getInt("imageId");
            ImageView imageView=(ImageView)findViewById(R.id.imageView1);
            imageView.setImageResource(imageId);
        }
    }

}
