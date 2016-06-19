package com.vison.devdemo.system;


import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.vison.devdemo.R;
import com.vison.devdemo.utils.ScreenUtils;
import com.vison.devdemo.widget.CustomDialog;

/**
 * Created by vison on 16/5/29.
 */
public class Alert {
    public static void showOKDialog(String title, String message,Context context){
        CustomDialog.Builder builder1 = new CustomDialog.Builder(context);
        CustomDialog dialog = builder1.setTitle(title)
                .setContentView(View.inflate(context, R.layout.dialog_content, null))
                .setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (ScreenUtils.getScreenWidth(context) * 0.83);
        window.setAttributes(attributes);
        dialog.show();
    }
}
