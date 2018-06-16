package com.example.qq.controller.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qq.R;
import com.example.qq.model.bean.UserInfo;
import com.example.qq.Model;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class LoginActivity extends AppCompatActivity {
    //定义用户名，密码
    private EditText ed_login_name,ed_login_pwd;
    //定义注册，登录按钮
    private Button bt_login_regist,bt_login_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化控件
        initView();
        //初始化监听事件
        initListener();
    }

    private void initListener() {
        //注册的监听事件
        bt_login_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regist();
            }
        });
        //登录的监听事件
        bt_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });
    }
    //登录の业务逻辑处理
    private void login() {
        //1 获取输入的用户名和密码
        final String loginName=ed_login_name.getText().toString();
        final String loginPwd=ed_login_pwd.getText().toString();
        //2 校验输入的用户名和密码
        if (TextUtils.isEmpty(loginName)||TextUtils.isEmpty(loginPwd)) {
            Toast.makeText(LoginActivity.this, "抱歉，您登录の用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        //3 登录逻辑の处理
        Model.getInstance().getGlobalThreadPool().execute( new Runnable() {
            @Override
            public void run() {
                //去环信服务器登录
                EMClient.getInstance().login(loginName, loginPwd, new EMCallBack() {
                    //登陆成功
                    @Override
                    public void onSuccess() {
                        //对模型层的数据处理
                        Model.getInstance().loginSuccess(new UserInfo(loginName));
                        //保存用户信息到本地数据库
                        Model.getInstance().getUserAccountDao().addAccount(new UserInfo(loginName));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //提示登录页面
                                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                //提示跳转页面
                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                    //登录失败
                    @Override
                    public void onError(int i, String s) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //提示登录页面
                                Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    //登陆过程处理
                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });

    }

    //注册の业务逻辑处理
    private void regist() {
        //1 获取输入的用户名和密码
        final String regastName=ed_login_name.getText().toString();//从输入框中拿取用户名
        final String regastPwd=ed_login_pwd.getText().toString();//从输入框中拿取密码
        //2 校验输入的用户名和密码
        if (TextUtils.isEmpty(regastName)||TextUtils.isEmpty(regastPwd)){
            Toast.makeText(LoginActivity.this, "抱歉，您注册の用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        //3 去服务器注册账号(服务器是环信的服务器)
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //子线程中去环信服务器注册账号
                    EMClient.getInstance().createAccount(regastName,regastPwd);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (final HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "注册失败"+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    //初始化控件
    private void initView() {
        ed_login_name = (EditText) findViewById(R.id.ed_login_name);
        ed_login_pwd = (EditText) findViewById(R.id.ed_login_pwd);
        bt_login_regist = (Button) findViewById(R.id.bt_login_regist);
        bt_login_login = (Button) findViewById(R.id.bt_login_login);
    }
}
