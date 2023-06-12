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

public class ChatAdapter$ChatLeftViewHolder$$ViewBinder<T extends ChatAdapter.ChatLeftViewHolder> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends ChatAdapter.ChatLeftViewHolder> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.mTvLeftTime = finder.findRequiredViewAsType(source, 2131296632, "field 'mTvLeftTime'", TextView.class);
      target.mTvMsgLeft = finder.findRequiredViewAsType(source, 2131296633, "field 'mTvMsgLeft'", TextView.class);
      target.mImMsgLeft = finder.findRequiredViewAsType(source, 2131296415, "field 'mImMsgLeft'", ImageView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mTvLeftTime = null;
      target.mTvMsgLeft = null;
      target.mImMsgLeft = null;

      this.target = null;
    }
  }
}
