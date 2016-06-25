package com.vison.devdemo.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vison.devdemo.R;

/**
 * Created by vison on 16/6/25.
 */
public class MyToast {

    private static Toast toast;
    private static View mLayout;
    private static TextView mText;
    private static ImageView mImageView;
    /**
     * 显示Toast
     * @param context
     * @param tvString
     */

    public static void logoShow(Context context,String tvString){
        mLayout = LayoutInflater.from(context).inflate(R.layout.layout_toast,null);
        mText = (TextView) mLayout.findViewById(R.id.tv_toast);
        mImageView = (ImageView) mLayout.findViewById(R.id.iv_toast);
        mImageView.setBackgroundResource(R.mipmap.ic_launcher);
        mText.setText(tvString);
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(mLayout);
        toast.show();
    }

    public static void bitmapShow(Context context,String tvString,int bitmapId){
        mLayout = LayoutInflater.from(context).inflate(R.layout.layout_toast,null);
        mText = (TextView) mLayout.findViewById(R.id.tv_toast);
        mImageView = (ImageView) mLayout.findViewById(R.id.iv_toast);
        mImageView.setBackgroundResource(bitmapId);
        mText.setText(tvString);
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(mLayout);
        toast.show();
    }

    public static void show(Context context,String tvString){
        mLayout = LayoutInflater.from(context).inflate(R.layout.layout_toast,null);
        mText = (TextView) mLayout.findViewById(R.id.tv_toast);
        mImageView = (ImageView) mLayout.findViewById(R.id.iv_toast);
        mImageView.setVisibility(View.GONE);
        mText.setText(tvString);
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(mLayout);
        toast.show();
    }
}
