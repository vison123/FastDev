package com.vison.devdemo.system;


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

public class MainActivity extends BaseActivity {
    public boolean isExit = false;
    private RadioGroup rg_tabs;
    private RadioButton rb_home, rb_market, rb_publish, rb_im, rb_mine;
    private ViewPager vp_main;
    private TextView toolbar_title;
    private List<Fragment> fragmentList;

    @Override
    public void initParams() {

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        rg_tabs = (RadioGroup) findViewById(R.id.rg_tabs);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_market = (RadioButton) findViewById(R.id.rb_market);
        rb_publish = (RadioButton) findViewById(R.id.rb_publish);
        rb_im = (RadioButton) findViewById(R.id.rb_im);
        rb_mine = (RadioButton) findViewById(R.id.rb_mine);
        vp_main = (ViewPager) findViewById(R.id.vp_main);
        rb_home.setChecked(true);
    }

    @Override
    public void initToolbar() {
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getText(R.string.title_home));
    }

    @Override
    public void initData() {
        fragmentList = new ArrayList();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new MarketFragment());
        fragmentList.add(new PublishFragment());
        fragmentList.add(new ImFragment());
        fragmentList.add(new MineFragment());
        vp_main.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));
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
        rg_tabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        vp_main.setCurrentItem(0);
                        toolbar_title.setText(getResources().getText(R.string.title_home));
                        break;
                    case R.id.rb_market:
                        vp_main.setCurrentItem(1);
                        toolbar_title.setText(getResources().getText(R.string.title_market));
                        break;
                    case R.id.rb_publish:
                        vp_main.setCurrentItem(2);
                        toolbar_title.setText(getResources().getText(R.string.title_publish));
                        break;
                    case R.id.rb_im:
                        vp_main.setCurrentItem(3);
                        toolbar_title.setText(getResources().getText(R.string.title_im));
                        break;
                    case R.id.rb_mine:
                        vp_main.setCurrentItem(4);
                        toolbar_title.setText(getResources().getText(R.string.title_mine));
                        break;
                }
            }
        });

        vp_main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rb_home.setChecked(true);
                        toolbar_title.setText(getResources().getText(R.string.title_home));
                        break;
                    case 1:
                        rb_market.setChecked(true);
                        toolbar_title.setText(getResources().getText(R.string.title_market));
                        break;
                    case 2:
                        rb_publish.setChecked(true);
                        toolbar_title.setText(getResources().getText(R.string.title_publish));
                        break;
                    case 3:
                        rb_im.setChecked(true);
                        toolbar_title.setText(getResources().getText(R.string.title_im));
                        break;
                    case 4:
                        rb_mine.setChecked(true);
                        toolbar_title.setText(getResources().getText(R.string.title_mine));
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
