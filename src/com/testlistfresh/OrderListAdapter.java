package com.testlistfresh;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.kstapp.wanshida.R;

/**
 * 订单列表adapter
 * 
 * @author luh
 * 
 */
public class OrderListAdapter extends BaseAdapter implements OnClickListener {
	private Context context;
	private List<OrderListItemBean> list;
	public OrderListAdapter(Context context, List<OrderListItemBean> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.order_list_item, null);
			holder = new ViewHolder();
//			holder.tv_order_status_desc = (TextView) convertView
//					.findViewById(R.id.tv_order_status_desc);
			ButterKnife.inject(holder, convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final OrderListItemBean orderListItemBean = list.get(position);
		holder.tv_order_status_desc.setText(orderListItemBean.getOrder_id());
		holder.btn_click.setTag("btn:"+position);
		return convertView;
	}

	class ViewHolder {
		@InjectView(R.id.tv_order_status_desc)
		TextView  tv_order_status_desc;
		@InjectView(R.id.btn_click)
		Button btn_click;
		@OnClick(R.id.btn_click)
		void btnClick(){
			Utility.showToast(context, String.valueOf(btn_click.getTag()));
		}
	}

	@Override
	public void onClick(View v) {
	}

}
