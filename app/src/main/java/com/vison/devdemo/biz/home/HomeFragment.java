package com.vison.devdemo.biz.home;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.vison.devdemo.R;
import com.vison.devdemo.system.BaseFragment;


public class HomeFragment extends BaseFragment{

    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_home, null, false);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {

    }
}
