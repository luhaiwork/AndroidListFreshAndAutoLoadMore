package com.testlistfresh;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.kstapp.wanshida.R;

public class ListviewFreshActivity extends Activity implements
		CusListviewData.ICallBack,OnItemClickListener {
	@InjectView(R.id.cus_listview)
	CusListviewData cusListviewData;
	private OrderListAdapter adapter;
	// 从服务器获取的全部预订信息列表
	private List<OrderListItemBean> allList;
	// 当前分类、当前页面需要现实的List
	private List<OrderListItemBean> currentList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listviewfresh);
		ButterKnife.inject(this);
		allList = new ArrayList<OrderListItemBean>();
		currentList = new ArrayList<OrderListItemBean>();
		adapter = new OrderListAdapter(this, currentList);
		View exceptionView = ExceptionContentView.exceptView(this,
				ExceptionContentView.NO_DATA_ORDER);
		cusListviewData.init(this, exceptionView, adapter);
		cusListviewData.fresh();
		ListView listView = cusListviewData.getListView();
		listView.setOnItemClickListener(this);
	}

	private final int REFRESH_COMPLETE = 0;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case REFRESH_COMPLETE:
				cusListviewData.setFinishLoading();
				break;
			}
		};
	};
	// 标记是否可以加载更多
	private int pageIndex = 1; // 当前页

	private void getContent(final boolean isRefresh) {
		new AsyncTask<String, Integer, Boolean>() {
			@Override
			protected Boolean doInBackground(String... params) {
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(Boolean result) {
				afterExecute(isRefresh);
			};

		}.execute("");
	}

	private void afterExecute(final boolean isFresh) {
		allList = new ArrayList<OrderListItemBean>();
		if (pageIndex < 4) {
			for (int i = 0; i < 20; i++) {
				OrderListItemBean bean = new OrderListItemBean();
				bean.setOrder_id("order_id_i:" + i + " page:" + pageIndex);
				allList.add(bean);
			}
		} else {
			cusListviewData.noMoreData();
		}
		if (isFresh) {
			currentList.clear();
		}
		currentList.addAll(allList);
		adapter.notifyDataSetChanged();
		cusListviewData.setFinishLoading();
		// View exceptionView = ExceptionContentView.exceptView(
		// ListviewFreshActivity.this, ExceptionContentView.NO_NET);
		// cusListviewData.updateEmptyView(exceptionView);
	}

	@Override
	public void onRefresh() {
		pageIndex = 1;
		getContent(true);
	}

	@Override
	public void onLoadMore() {
		pageIndex += 1;
		getContent(false);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Utility.showToast(this, "position:"+position);
	}

}
