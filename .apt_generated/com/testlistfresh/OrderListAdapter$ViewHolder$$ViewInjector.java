// Generated code from Butter Knife. Do not modify!
package com.testlistfresh;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class OrderListAdapter$ViewHolder$$ViewInjector<T extends com.testlistfresh.OrderListAdapter.ViewHolder> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427400, "field 'tv_order_status_desc'");
    target.tv_order_status_desc = finder.castView(view, 2131427400, "field 'tv_order_status_desc'");
    view = finder.findRequiredView(source, 2131427401, "field 'btn_click' and method 'btnClick'");
    target.btn_click = finder.castView(view, 2131427401, "field 'btn_click'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.btnClick();
        }
      });
  }

  @Override public void reset(T target) {
    target.tv_order_status_desc = null;
    target.btn_click = null;
  }
}
