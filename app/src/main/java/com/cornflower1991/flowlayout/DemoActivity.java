package com.cornflower1991.flowlayout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.cornflower1991.flowlayout.widget.FlowTagLayout;
import com.cornflower1991.flowlayout.widget.OnTagClickListener;
import com.cornflower1991.flowlayout.widget.OnTagSelectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yexiuliang on 2016/7/11.
 */

public class DemoActivity extends AppCompatActivity {

    private FlowTagLayout mColorFlowTagLayout;
    private FlowTagLayout mSizeFlowTagLayout;
    private FlowTagLayout mMobileFlowTagLayout;
    private TagAdapter<String> mSizeTagAdapter;
    private TagAdapter<String> mColorTagAdapter;
    private TagAdapter<String> mMobileTagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //        toolbar.setTitle("");
        TextView textView = (TextView) findViewById(R.id.toolbar_title);
        textView.setText("流式标签可单选多选");

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //        点击
        mColorFlowTagLayout = (FlowTagLayout) findViewById(R.id.single_click_flow_layout);
        //        单选
        mSizeFlowTagLayout = (FlowTagLayout) findViewById(R.id.single_choose_flow_layout);
        //        多选
        mMobileFlowTagLayout = (FlowTagLayout) findViewById(R.id.multi_select_flow_layout);

        //点击
        mColorTagAdapter = new TagAdapter<>(this);
        mColorFlowTagLayout.setAdapter(mColorTagAdapter);
        mColorFlowTagLayout.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                Snackbar.make(view, "点击了标签"+position, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //单选
        mSizeTagAdapter = new TagAdapter<>(this);
        mSizeTagAdapter.setSelected(4);//默认选择第四个
        //这里FLOW_TAG_CHECKED_SINGLE是单选，FLOW_TAG_CHECKED_MULTI多选
        mSizeFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        mSizeFlowTagLayout.setAdapter(mSizeTagAdapter);
        mSizeFlowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int position, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(":");
                    }
                    Snackbar.make(parent, position + "恭喜你" + sb.toString(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(parent, "没有选择标签", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        //多选
        mMobileTagAdapter = new TagAdapter<>(this);
        mMobileFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        mMobileFlowTagLayout.setAdapter(mMobileTagAdapter);
        mMobileFlowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int positon, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();

                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(":");
                    }
                    Snackbar.make(parent, "O(∩_∩)O哈哈哈~:" + sb.toString(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(parent, "没有选择标签", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        initColorData();

        initSizeData();

        initMobileData();
    }

    private void initMobileData() {
        List<String> dataSource = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataSource.add("多选*-* " + i);

        }
        mMobileTagAdapter.onlyAddAll(dataSource);
    }

    private void initColorData() {
        List<String> dataSource = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataSource.add("单击!+! " + i);

        }
        mColorTagAdapter.onlyAddAll(dataSource);
    }

    /**
     * 初始化数据
     */
    private void initSizeData() {
        List<String> dataSource = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataSource.add("单选^-^ " + i);

        }
        mSizeTagAdapter.onlyAddAll(dataSource);
    }

    //    @Override
    //    public boolean onCreateOptionsMenu(Menu menu) {
    //        // Inflate the menu; this adds items to the action bar if it is present.
    //        getMenuInflater().inflate(R.menu.menu_main, menu);
    //        return true;
    //    }
    //
    //    @Override
    //    public boolean onOptionsItemSelected(MenuItem item) {
    //        // Handle action bar item clicks here. The action bar will
    //        // automatically handle clicks on the Home/Up button, so long
    //        // as you specify a parent activity in AndroidManifest.xml.
    //        int id = item.getItemId();
    //
    //        //noinspection SimplifiableIfStatement
    //        if (id == R.id.action_settings) {
    //            return true;
    //        }
    //
    //        return super.onOptionsItemSelected(item);
    //    }

}
