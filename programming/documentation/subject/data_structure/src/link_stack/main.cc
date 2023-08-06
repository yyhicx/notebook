#include "link_stack.h"
#include <iostream>

template <typename T>
void Print(const T& t) {
  std::cout << t << std::endl;
}

int main(int argc, char* argv[]) {
  int data[10];
  for (int i = 0; i < 10; i++)
    data[i] = i;

  LinkStack<int> ls1;
  LinkStack<int> ls2(data, 10);
  LinkStack<int> ls3 = ls2;

  std::cout<< ls1.Empty() << std::endl;

  int e1, e2;
  ls2.Push(11);
  ls2.GetTop(e1);
  ls3.Pop(e2);

  std::cout << e1 << " " << e2 << " " << ls3.GetLength() << std::endl;
  ls2.Traverse(Print);

  return 0;
}
