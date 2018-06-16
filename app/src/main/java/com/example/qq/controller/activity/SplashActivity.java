package com.example.qq.controller.activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.qq.R;
import com.example.qq.model.bean.UserInfo;
import com.example.qq.Model;
import com.hyphenate.chat.EMClient;
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //1 判断进入主页面还是登录页面
        toMainLogin();
    }
    //1 判断进入主页面还是登录页面
    private void toMainLogin() {
        //2 判断当前页面是否已经登录
        if(EMClient.getInstance().isLoggedInBefore()){
            //获取用户登录信息
            UserInfo account = Model.getInstance().getUserAccountDao().getAccountByHxId(EMClient.getInstance().getCurrentUser());
            Model.getInstance().loginSuccess(account);
            toMain();
        }else {
            toLogin();
        }
        //存储到数据库中
    }
    //跳转到登录页面
    private void toLogin() {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //跳转到主页面
    private void toMain() {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //        //第二种方式  Handler  发送延迟消息
//        new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
//                startActivity(intent);
//                return false;
//            }
//        }).sendEmptyMessageDelayed(0,2000);
//        //第三种  使用Java计时器
//        Timer timer=new Timer();
//        timer.schedule(new MyTask(),2000);//定时器延时执行任务的方法
//    }
//    //  使用Java计时器
//     class MyTask extends TimerTask{
//
//        @Override
//        public void run() {
//            Intent intent=new Intent(SplashActivity.this,MainActivity.class);
//            startActivity(intent);
//        }
    }
}
