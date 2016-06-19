package com.vison.devdemo.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vison.devdemo.R;


/**
 * Created by amarsoft on 2016/1/5.
 */
public class CustomDialog extends Dialog {
    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private String title;
        private String message;
        private View contentView;
        private String positivieButtonText;
        private DialogInterface.OnClickListener positiveButtonOnclickListener;
        private DialogInterface.OnClickListener negativeButtonOnclickListener;
        private String negativieButtonText;

        public Builder(Context context) {
            this.context = context;
        }


        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int msg) {
            this.message = (String) context.getText(msg);
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public Builder setPositiveButton(String positivieButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positivieButtonText = positivieButtonText;
            this.positiveButtonOnclickListener = listener;
            return this;
        }

        public Builder setPositiveButton(int positivieButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positivieButtonText = (String) context.getText(positivieButtonText);
            this.positiveButtonOnclickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativieButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativieButtonText = negativieButtonText;
            this.negativeButtonOnclickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativieButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativieButtonText = (String) context.getText(negativieButtonText);
            this.negativeButtonOnclickListener = listener;
            return this;
        }

        public CustomDialog create() {
            LayoutInflater inflater = LayoutInflater.from(context);
            final CustomDialog dialog = new CustomDialog(context, R.style.Dialog);
            View view = inflater.inflate(R.layout.layout_dialog, null);
            dialog.addContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            //设置标题
            ((TextView) view.findViewById(R.id.tv_title)).setText(title);
            //设置提交按钮
            if (positivieButtonText != null) {
                ((TextView) view.findViewById(R.id.tv_go)).setText(positivieButtonText);
                if (positiveButtonOnclickListener != null) {
                    (view.findViewById(R.id.tv_go)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            positiveButtonOnclickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            } else {
                view.findViewById(R.id.tv_go).setVisibility(View.GONE);
            }

            //设置取消按钮
            if (negativeButtonOnclickListener != null) {
                (view.findViewById(R.id.tv_cancel)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        negativeButtonOnclickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                    }
                });
//                if (negativieButtonText != null) {
//                    ((Button) view.findViewById(R.id.btn_cancel)).setText(negativieButtonText);
//                }
            } else {
                view.findViewById(R.id.tv_cancel).setVisibility(View.GONE);
            }

            //设置中间内容
            if (message != null) {
                ((TextView) view.findViewById(R.id.tv_content)).setText(message);
            } else if (contentView != null) {
                ((LinearLayout) view.findViewById(R.id.content)).removeAllViews();
                ((LinearLayout) view.findViewById(R.id.content)).addView(contentView,
                        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT));

            }

            dialog.setContentView(view);

            return dialog;
        }

    }

}
