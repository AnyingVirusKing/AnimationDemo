package com.virusking.animationdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv_img;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                startTvImgAnimation();
            }
        };

        // 初始化ui
        init();

        // 初始化Data
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
       new Thread(){
           @Override
           public void run() {
               while (true){
                   System.out.println("动画准备开始执行");
                   handler.sendEmptyMessage(0);
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   System.out.println("动画执行结束");
               }
           }
       }.start();
    }

    private void startTvImgAnimation() {
        /*
                 * android中动画之旋转动画（RotateAnimation）
                 * RotateAnimation(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue)
                 * 第一个参数为 旋转开始的角度
                 * 第二个参数为 旋转结束的角度
                 * 第三个参数为 相对于什么开始旋转 一般用Animation.RELATIVE_TO_SELF 相对于自己开始旋转 X轴
                 * 第四个参数为 从自身x什么地方开始（用的是百分比,0~1）
                 * 第五个参数为 相对于什么开始旋转 一般用Animation.RELATIVE_TO_SELF 相对于自己开始旋转 Y轴
                 * 第六个参数为 从自身y什么地方开始 （用的是百分比,0~1）
                 */
        // 创建一个旋转动画对象
        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360.0f,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        // 设置动画的执行时长
        rotateAnimation.setDuration(1000);
        // 设置动画执行完毕后是否停止,默认false,代表执行完毕后恢复原来的样子,true的话，就停止在动画执行完毕的时候的样子
        rotateAnimation.setFillAfter(true);
        // 让view开始执行动画
        tv_img.startAnimation(rotateAnimation);
    }

    /**
     * 初始化UI
     */
    private void init() {
        tv_img = (TextView) findViewById(R.id.tv_img);
    }


}
