// Generated code from Butter Knife. Do not modify!
package com.example.dell.myapplication.function;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class FuntionIntroduce$$ViewBinder<T extends FuntionIntroduce> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends FuntionIntroduce> implements Unbinder {
    protected T target;

    private View view2131296431;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      view = finder.findRequiredView(source, 2131296431, "field 'jump' and method 'onViewClicked'");
      target.jump = finder.castView(view, 2131296431, "field 'jump'");
      view2131296431 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked();
        }
      });
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.jump = null;

      view2131296431.setOnClickListener(null);
      view2131296431 = null;

      this.target = null;
    }
  }
}
