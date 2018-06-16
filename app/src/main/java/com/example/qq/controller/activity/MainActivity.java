package com.example.qq.controller.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.qq.R;
import com.example.qq.controller.fragment.ChatFragment;
import com.example.qq.controller.fragment.ContactListFragment;
import com.example.qq.controller.fragment.SettingFragment;
import com.example.qq.utils.SpUtils;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rg_main;
    private ChatFragment chatFragment;
    private ContactListFragment contactListFragment;
    private SettingFragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();
    }


    private void initListener() {
        //RadioGroup的选择事件
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                Fragment fragment=null;
                switch (checkedId){
                    //会话列表页
                    case R.id.rb_main_chat:
                        fragment=chatFragment;
                        break;
                    //联系人页面
                    case R.id.rb_main_contact:
                        fragment=contactListFragment;
                        break;
                    //设置页面
                    case R.id.rb_main_setting:
                        fragment=settingFragment;
                        break;
                }
                //实现切换方式
                switchFragment(fragment);
            }
        });
        //默认选择会话列表
        rg_main.check(R.id.rb_main_chat);
    }
    //实现切换的方法
    private void switchFragment(Fragment fragment) {
        FragmentManager fm=getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fl_main,fragment).commit();
    }
    private void initData() {
        //创建3个Fragment的对象
        chatFragment=new ChatFragment();
        contactListFragment=new ContactListFragment();
        settingFragment=new SettingFragment();
    }

    private void initView() {
        rg_main=(RadioGroup) findViewById(R.id.rg_main);
    }
}
