import 'package:flutter/material.dart';

import '../config/application.dart';

final Map<String, Map<String, List<String>>> data = {
  'User Interface': {
    'Introduction To Widgets': ['Counter', 'Shopping List'],
    'Building Layouts': ['Places Of Interset'],
  },
  'Data & Backend': {
    'State Management': [],
    'Networking & HTTP': [],
  },
  'Accessibility & Internationalization': {},
  'Platform Integration': {},
  'Packages & Plugins': {},
};

class HomePage extends StatelessWidget {
  const HomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: data.keys.length,
      child: Scaffold(
        drawer: _buildNavBar(context),
        appBar: AppBar(
          bottom: TabBar(
            isScrollable: true,
            tabs: <Widget>[
              ...data.keys.map(((k) => Tab(text: k))),
            ],
          ),
          title: const Text('Flutter Example'),
        ),
        body: TabBarView(
          children: <Widget>[
            ...data.values.map((v) => _buildCardList(context, v)),
          ],
        ),
      ),
    );
  }

  Widget _buildNavBar(BuildContext context) {
    return Drawer(
      child: ListView(
        // Remove padding
        padding: EdgeInsets.zero,
        children: <Widget>[
          UserAccountsDrawerHeader(
            accountName: const Text('CHNxish'),
            accountEmail: const Text('1095219764@qq.com'),
            currentAccountPicture: CircleAvatar(
              child: ClipOval(
                child: Image.asset(
                  'assets/images/profile.jpeg',
                  fit: BoxFit.cover,
                  width: 100,
                  height: 100,
                ),
              ),
            ),
            decoration: BoxDecoration(
              color: Theme.of(context).primaryColor,
              image: const DecorationImage(
                fit: BoxFit.fill,
                image: AssetImage(
                  'assets/images/background_image.jpeg',
                ),
              ),
            ),
          ),
          ListTile(
            leading: const Icon(Icons.favorite),
            title: const Text('Favorites'),
            onTap: () {},
          ),
          ListTile(
            leading: const Icon(Icons.person),
            title: const Text('Friends'),
            onTap: () {},
          ),
          ListTile(
            leading: const Icon(Icons.share),
            title: const Text('Share'),
            onTap: () {},
          ),
          ListTile(
            leading: const Icon(Icons.notifications),
            title: const Text('Request'),
            onTap: () {},
            trailing: ClipOval(
              child: Container(
                color: Colors.red,
                width: 20,
                height: 20,
                child: const Center(
                  child: Text(
                    '8',
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 12,
                    ),
                  ),
                ),
              ),
            ),
          ),
          const Divider(),
          ListTile(
            leading: const Icon(Icons.settings),
            title: const Text('Settings'),
            onTap: () {},
          ),
          ListTile(
            leading: const Icon(Icons.description),
            title: const Text('Policies'),
            onTap: () {},
          ),
          const Divider(),
          ListTile(
            leading: const Icon(Icons.exit_to_app),
            title: const Text('Exit'),
            onTap: () {},
          ),
        ],
      ),
    );
  }

  Widget _buildCardList(BuildContext context, Map<String, List<String>> data) {
    return Column(
      children: <Widget>[
        const SizedBox(
          height: 5,
        ),
        ...data.entries.map(
          (e) => Card(
            elevation: 0,
            child: Column(
              children: <Widget>[
                Container(
                  width: double.infinity,
                  height: 30,
                  padding: const EdgeInsets.fromLTRB(5, 6, 0, 0),
                  color: Colors.black26,
                  child: Text(e.key),
                ),
                ...e.value.map(
                  (v) => GestureDetector(
                    onTap: () {
                      tappedList(context, v);
                    },
                    child: Container(
                      width: double.infinity,
                      height: 50,
                      padding: const EdgeInsets.fromLTRB(15, 12, 0, 0),
                      decoration: const BoxDecoration(
                        color: Colors.black12,
                        border: Border(
                          top: BorderSide(
                            width: 1,
                            color: Colors.grey,
                          ),
                        ),
                      ),
                      child: Text(v, style: const TextStyle(fontSize: 20)),
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
      ],
    );
  }

  // actions
  void tappedList(BuildContext context, String key) {
    String route = '/display?key=$key';

    Application.router.navigateTo(context, route);
  }
}
