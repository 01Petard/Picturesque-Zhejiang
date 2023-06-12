// Generated code from Butter Knife. Do not modify!
package com.example.dell.myapplication.home;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class BoardFragment$$ViewBinder<T extends BoardFragment> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends BoardFragment> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.mRvChatList = finder.findRequiredViewAsType(source, 2131296512, "field 'mRvChatList'", RecyclerView.class);
      target.mEtContent = finder.findRequiredViewAsType(source, 2131296387, "field 'mEtContent'", EditText.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.mRvChatList = null;
      target.mEtContent = null;

      this.target = null;
    }
  }
}
