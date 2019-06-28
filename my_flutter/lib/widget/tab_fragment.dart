import 'dart:io';
import "package:flutter/widgets.dart";
import 'package:flutter/services.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:my_flutter/util/navigator_util.dart';
import 'package:my_flutter/util/toast_util.dart';

class TabFragment extends StatefulWidget {
  String content = "Tab3";
  @override
  _TabFragmentState createState() => _TabFragmentState();
}

class _TabFragmentState extends State<TabFragment> {
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

            ],
          ),
        ),
      ),
    );
  }

}
