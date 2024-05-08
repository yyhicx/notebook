import 'package:fluro/fluro.dart';
import 'package:flutter/material.dart';

import '../pages/home.dart';
import '../pages/display.dart';

var rootHandler = Handler(
    handlerFunc: (BuildContext? context, Map<String, List<String>> params) {
  return const HomePage(title: 'Flutter Example Home Page');
});

var displayHandler = Handler(
    handlerFunc: (BuildContext? context, Map<String, List<String>> params) {
  String? name = params['key']?.first;
  if (name != null) {
    return DisplayPage(subcomponentName: name);
  } else {
    return const DisplayPage(subcomponentName: 'Counter');
  }
});
