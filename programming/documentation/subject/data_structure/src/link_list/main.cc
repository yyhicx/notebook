#include "link_list.h"
#include <iostream>

template <typename T>
void Print(const T& t) {
  std::cout << t << std::endl;
}

int main(int argc, char* argv[]) {
  int data[10];
  for (int i = 0; i < 10; i++)
    data[i] = i;

  LinkList<int> ll1;
  LinkList<int> ll2(data, 10);
  LinkList<int> ll3 = ll2;

  ll1.InsertElem(10);
  ll1.InsertElem(1, 9);

  ll1.Traverse(Print);

  int e1, e2;
  ll2.DeleteElem(3, e1);
  ll2.SetElem(1, e1);
  ll2.GetElem(7, e2);
  int index = ll2.LocateElem(4);

  std::cout << e1 << " " << e2 << " " << index << std::endl;
  ll2.Traverse(Print);

  int len = ll3.GetLength();
  std::cout << len << std::endl;

  return 0;
}
