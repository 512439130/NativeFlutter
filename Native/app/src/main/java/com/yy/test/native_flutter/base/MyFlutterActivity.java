package com.yy.test.native_flutter.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import io.flutter.facade.Flutter;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.FlutterView;

/**
 * native->flutter（AppCompatActivity为载体）
 */
public class MyFlutterActivity extends AppCompatActivity implements MethodChannel.MethodCallHandler {
    private static final String TOAST_CHANNEL = "com.test.native_flutter/toast";
    private static final String NAVIGATOR_CHANNEL = "com.test.native_flutter/navigator";
    private FlutterView flutterView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String route = getIntent().getStringExtra("__route__");
        String params = getIntent().getStringExtra("__params__");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageParams", params);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // 创建FlutterView
        flutterView = Flutter.createView(this, getLifecycle(), route + "?" + jsonObject.toString());
        setContentView(flutterView);
        registerMethodChannel();
    }

    @Override
    public void onBackPressed() {
        if(flutterView!=null){
            flutterView.popRoute();
        }else{
            super.onBackPressed();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void registerMethodChannel() {
        new MethodChannel(flutterView, TOAST_CHANNEL).setMethodCallHandler(this);
        new MethodChannel(flutterView, NAVIGATOR_CHANNEL).setMethodCallHandler(this);

    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        switch (methodCall.method) {
            case "showToast": //调用原生的Toast
                String content = methodCall.argument("content");
                Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
                break;
            default:
                result.notImplemented();
        }

    }
}
