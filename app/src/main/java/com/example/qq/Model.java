package com.example.qq;
import android.content.Context;

import com.example.qq.model.bean.UserInfo;
import com.example.qq.model.dao.UserAccountDao;
import com.example.qq.model.db.DBManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Created by 帅比浩宇 on 2018/6/8.
 */
//数据模型层的全局类   用单例的模式创建
public class Model {

    //定义一个线程池
    private ExecutorService  executors= Executors.newCachedThreadPool();
    //定义一个上下文
    private Context mContext;
    //创建对象
    private  static Model model=new Model();
//    public DBManager dbManager;
    private UserAccountDao userAccountDao;
    public DBManager dbManager;
    //创建一个私有的构造
    private  Model(){
    }
    //获取单例对象
    public static Model getInstance(){
        return  model;
    }
    //初始化方法
    public void init(Context context) {
        mContext = context;
        //创建用户账号数据库操作类对象
        userAccountDao=new UserAccountDao(mContext);
        //全局的监听事件
//        EventListener eventListener=new EventListener(mContext);
    }
    //获取全局线程池对象
    public  ExecutorService getGlobalThreadPool(){
        return executors;
    }

    //获取用户数据库的对象
    public UserAccountDao getUserAccountDao(){
        return userAccountDao;
    }

    //用户登录成功后的处理方法
    public void loginSuccess(UserInfo account) {
        //校验
        if (account == null) {
            return;
        }
        if (dbManager!=null){
            dbManager.close();
        }
        dbManager=new DBManager(mContext,account.getName());
    }
    //数据库的管理类
    public DBManager getDbManager(){
        return  dbManager;
    }
}