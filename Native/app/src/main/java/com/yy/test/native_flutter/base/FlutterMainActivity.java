package com.yy.test.native_flutter.base;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.view.FlutterMain;
import io.flutter.view.FlutterNativeView;
import io.flutter.view.FlutterView;

/**
 * native->flutter（FlutterActivity为载体）
 */
public class FlutterMainActivity extends FlutterActivity implements MethodChannel.MethodCallHandler {
    private static final String TAG = "FlutterMainActivity";
    private String routeStr = "";
    private static final String TOAST_CHANNEL = "com.test.native_flutter/toast";
    private static final String NAVIGATOR_CHANNEL = "com.test.native_flutter/navigator";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FlutterMain.startInitialization(getApplicationContext());
        super.onCreate(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(this);
        registerCustomPlugin(this);
    }

    private void registerCustomPlugin(PluginRegistry registrar) {
        registerMethodChannel();
    }


    @Override
    public FlutterView createFlutterView(Context context) {
        getIntentData();
        WindowManager.LayoutParams matchParent = new WindowManager.LayoutParams(-1, -1);
        FlutterNativeView nativeView = this.createFlutterNativeView();
        FlutterView flutterView = new FlutterView(FlutterMainActivity.this, (AttributeSet) null, nativeView);
        flutterView.setInitialRoute(routeStr);
        flutterView.setLayoutParams(matchParent);
        this.setContentView(flutterView);
        return flutterView;
    }


    private void getIntentData() {
        String route = getIntent().getStringExtra("__route__");
        String params = getIntent().getStringExtra("__params__");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageParams", params);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        routeStr = route + "?" + jsonObject.toString();

        Log.d(TAG, "onCreate-route:" + route + "-params:" + params);
        Log.d(TAG, "onCreate-routeStr:" + routeStr);
    }


    private void registerMethodChannel() {
        new MethodChannel(this.registrarFor(TOAST_CHANNEL).messenger(),TOAST_CHANNEL).setMethodCallHandler(this);
        new MethodChannel(this.registrarFor(NAVIGATOR_CHANNEL).messenger(),NAVIGATOR_CHANNEL).setMethodCallHandler(this);
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
