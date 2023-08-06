import 'package:flutter/material.dart';

import '../components/components.dart';

Widget testCounter() {
  return const Counter();
}

Widget testShoppingList() {
  return ShoppingList(products: ShoppingListTestData.data());
}

Widget testPlacesOfInterset() {
  return PlacesOfInterset(places: PlacesOfIntersetTestData.data());
}

typedef ComponentCallback = Widget Function();

class DisplayPage extends StatelessWidget {
  const DisplayPage({Key? key, required this.subcomponentName})
      : super(key: key);

  final String subcomponentName;

  @override
  Widget build(BuildContext context) {
    late ComponentCallback subcomponentCallback;

    if (subcomponentName == 'Counter') {
      subcomponentCallback = testCounter;
    } else if (subcomponentName == 'Shopping List') {
      subcomponentCallback = testShoppingList;
    } else if (subcomponentName == 'Places Of Interset') {
      subcomponentCallback = testPlacesOfInterset;
    }

    return Scaffold(
      appBar: AppBar(
        title: Text(subcomponentName),
      ),
      body: Container(
        child: subcomponentCallback(),
      ),
    );
  }
}
