package com.testlistfresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.dobmob.doblist.DobList;
import com.dobmob.doblist.events.OnLoadMoreListener;
import com.dobmob.doblist.exceptions.NoEmptyViewException;
import com.dobmob.doblist.exceptions.NoListViewException;
import com.kstapp.wanshida.R;

public class CusListviewData extends RelativeLayout {
	RelativeLayout wholeLin;
	ListView listView;
	SwipeRefreshLayout swiptFreshLayout;
	private DobList dobList;
	private ICallBack callback;
	private View emptyView;
	private BaseAdapter adapter;

	public CusListviewData(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.cus_listview, this);
	}

	public void init(ICallBack callbackParam, View exceptionView,
			BaseAdapter adapterParam) {
		callback = callbackParam;
		adapter = adapterParam;
		emptyView=exceptionView;
		wholeLin = (RelativeLayout) findViewById(R.id.order_whole_view);
		listView = (ListView) findViewById(R.id.order_listview);
		swiptFreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiptFreshLayout);
		swiptFreshLayout
				.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
					@Override
					public void onRefresh() {
						dobList.setNoMoreData(false);
//						callback.onRefresh();
						fresh();
					}
				});
//		swiptFreshLayout.setEnabled(false);
		initDobList(wholeLin, listView);
		listView.setAdapter(adapter);
	}

	private void initDobList(View rootView, ListView listView) {
		// DobList initializing
		dobList = new DobList();
		try {
			// Register ListView
			//
			// NoListViewException will be thrown when
			// there is no ListView
			dobList.register(listView);

			// Add ProgressBar to footers of ListView
			// to be shown in loading more
//			dobList.addDefaultLoadingFooterView();
			dobList.setFooterLoadingView(R.layout.cus_loading);
			dobList.addDefaultFinishLoadingFooterView();
			// Sets the view to show if the adapter is empty
			// see startCentralLoading() method
			// View noItems = rootView.findViewById(R.id.noItems);
			wholeLin.addView(emptyView);
			dobList.setEmptyView(emptyView);
			// Callback called when reaching last item in ListView
			dobList.setOnLoadMoreListener(new OnLoadMoreListener() {
				@Override
				public void onLoadMore(final int totalItemCount) {
					callback.onLoadMore();
				}
			});

		} catch (NoListViewException e) {
			e.printStackTrace();
		}
		swiptFreshLayout.setVisibility(View.GONE);
	}
	/**
	 *  更新数据为空view
	 */
	public void updateEmptyView(View exceptionView){
		if(emptyView!=null){
			wholeLin.removeView(emptyView);
		}
		wholeLin.addView(exceptionView);
		emptyView=exceptionView;
		dobList.setEmptyView(exceptionView);
	}
	public void fresh() {
		try {
			// Show ProgressBar at the center of ListView
			// this can be used while loading data from
			// server at the first time
			//
			// setEmptyView() must be called before
			//
			// NoEmptyViewException will be thrown when
			// there is no EmptyView
			dobList.startCentralLoading();
		} catch (NoEmptyViewException e) {
			e.printStackTrace();
		}
		callback.onRefresh();
	}

	// public void setCallBack(ICallBack callback) {
	// this.callback = callback;
	// }

	private void setFreshingFinished() {
		swiptFreshLayout.setRefreshing(false);
	}
	private void finishLoadingMore() {
		dobList.finishLoading();
	}
	public void setFinishLoading(){
		if(adapter.getCount()>0){
			showList();
		}
		setFreshingFinished();
		finishLoadingMore();
	}
	public void noMoreData(){
		dobList.setNoMoreData(true);
	}
	public void showList(){
		swiptFreshLayout.setVisibility(View.VISIBLE);
	}


	public BaseAdapter getAdapter() {
		return adapter;
	}
	
	public interface ICallBack {
		void onRefresh();

		void onLoadMore();
	}
	/**
	 * 得到控件中的listview
	 * @return
	 */
	public ListView getListView(){
		return listView;
	}
}
