#include <iostream>

#define PRINT_NAME(x) \
    std::cout << "Name: " << x << std::endl;

int main(int argc, char* argv[]) {
  char kUsername1[] = "abcdefghijklmn"
                      "opqrstuvwxyz";

  char kUsername2[] = "abcdefghijklmn\
                      opqrstuvwxyz";

  std::cout << "Hello, Xish. I'm name is "
      << kUsername1 << std::endl;

  PRINT_NAME(kUsername1);
  PRINT_NAME(kUsername2);

  return 0;
}
