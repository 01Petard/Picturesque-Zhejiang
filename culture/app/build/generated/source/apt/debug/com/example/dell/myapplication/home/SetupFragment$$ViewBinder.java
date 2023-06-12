// Generated code from Butter Knife. Do not modify!
package com.example.dell.myapplication.home;

import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class SetupFragment$$ViewBinder<T extends SetupFragment> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends SetupFragment> implements Unbinder {
    protected T target;

    private View view2131296657;

    private View view2131296305;

    private View view2131296487;

    protected InnerUnbinder(final T target, final Finder finder, Object source) {
      this.target = target;

      View view;
      view = finder.findRequiredView(source, 2131296657, "field 'wifiL' and method 'onViewClicked'");
      target.wifiL = finder.castView(view, 2131296657, "field 'wifiL'");
      view2131296657 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(finder.<RelativeLayout>castParam(p0, "doClick", 0, "onViewClicked", 0));
        }
      });
      view = finder.findRequiredView(source, 2131296305, "field 'apL' and method 'onViewClicked'");
      target.apL = finder.castView(view, 2131296305, "field 'apL'");
      view2131296305 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(finder.<RelativeLayout>castParam(p0, "doClick", 0, "onViewClicked", 0));
        }
      });
      view = finder.findRequiredView(source, 2131296487, "field 'webL' and method 'onViewClicked'");
      target.webL = finder.castView(view, 2131296487, "field 'webL'");
      view2131296487 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(finder.<RelativeLayout>castParam(p0, "doClick", 0, "onViewClicked", 0));
        }
      });
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.wifiL = null;
      target.apL = null;
      target.webL = null;

      view2131296657.setOnClickListener(null);
      view2131296657 = null;
      view2131296305.setOnClickListener(null);
      view2131296305 = null;
      view2131296487.setOnClickListener(null);
      view2131296487 = null;

      this.target = null;
    }
  }
}
