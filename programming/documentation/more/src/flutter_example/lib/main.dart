import 'package:flutter/material.dart';
import 'package:fluro/fluro.dart';

// for test
// https://stackoverflow.com/questions/49219093/debugpaintsizeenabled-is-not-working-in-flutter
// import 'package:flutter/rendering.dart';

// theme
import 'src/theme/app.dart';

// route
import 'src/config/application.dart';
import 'src/config/routes.dart';

void main() {
  // for test
  // debugPaintSizeEnabled = true;
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  _MyAppState() {
    final router = FluroRouter();
    Routes.configureRoutes(router);
    Application.router = router;
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Example',
      theme: AppTheme.lightTheme,
      darkTheme: AppTheme.darkTheme,
      debugShowCheckedModeBanner: false,
      onGenerateRoute: Application.router.generator,
    );
  }
}
