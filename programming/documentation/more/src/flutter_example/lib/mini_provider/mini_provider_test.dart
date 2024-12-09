import 'dart:collection';

import 'package:flutter/material.dart';

import 'package:flutter_example/mini_provider/change_notifier_provider.dart';
import 'package:flutter_example/mini_provider/consumer.dart';

class Item {
  Item(this.name, this.price, this.count);

  String name;
  double price;
  int count;
}

class CartModel extends ChangeNotifier {
  final List<Item> _items = [];

  UnmodifiableListView<Item> get items => UnmodifiableListView<Item>(_items);

  double get totalPrice =>
      _items.fold(0, (sum, item) => sum + item.price * item.count);

  void addItem(Item item) {
    _items.add(item);
    notifyListeners();
  }

  void removeItem(Item item) {
    _items.remove(item);
    notifyListeners();
  }
}

class MiniProviderExample extends StatefulWidget {
  const MiniProviderExample({super.key});

  @override
  State<MiniProviderExample> createState() => _MiniProviderExampleState();
}

class _MiniProviderExampleState extends State<MiniProviderExample> {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: ChangeNotifierProvider<CartModel>(
        data: CartModel(),
        child: Column(
          children: <Widget>[
            Consumer<CartModel>(
                builder: (context, cart) =>
                    Text('Total Price: ${cart.totalPrice}')),
            Builder(builder: (context) {
              return ElevatedButton(
                onPressed: () {
                  ChangeNotifierProvider.of<CartModel>(context, listen: false)
                      .addItem(Item('Item 1', 10.0, 1));
                },
                child: const Text('Add Item'),
              );
            }),
          ],
        ),
      ),
    );
  }
}
