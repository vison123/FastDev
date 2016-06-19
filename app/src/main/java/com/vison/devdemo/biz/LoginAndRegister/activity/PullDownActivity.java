package com.vison.devdemo.biz.LoginAndRegister.activity;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.vison.devdemo.R;
import com.vison.devdemo.widget.PullDownView;

import java.util.ArrayList;
import java.util.List;

public class PullDownActivity extends Activity implements PullDownView.OnPullDownListener, AdapterView.OnItemClickListener {

    private static final int WHAT_DID_LOAD_DATA = 0;
    private static final int WHAT_DID_REFRESH = 1;
    private static final int WHAT_DID_MORE = 2;

    private ListView mListView;
    private ArrayAdapter<String> mAdapter;

    private PullDownView mPullDownView;
    private List<String> mStrings = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_down);

        /*
         * 1.使用PullDownView
         * 2.设置OnPullDownListener
         * 3.从mPullDownView里面获取ListView
         */
        mPullDownView = (PullDownView) findViewById(R.id.pull_down_view);
        mPullDownView.setOnPullDownListener(this);
        mListView = mPullDownView.getListView();

        mListView.setOnItemClickListener(this);
        mAdapter = new ArrayAdapter<String>(this, R.layout.pulldown_item, mStrings);
        mListView.setAdapter(mAdapter);

        mPullDownView.enableAutoFetchMore(true, 1);

        loadData();
    }

    private void loadData(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<String> strings = new ArrayList<String>();
                for (String body : mStringArray) {
                    strings.add(body);
                }
                Message msg = mUIHandler.obtainMessage(WHAT_DID_LOAD_DATA);
                msg.obj = strings;
                msg.sendToTarget();
            }
        }).start();
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = mUIHandler.obtainMessage(WHAT_DID_REFRESH);
                msg.obj = "After refresh " + System.currentTimeMillis();
                msg.sendToTarget();
            }
        }).start();
    }

    @Override
    public void onMore() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = mUIHandler.obtainMessage(WHAT_DID_MORE);
                msg.obj = "After more " + System.currentTimeMillis();
                msg.sendToTarget();
            }
        }).start();
    }

    private Handler mUIHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_DID_LOAD_DATA:{
                    if(msg.obj != null){
                        List<String> strings = (List<String>) msg.obj;
                        if(!strings.isEmpty()){
                            mStrings.addAll(strings);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                    // 诉它数据加载完毕;
                    mPullDownView.notifyDidLoad();
                    break;
                }
                case WHAT_DID_REFRESH :{
                    String body = (String) msg.obj;
                    mStrings.add(0, body);
                    mAdapter.notifyDataSetChanged();
                    // 告诉它更新完毕
                    mPullDownView.notifyDidRefresh();
                    break;
                }

                case WHAT_DID_MORE:{
                    String body = (String) msg.obj;
                    mStrings.add(body);
                    mAdapter.notifyDataSetChanged();
                    // 告诉它获取更多完毕
                    mPullDownView.notifyDidMore();
                    break;
                }
            }

        }

    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "啊，你点中我了 " + position, Toast.LENGTH_SHORT).show();
    }

    // 模拟数据
    private String[] mStringArray = {
            "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale",
            "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese"
    };

}