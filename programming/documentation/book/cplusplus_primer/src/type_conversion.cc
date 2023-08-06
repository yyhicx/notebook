#include <iostream>

int main(int argc, char* argv[]) {
  const int kPositiveNumber = 1.2;
  const int kNegativeNnumber = -1.2;

  std::cout << kPositiveNumber << std::endl;
  std::cout << kNegativeNnumber << std::endl;

  const long long kBigNumber = 111111111111111;
  const double kFloatingPointNumber = kBigNumber;

  std::cout << kBigNumber << std::endl;
  std::cout << kFloatingPointNumber << std::endl;

  const unsigned char kUnsignedChar = -1;
  const char kChar = 255;

  std::cout << (int)kUnsignedChar << std::endl;
  std::cout << (int)kChar << std::endl;

  return 0;
}
