#ifndef DATA_STRUCTURE_FALAT_ERROR_
#define DATA_STRUCTURE_FALAT_ERROR_

#include <cstdlib>
#include <iostream>

// Out of space, Full Stack, Empty Stack
inline void FalatError(char* s) {
  std::cout << *s << std::endl;
  exit(1);
}

#endif
