import 'dart:convert';
import 'dart:io';
import 'dart:ui';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:my_flutter/widget/test.dart';

void main() {
//  runApp(_widgetForRoute(window.defaultRouteName));
  runApp(_widgetForRoute("test"));
  if (Platform.isAndroid) {
    //android同步沉浸式
    SystemUiOverlayStyle systemUiOverlayStyle = SystemUiOverlayStyle(statusBarColor: Colors.transparent);
    SystemChrome.setSystemUIOverlayStyle(systemUiOverlayStyle);
  }
}


Widget _widgetForRoute(String route) {
  String pageName = _getPageName(route);
  Map<String, dynamic> pageParams = json.decode(_parseNativeParams(route));

  print("pageName:" + pageName.toString());
  print("pageParams:" + pageParams.toString());

  switch (pageName) {
    case 'test':
      String content = pageParams["content"] ?? "defaultContent";
      return new Test(content: content,);
    default:
      return Center(
        child: Text('Unknown route: $route', textDirection: TextDirection.ltr),
      );
  }
}

String _getPageName(String route) {
  String pageName = route;
  if (route.indexOf("?") != -1)
    pageName = route.substring(0, route.indexOf("?"));
  print("pageName:" + pageName);
  return pageName;
}

/// 解析native参数，执行初始化操作
/// return 页面参数
String _parseNativeParams(String route) {
  Map<String, dynamic> nativeParams = {};
  if (route.indexOf("?") != -1){
    nativeParams = json.decode(route.substring(route.indexOf("?")+1));
  }
  print("pageParams:" + nativeParams.toString());
  return nativeParams['pageParams'] ?? "{}";
}




