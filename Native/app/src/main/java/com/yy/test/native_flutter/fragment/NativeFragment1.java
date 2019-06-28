package com.yy.test.native_flutter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.yy.test.native_flutter.R;
import com.yy.test.native_flutter.base.FlutterMainActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SoMustYY
 * @create 2019/6/28 6:12 PM
 * @organize 卓世达科
 * @describe
 * @update
 */
public class NativeFragment1 extends Fragment {
    private Button btnNativeToFlutter;
    private View mView;

    public static NativeFragment1 newInstance() {

        Bundle args = new Bundle();

        NativeFragment1 fragment = new NativeFragment1();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_native1,null);
        init();
        return mView;
    }


    private void init() {
        initView();
        initListener();
    }


    private void initView() {
        btnNativeToFlutter = mView.findViewById(R.id.btn_native_to_flutter);
    }

    private void initListener() {
        btnNativeToFlutter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<>();
                map.put("content", "这是测试内容");
                String jsonString = new Gson().toJson(map);
                String route = "test";

                Intent intent = new Intent(getActivity(), FlutterMainActivity.class);
                intent.putExtra("__route__", route);
                intent.putExtra("__params__", jsonString);
                startActivity(intent);
            }
        });
    }
}
