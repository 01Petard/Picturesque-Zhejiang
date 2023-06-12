package com.qingbai.idylls.faxian;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.qingbai.idylls.R;

public class liuyanFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_liuyan_item,container,false);
        TextView tv_user_comment = view.findViewById(R.id.user_comment);
        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        String comment = null;
        if(bundle != null){
            comment = bundle.getString("data");
        }
        Log.i("liuyanFragment",comment);
        tv_user_comment.setText(comment);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
