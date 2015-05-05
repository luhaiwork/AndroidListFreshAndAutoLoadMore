AndroidListFreshAndAutoLoadMore
=====
Description
----
Android 库，用于对Listview 下拉刷新和上拉加载的封装。
How?
----
```java
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
```
