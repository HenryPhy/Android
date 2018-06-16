package com.example.qq.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.qq.model.dao.UserAccountTable;

/**
 * Created by 帅比浩宇 on 2018/6/8.
 */

public class UserAccountDB extends SQLiteOpenHelper {
    //构造
    public UserAccountDB(Context context) {
        super(context, "account.db", null, 1);
    }
    //数据库创建的时候回调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建的数据库语句
        db.execSQL(UserAccountTable.CREATE_TAB);

    }
    //数据库更新的时候回调用
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
