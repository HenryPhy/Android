package com.example.qq.model.db;

import android.content.Context;

import com.example.qq.model.dao.ContactTabDao;
import com.example.qq.model.dao.InviteTableDao;

/**
 * Created by 帅比浩宇 on 2018/6/12.
 */

public class DBManager {
    private DBHelper  dbHelper;
    private ContactTabDao contactTabDao;
    private InviteTableDao inviteTableDao;
    public DBManager(Context context, String name){
        //创建数据库
        dbHelper = new DBHelper(context, name);
        //创建数据库中的表的操作类
        contactTabDao=new ContactTabDao(this.dbHelper);
        inviteTableDao=new InviteTableDao(this.dbHelper);

    }
    //获取联系人操作类对象
    public ContactTabDao getContactTabDao(){
        return contactTabDao;
    }
    //获取邀请操作类对象
    public InviteTableDao getinviteTableDao(){
        return inviteTableDao;
    }
    //关闭数据库
    public void close(){
        dbHelper.close();
    }

}
