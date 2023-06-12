package com.qingbai.idylls.shilu;

        import android.content.Context;
        import android.widget.FrameLayout;

        import androidx.lifecycle.LiveData;
        import androidx.lifecycle.MutableLiveData;
        import androidx.lifecycle.ViewModel;

public class ShiluModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ShiluModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}