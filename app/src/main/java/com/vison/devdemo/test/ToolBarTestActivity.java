package com.vison.devdemo.test;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.vison.devdemo.R;
import com.vison.devdemo.system.ToolBarBaseActivity;

public class ToolBarTestActivity extends ToolBarBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar_test);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initToolbar(Toolbar toolbar, TextView toolBarTitle) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void setClick(View view) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
     // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.menu_home, menu);
      return true;
    }

}