#include <iostream>
#include <vector>

int main(int argc, char* argv[]) {
  std::vector<int> marks = {42, 65, 95, 100, 39, 67, 95, 76,
                            88, 76, 83, 92, 76, 93};

  std::vector<int> scores(11, 0);

  for (const auto m : marks)
    if (m <= 100)
      ++scores[m/10];

  // A+ 100 – 90
  // A  89 – 80
  // B+ 79 – 70
  // B  69 – 60
  // C  59 – 50
  // D  49 – 40
  // E  39 – 30
  // F  29 – 0
  std::cout << "There are " << scores[9] + scores[10] << " students with grade A+" << '\n'
            << "There are " << scores[8] << " students with grade A" << '\n'
            << "There are " << scores[7] << " students with grade B+" << '\n'
            << "There are " << scores[6] << " students with grade B" << '\n'
            << "There are " << scores[5] << " students with grade C" << '\n'
            << "There are " << scores[4] << " students with grade D" << '\n'
            << "There are " << scores[3] << " students with grade E" << '\n'
            << "There are " << scores[0] + scores[1] + scores[2] << " students with grade F"
            << std::endl;

  return 0;
}
