package com.vison.devdemo.system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.vison.devdemo.R;

import org.acra.prefs.PrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {


    @BindView(R.id.iv_splash)
    ImageView ivSplash;
    AnimationSet set;
    @Override
    public void initParams() {

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        set = new AnimationSet(false);//动画集合--旋转和缩放同时进行
//        //旋转动画
//        RotateAnimation rotate=new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//        rotate.setDuration(1000);//设置动画时间
//        rotate.setFillAfter(true);//保持动画后的状态
//
//        //缩放动画
//        ScaleAnimation scale=new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//        scale.setDuration(1000);//设置动画时间
//        scale.setFillAfter(true);//保持动画状态

        //渐变动画
        AlphaAnimation alpna=new AlphaAnimation(1, 1);
        alpna.setDuration(1000);//设置动画时间
        alpna.setFillAfter(true);//保持动画状态

//        set.addAnimation(rotate);
//        set.addAnimation(scale);
        set.addAnimation(alpna);
        ivSplash.startAnimation(set);
    }

    @Override
    public void initListeners() {
        //设置动画监听
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            //动画执行结束
            @Override
            public void onAnimationEnd(Animation animation) {
                toPage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void setClick(View view) {

    }

    //跳转至下一个页面
    private void toPage(){
//        boolean userGuide= PrefUtils.getBoolean(this, "is_user_guide_showed", false);
//        System.out.println("userGuide" + userGuide);
//        if(!userGuide){
//            startActivity(new Intent(SplashActivity.this,GuideActivity.class));
//        }else{
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
//        }
//        finish();
    }

}
