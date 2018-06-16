package com.example.qq.controller.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.qq.R;
import com.example.qq.controller.activity.AddContactActivity;
import com.hyphenate.easeui.ui.EaseContactListFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends EaseContactListFragment {
    private ImageView iv_contact_red;
    private LinearLayout ll_contact_invite;
    private LocalBroadcastManager mLBM;
    private  String mHxid;
    @Override
    protected void initView() {
        super.initView();
        //布局添加加号+
        titleBar.setRightImageResource(R.mipmap.add_friend_normal);
        //添加头布局
        View headerView=View.inflate(getActivity(),R.layout.header_add_contact,null);
        listView.addHeaderView(headerView);
        //获取红点对象
        iv_contact_red=(ImageView) headerView.findViewById(R.id.iv_contact_red);
    }
    @Override
    protected void setUpView() {
        super.setUpView();
        //添加联系人按钮的点击事件处理
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),AddContactActivity.class);
                startActivity(intent);
            }
        });
    }
}