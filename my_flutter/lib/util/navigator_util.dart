import "package:flutter/widgets.dart";
import 'package:flutter/services.dart';
import 'dart:io';

class NavigatorUtil extends Navigator {

  static const MethodChannel _methodChannel = const MethodChannel('com.test.native_flutter/navigator');

  static bool canPop(BuildContext context) {
    return Navigator.canPop(context);
  }

  @optionalTypeArgs
  static bool pop<T extends Object>(BuildContext context, [ T result ]) {
    // 移除当前焦点
    FocusScope.of(context).requestFocus(FocusNode());
    if (canPop(context)) {
      return Navigator.pop(context, result);
    } else {
      Platform.isIOS ? nativePop() : SystemNavigator.pop();
      return true;
    }
  }

  static void close(BuildContext context) {
    // 移除当前焦点
    FocusScope.of(context).requestFocus(FocusNode());
    Platform.isIOS ? nativePop() : SystemNavigator.pop();
  }

  @optionalTypeArgs
  static Future<T> push<T extends Object>(BuildContext context, Route<T> route) {
    // 移除当前焦点
    FocusScope.of(context).requestFocus(FocusNode());
    return Navigator.push(context, route);
  }

  /// Native Interface
  // Native端的页面pop操作
  static Future<String> nativePop() async{
    return await _methodChannel.invokeMethod('pop');
  }
}