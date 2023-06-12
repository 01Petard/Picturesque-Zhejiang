package com.example.dell.myapplication.function;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.myapplication.BaseFragment;
import com.example.dell.myapplication.R;
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.IOException;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class FunctionSummary extends BaseFragment {

    View view;
    private VrPanoramaView vr_pan_view;
    private final String TAG = "VrPanoramaView";

    public FunctionSummary() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_summary, container, false);

        load360Image();

        // Inflate the layout for this fragment
        return view;
    }

    private void load360Image() {
        vr_pan_view = (VrPanoramaView) view.findViewById(R.id.vr_pan_view);
        InputStream open = null;
        try {
            open = mContext.getAssets().open("vr.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(open);
        VrPanoramaView.Options options = new VrPanoramaView.Options();
        options.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
        vr_pan_view.setEventListener(new VrPanoramaEventListener() {
            @Override
            public void onDisplayModeChanged(int newDisplayMode) {
                super.onDisplayModeChanged(newDisplayMode);
            }
            @Override
            public void onLoadError(String errorMessage) {
                super.onLoadError(errorMessage);
                Log.d(TAG, "onLoadError()->errorMessage=" + errorMessage);
            }
            @Override
            public void onLoadSuccess() {
                super.onLoadSuccess();
            }
            @Override
            public void onClick() {
                super.onClick();
            }
        });
        vr_pan_view.loadImageFromBitmap(bitmap, options);
    }

}
