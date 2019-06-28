package com.yy.test.native_flutter.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yy.test.native_flutter.R;

/**
 * @author SoMustYY
 * @create 2019/6/28 6:12 PM
 * @organize 卓世达科
 * @describe
 * @update
 */
public class NullFragment extends Fragment {
    public static NullFragment newInstance() {
        Bundle args = new Bundle();
        NullFragment fragment = new NullFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_null,null);
    }
}
