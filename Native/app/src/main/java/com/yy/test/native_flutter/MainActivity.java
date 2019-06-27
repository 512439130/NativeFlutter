package com.yy.test.native_flutter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.yy.test.native_flutter.base.FlutterMainActivity;

import java.util.HashMap;
import java.util.Map;

import io.flutter.app.FlutterActivity;

public class MainActivity extends FlutterActivity {
    private Button btnNativeToFlutter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        btnNativeToFlutter = findViewById(R.id.btn_native_to_flutter);
    }

    private void initListener() {
        btnNativeToFlutter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<>();
                map.put("content", "这是测试内容");
                String jsonString = new Gson().toJson(map);
                String route = "test";

                Intent intent = new Intent(MainActivity.this, FlutterMainActivity.class);
                intent.putExtra("__route__", route);
                intent.putExtra("__params__", jsonString);
                startActivity(intent);
            }
        });
    }
}
