import 'package:flutter/material.dart';
import 'package:mdi/mdi.dart';

class Place {
  Place({
    required this.name,
    required this.city,
    required this.stars,
    required this.description,
    required this.imagePath,
  });

  final String name;
  final String city;
  final int stars;
  final String description;
  final String imagePath;
}

class PlacesOfIntersetTestData {
  static List<Place> data() {
    return [
      Place(
        name: 'Egyptian Pyramids',
        city: 'Egypt',
        stars: 41,
        description: 'The Egyptian pyramids are ancient masonry structures '
            'located in Egypt. Approximately 80 pyramids were built '
            'within the Kingdom of Kush, now located in the modern '
            'country of Sudan. Of those located in modern Egypt, '
            'most were built as tombs for the country\'s pharaohs and '
            'their consorts during the Old and Middle Kingdom periods.',
        imagePath: 'assets/images/egyptian_pyramids.jpeg',
      ),
      Place(
        name: 'Great Wall Of China',
        city: 'China',
        stars: 50,
        description: 'The Great Wall of China is a series of fortifications '
            'that were built across the historical northern borders '
            'of ancient Chinese states and Imperial China as protection '
            'against various nomadic groups from the Eurasian Steppe. '
            'Several walls were built from as early as the 7th century '
            'BC, with selective stretches later joined together by Qin '
            'Shi Huang (220–206 BC), the first emperor of China. Little '
            'of the Qin wall remains.Later on, many successive dynasties '
            'built and maintained multiple stretches of border walls. '
            'The best-known sections of the wall were built by the Ming dynasty (1368–1644).',
        imagePath: 'assets/images/great_wall_of_china.jpeg',
      ),
      Place(
        name: 'Statue Of Liberty',
        city: 'New York, USA',
        stars: 38,
        description: 'The Statue of Liberty  is a colossal neoclassical '
            'sculpture on Liberty Island in New York Harbor in '
            'New York City, in the United States. The copper statue, '
            'a gift from the people of France to the people of the '
            'United States, was designed by French sculptor Frédéric '
            'Auguste Bartholdi and its metal framework was built by '
            'Gustave Eiffel. The statue was dedicated \'on October 28, 1886.',
        imagePath: 'assets/images/statue_of_liberty.jpeg',
      ),
    ];
  }
}

class PlacesOfInterset extends StatefulWidget {
  const PlacesOfInterset({Key? key, required this.places}) : super(key: key);

  final List<Place> places;

  @override
  State<PlacesOfInterset> createState() => _PlacesOfIntersetState();
}

class _PlacesOfIntersetState extends State<PlacesOfInterset> {
  int _selectedIndex = 0;

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: _buildPage(widget.places, _selectedIndex),
      ),
      bottomNavigationBar: BottomNavigationBar(
        selectedIconTheme:
            const IconThemeData(color: Color.fromARGB(224, 91, 228, 121)),
        selectedItemColor: const Color.fromARGB(224, 91, 228, 121),
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Mdi.alphaEBoxOutline),
            label: 'Egypt',
          ),
          BottomNavigationBarItem(
            icon: Icon(Mdi.alphaCBoxOutline),
            label: 'China',
          ),
          BottomNavigationBarItem(
            icon: Icon(Mdi.alphaUBoxOutline),
            label: 'USA',
          ),
        ],
        currentIndex: _selectedIndex,
        onTap: _onItemTapped,
      ),
    );
  }

  Widget _buildPage(List<Place> places, int index) {
    return ListView(
      children: <Widget>[
        _buildImageSection(places[index].imagePath),
        _buildTitleSection(
            places[index].name, places[index].city, places[index].stars),
        _buildButtonSection(),
        _buildTextSection(places[index].description),
      ],
    );
  }

  Widget _buildImageSection(String imagePath) {
    return Image.asset(
      imagePath,
      width: 480,
      fit: BoxFit.cover,
    );
  }

  Widget _buildTitleSection(String name, String city, int stars) {
    return Container(
      padding: const EdgeInsets.all(32),
      child: Row(
        children: <Widget>[
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Container(
                  padding: const EdgeInsets.only(bottom: 3),
                  child: Text(
                    name,
                    style: const TextStyle(
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
                Text(
                  city,
                  style: TextStyle(
                    color: Colors.grey[500],
                  ),
                ),
              ],
            ),
          ),
          Icon(
            Icons.star,
            color: Colors.red[500],
          ),
          Text(stars.toString()),
        ],
      ),
    );
  }

  Column _buildButtonColumn(Color color, IconData icon, String label) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        Icon(icon, color: color),
        Container(
          margin: const EdgeInsets.only(top: 8),
          child: Text(
            label,
            style: TextStyle(
              fontSize: 12,
              fontWeight: FontWeight.w400,
              color: color,
            ),
          ),
        ),
      ],
    );
  }

  Widget _buildButtonSection() {
    const Color color = Color.fromARGB(225, 27, 98, 179);

    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: <Widget>[
        _buildButtonColumn(color, Icons.call, 'CALL'),
        _buildButtonColumn(color, Icons.near_me, 'ROUTE'),
        _buildButtonColumn(color, Icons.share, 'SHARE'),
      ],
    );
  }

  Widget _buildTextSection(String description) {
    return Padding(
      padding: const EdgeInsets.all(32),
      child: Text(
        description,
        softWrap: true,
      ),
    );
  }
}
