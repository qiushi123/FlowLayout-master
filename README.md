Android流式布局，支持单选、多选等，适合用于产品标签等，可以自定义标签颜色，标签大小，标签距离。

特色
可以单选多选，使用方便
可以自定义标签颜色，标签大小，标签距离
以setAdapter形式注入数据
直接设置selector为background即可完成标签选则的切换，类似CheckBox
支持控制选择的Tag数量，比如：单选、多选
支持setOnTagClickListener，当点击某个Tag回调
支持setOnSelectListener，当选择某个Tag后回调
支持adapter.notifyDataChanged
Activity重建（或者旋转）后，选择的状态自动保存

使用步骤
1，将项目里的类复制到你的项目中（这是源码奥，可以自己根据需求自己改，比导入类库灵活）

2，布局文件中声明：
	

3，常用设置（单选，多选，仅仅点击，默认选中项等）
	//这里FLOW_TAG_CHECKED_SINGLE是单选，FLOW_TAG_CHECKED_MULTI多选，默认是仅仅点击
	mSizeFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);

	mSizeTagAdapter.setSelected(4);//默认选择第四个


4，事件
	4-1选中事件
	 mSizeFlowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int position, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : selectedList) {
					//这里的parent.getAdapter().getItem(i)用来获取选中的标签的数据
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

	4-2点击事件
		 mColorFlowTagLayout.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                Snackbar.make(view, "点击了标签"+position, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

5，可以自定义标签大小距离等属性	
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
		//这里大家熟悉吧，就是使用listview的adapter，这里的布局就是标签的布局，可以自己定义
        View view = LayoutInflater.from(mContext).inflate(R.layout.tag_item, null);
        if (t instanceof String) {
            textView.setText((String) t);
        }
        return view;
    }

	上面tag_item的布局（是不是感觉自定义定义标签so easy）
		<?xml version="1.0" encoding="utf-8"?>
		<LinearLayout
			xmlns:android="http://schemas.android.com/apk/res/android"
			android:layout_width="wrap_content"
			android:layout_height="wrap_content"
			>
			<TextView
				android:id="@+id/tv_tag"
				android:layout_width="wrap_content"
				android:layout_height="wrap_content"
				android:layout_marginLeft="10dp"
				android:layout_marginTop="10dp"
				android:background="@drawable/blue_rect_round_bg"
				android:paddingBottom="5dp"
				android:paddingLeft="10dp"
				android:paddingRight="10dp"
				android:paddingTop="5dp"
				/>
		</LinearLayout>

6,自定义标签选中颜色，
	上面5中的tv_tag的background属性，可以自己定义选中颜色





