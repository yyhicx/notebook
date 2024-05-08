import 'package:fluro/fluro.dart';
import './route_handles.dart';

class Routes {
  static String root = '/';
  static String display = '/display';

  static void configureRoutes(FluroRouter router) {
    router.define(root, handler: rootHandler);
    router.define(display, handler: displayHandler);
  }
}
