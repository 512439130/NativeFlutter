import 'dart:io';
import 'package:dio/dio.dart';
import "package:flutter/widgets.dart";
import 'package:flutter/services.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:my_flutter/util/http_util.dart';
import 'package:my_flutter/util/navigator_util.dart';
import 'package:my_flutter/util/toast_util.dart';

class TabFragment extends StatefulWidget {
  String content = "Tab3";
  @override
  _TabFragmentState createState() => _TabFragmentState();
}

class _TabFragmentState extends State<TabFragment> {
  
  String text = "这是默认内容";
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return MaterialApp(
      home: new Scaffold(

        body: new Container(
          color: Colors.greenAccent,
          child: new ListView(
            children: <Widget>[
              new Padding(padding: EdgeInsets.only(top: 200)),
              new Container(
                alignment: Alignment.center,
                child: new Text(
                  widget.content,
                  style: new TextStyle(
                      color: Colors.white,
                      fontSize: 40,
                      fontWeight: FontWeight.normal,
                      decoration: TextDecoration.none),
                ),

              ),
              new Padding(padding: EdgeInsets.only(top: 200)),
              new Container(
                width: 100,
                alignment: Alignment.center,
                child: new MaterialButton(
                  child: new Text("网络请求测试"),
                  color: Colors.greenAccent,
                  onPressed: () {
                    buttonClick();
                  },
                ),
              ),
              new Container(
                alignment: Alignment.center,
                child: new Text(
                  text,
                  style: new TextStyle(
                      color: Colors.white,
                      fontSize: 20,
                      fontWeight: FontWeight.normal,
                      decoration: TextDecoration.none),
                ),

              ),
            ],
          ),
        ),
      ),
    );

  }

  void buttonClick() async{
    Response response = await HttpUtil().doGet("api/test");
    if (response != null) {
      if (response.statusCode == 200) {
        setState(() {
          print("请求成功-response:"+response.data.toString());
          text = response.data.toString();
        });
      } else {
        print("请求失败，请检查网络后重试");
      }
    } else {
      print("请求失败，请检查网络后重试");
    }
  }

}
