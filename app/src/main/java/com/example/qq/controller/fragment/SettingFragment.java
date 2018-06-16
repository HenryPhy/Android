package com.example.qq.controller.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.qq.R;
import com.example.qq.controller.activity.LoginActivity;
import com.example.qq.Model;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {
    private Button bt_setting_out;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View dy= inflater.inflate(R.layout.fragment_setting, container, false);
        initView(dy);
        return dy;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        //在Button上显示当前用户
        bt_setting_out.setText("退出登录("+ EMClient.getInstance().getCurrentUser()+")");
        //退出的逻辑
        bt_setting_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        //退出登录的环信服务器
                        EMClient.getInstance().logout(false, new EMCallBack() {
                            //退出成功
                            @Override
                            public void onSuccess() {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //提示退出成功
                                        Toast.makeText(getActivity(),"退出成功",Toast.LENGTH_SHORT).show();
                                        //回到登录页面
                                        Intent in=new Intent(getActivity(), LoginActivity.class);
                                        startActivity(in);
                                        getActivity().finish();
                                    }
                                });
                            }
                            //退出失败
                            @Override
                            public void onError(int i, String s) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(),"退出失败",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            //退出过程中
                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });
                    }
                });

            }
        });
    }

    private void initView(View dy) {
        bt_setting_out=(Button)dy.findViewById(R.id.bt_setting_out);
    }

}
