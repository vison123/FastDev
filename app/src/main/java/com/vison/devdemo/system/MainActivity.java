package com.vison.devdemo.system;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vison.devdemo.R;
import com.vison.devdemo.biz.home.HomeFragment;
import com.vison.devdemo.biz.im.ImFragment;
import com.vison.devdemo.biz.market.MarketFragment;
import com.vison.devdemo.biz.mine.MineFragment;
import com.vison.devdemo.biz.publish.PublishFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_market)
    RadioButton rbMarket;
    @BindView(R.id.rb_publish)
    RadioButton rbPublish;
    @BindView(R.id.rb_im)
    RadioButton rbIm;
    @BindView(R.id.rb_mine)
    RadioButton rbMine;
    @BindView(R.id.rg_tabs)
    RadioGroup rgTabs;
    private List<Fragment> fragmentList;
    public boolean isExit = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initParams() {

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        rbHome.setChecked(true);
        toolbarTitle.setText(getResources().getText(R.string.title_home));
    }

    @Override
    public void initData() {
        fragmentList = new ArrayList();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new MarketFragment());
        fragmentList.add(new PublishFragment());
        fragmentList.add(new ImFragment());
        fragmentList.add(new MineFragment());
        vpMain.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));
    }

    class MainViewPagerAdapter extends FragmentStatePagerAdapter {

        public MainViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    @Override
    public void initListeners() {
        rgTabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        vpMain.setCurrentItem(0);
                        toolbarTitle.setText(getResources().getText(R.string.title_home));
                        break;
                    case R.id.rb_market:
                        vpMain.setCurrentItem(1);
                        toolbarTitle.setText(getResources().getText(R.string.title_market));
                        break;
                    case R.id.rb_publish:
                        vpMain.setCurrentItem(2);
                        toolbarTitle.setText(getResources().getText(R.string.title_publish));
                        break;
                    case R.id.rb_im:
                        vpMain.setCurrentItem(3);
                        toolbarTitle.setText(getResources().getText(R.string.title_im));
                        break;
                    case R.id.rb_mine:
                        vpMain.setCurrentItem(4);
                        toolbarTitle.setText(getResources().getText(R.string.title_mine));
                        break;
                }
            }
        });

        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rbHome.setChecked(true);
                        toolbarTitle.setText(getResources().getText(R.string.title_home));
                        break;
                    case 1:
                        rbMarket.setChecked(true);
                        toolbarTitle.setText(getResources().getText(R.string.title_market));
                        break;
                    case 2:
                        rbPublish.setChecked(true);
                        toolbarTitle.setText(getResources().getText(R.string.title_publish));
                        break;
                    case 3:
                        rbIm.setChecked(true);
                        toolbarTitle.setText(getResources().getText(R.string.title_im));
                        break;
                    case 4:
                        rbMine.setChecked(true);
                        toolbarTitle.setText(getResources().getText(R.string.title_mine));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void setClick(View view) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit) {
                AppManager.getAppManager().finishActivity(MainActivity.this);
            } else {
                Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                isExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 1000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
