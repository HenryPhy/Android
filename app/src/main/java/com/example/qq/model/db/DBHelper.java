package com.example.qq.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.qq.model.dao.ContactTable;
import com.example.qq.model.dao.InviteTable;

/**
 * Created by 帅比浩宇 on 2018/6/12.
 */

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name) {
        super(context, name, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建联系人表
        db.execSQL(ContactTable.CREARE_TAB);
        //创建群组表
        db.execSQL(InviteTable.CREATE_TAB);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
