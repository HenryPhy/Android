package com.example.qq;

import android.app.Application;
import android.content.Context;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
/**
 * Created by 帅比浩宇 on 2018/6/7.
 */

public class QQApplication extends Application {
    private static Context mcontext;
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化EaseUI
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(false);// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAutoAcceptGroupInvitation(false);//设置同意后接受群邀请
//初始化
        EMClient.getInstance().init(this, options);
        //初始化数据模型类
        Model.getInstance().init(this);
        //初始化全局上下文
        mcontext=this;
    }
    //获取全局上下文的对象
    public static Context getGlobalApplication(){
        return mcontext;
    }
}
