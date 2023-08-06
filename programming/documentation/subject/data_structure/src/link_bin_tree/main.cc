#include "link_bin_tree.h"
#include <iostream>

template <typename T>
void Print(const T& t) {
  std::cout << t << std::endl;
}

int main(int argc, char* argv[]) {
  int data[11] = {7, 3, 9, 1, 5, 8, 10, 0, 2, 4, 6};

  LinkBinTree<int> lbt1;
  for (int i = 0; i < 11; i++) {
    lbt1.InsertElem(data[i]);
  }
  
  std::cout << lbt1.GetSize() << std::endl;
  std::cout << lbt1.GetHeight() << std::endl;

  LinkBinTree<int> lbt2 = lbt1;

  std::cout << lbt2.GetSize() << std::endl;

  lbt1.PreOrder(Print);
  lbt1.InOrder(Print);
  lbt2.PostOrder(Print);
  lbt2.LevelOrder(Print);

  return 0;
}
