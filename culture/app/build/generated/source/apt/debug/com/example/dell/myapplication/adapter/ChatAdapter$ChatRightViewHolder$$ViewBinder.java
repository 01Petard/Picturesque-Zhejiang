// Generated code from Butter Knife. Do not modify!
package com.example.dell.myapplication.adapter;

import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ChatAdapter$ChatRightViewHolder$$ViewBinder<T extends ChatAdapter.ChatRightViewHolder> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends ChatAdapter.ChatRightViewHolder> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.mTvRightTime = finder.findRequiredViewAsType(source, 2131296635, "field 'mTvRightTime'", TextView.class);
      target.mTvMsgRight = finder.findRequiredViewAsType(source, 2131296634, "field 'mTvMsgRight'", TextView.class);
      target.mImMsgRight = finder.findRequiredViewAsType(source, 2131296416, "field 'mImMsgRight'", ImageView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mTvRightTime = null;
      target.mTvMsgRight = null;
      target.mImMsgRight = null;

      this.target = null;
    }
  }
}
