// Generated code from Butter Knife. Do not modify!
package com.testlistfresh;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ListviewFreshActivity$$ViewInjector<T extends com.testlistfresh.ListviewFreshActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427391, "field 'cusListviewData'");
    target.cusListviewData = finder.castView(view, 2131427391, "field 'cusListviewData'");
  }

  @Override public void reset(T target) {
    target.cusListviewData = null;
  }
}
