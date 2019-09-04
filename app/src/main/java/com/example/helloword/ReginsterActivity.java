package com.example.helloword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helloword.utils.MD5Utils;

public class ReginsterActivity extends AppCompatActivity {

    private EditText etUsername,etPassword,etPwdAgain;
    private Button btnReginster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //1.获取界面控件
        initView();
        //2.button点击事件的监听
        btnReginster.setOnClickListener(new View.OnClickListener() {
            //3.处理点击事件
            @Override
            public void onClick(View view) {
                //3.1获取控件的值
                String username=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                String pswgagin=etPwdAgain.getText().toString();
                //3.2检查数据的有效性
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(ReginsterActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(password)||TextUtils.isEmpty(pswgagin)){
                    Toast.makeText(ReginsterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }else if(!password.equals(pswgagin)){
                    Toast.makeText(ReginsterActivity.this,"两次密码必须一致",Toast.LENGTH_SHORT).show();
                }else {
                    //3.3将注册信息存储
                    savePref(username, MD5Utils.md5(password));
                    //4.跳转到登录页面
                    Intent intent=new Intent(ReginsterActivity.this,LoginActivity.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                }

            }
        });

    }

    private void savePref(String username,String password) {
        SharedPreferences.Editor editor=getSharedPreferences("userinfo",MODE_PRIVATE).edit();
        editor.putString("username",username);
        editor.putString("password",password);

    }

    private void initView() {
        etUsername =findViewById(R.id.editText5);
        etPassword =findViewById(R.id.editText6);
        etPwdAgain =findViewById(R.id.editText7);
        btnReginster =findViewById(R.id.button3);
    }
}
