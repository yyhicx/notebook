#include <iostream>

const int kReused = 42;

int main(int argc, char* argv[]) {
  const int kUnique = 0;
  std::cout << kReused << " " << kUnique << std::endl;
  const int kReused = 0;
  std::cout << kReused << " " << kUnique << std::endl;
  std::cout << ::kReused << " " << kUnique << std::endl;
  
  return 0;
}
