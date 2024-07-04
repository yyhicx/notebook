#include <cstdlib>
#include <iostream>
#include <fstream>
#include <string>

int main(int argc, char* argv[]) {
  std::string filename = "content.txt";
  std::ifstream input(filename);

  if (!input.is_open()) {
    std::cout << "failed to open " << filename << '\n';
    exit(1);
  }

  std::string line;
  while (getline(input, line)) {
    std::cout << line << std::endl;
  }

  return 0;
}
