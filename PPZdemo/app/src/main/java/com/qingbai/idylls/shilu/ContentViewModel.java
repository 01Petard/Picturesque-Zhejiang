package com.qingbai.idylls.shilu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContentViewModel extends ViewModel {
    private MutableLiveData<String> mText_right;
    private MutableLiveData<String> mText_bottom;

    public ContentViewModel() {
        mText_right = new MutableLiveData<>();
        mText_bottom = new MutableLiveData<>();
        mText_right.setValue("This is home fragment");
        mText_bottom.setValue("This is home fragment");
    }

    public LiveData<String> getText_right() {
        return mText_right;
    }
    public LiveData<String> getText_bottom() {
        return mText_bottom;
    }
}
