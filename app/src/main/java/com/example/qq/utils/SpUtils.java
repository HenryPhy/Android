package com.example.qq.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.qq.QQApplication;

/**
 * Created by 帅比浩宇 on 2018/6/15.
 */
//工具类
//保存和获取数据
public class SpUtils {
    public static final String IS_NEW_INVITE = "is_new_invite";//新的邀请标记
    private static SpUtils spUtils = new SpUtils();//定义一个自己的对象

    private static SharedPreferences mSp;//定义一个SharedPreferences

    private SpUtils() {
    }//定义一个私有的构造方法

    //获取一个自己的实例
    public static SpUtils getInstance() {
        if (mSp == null) {
            mSp = QQApplication.getGlobalApplication().getSharedPreferences("im", Context.MODE_PRIVATE);
        }
        return spUtils;
    }
    //保存

    public void save(String key, Object value) {
        if (value instanceof String) {
            mSp.edit().putString(key, (String) value).commit();//获取数据
        } else if (value instanceof Boolean) {
            mSp.edit().putBoolean(key, (Boolean) value).commit();//获取boolean
        } else if (value instanceof Integer) {
            mSp.edit().putInt(key, (int) value).commit();//获取int数据
        }
    }
}

