package com.yy.test.native_flutter.base;

import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import io.flutter.facade.Flutter;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.view.FlutterView;

public class MyFlutterFragment extends Fragment {
    private static final String TAG = "MyFlutterFragment";
    public static final String ARG_ROUTE = "__route__";
    public static final String PARAMS = "__params__";
    private String mRoute = "/";
    private String mParams = "";
    private FlutterView mFlutterView;
    public static MyFlutterFragment newInstance(String route,String params) {
        Bundle args = new Bundle();
        MyFlutterFragment fragment = new MyFlutterFragment();
        args.putString(MyFlutterFragment.ARG_ROUTE, route);
        args.putString(MyFlutterFragment.PARAMS, params);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRoute = getArguments().getString(ARG_ROUTE);
            mParams = getArguments().getString(PARAMS);
            //拼接参数
            JSONObject jsonObject = new JSONObject();
            try {
                JSONObject pageParmasObject;
                if(!TextUtils.isEmpty(mParams)){
                    pageParmasObject = new JSONObject(mParams);
                    jsonObject.put("pageParams", pageParmasObject);
                    mRoute = mRoute + "?" + jsonObject.toString();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView-mRoute:"+mRoute);
        mFlutterView = Flutter.createView(getActivity(), getLifecycle(), mRoute);
        //综合解决闪屏,布局覆盖问题
        mFlutterView.setZOrderOnTop(true);
        mFlutterView.setZOrderMediaOverlay(false);
        mFlutterView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        mFlutterView.setBackgroundColor(Color.parseColor("#00000000"));

        //注册channel
        GeneratedPluginRegistrant.registerWith(mFlutterView.getPluginRegistry());

        return mFlutterView;
    }
}
