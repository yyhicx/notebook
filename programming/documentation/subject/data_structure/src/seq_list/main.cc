#include "seq_list.h"
#include <iostream>

template <typename T>
void Print(const T& t) {
  std::cout << t << std::endl;
}

int main(int argc, char* argv[]) {
  int data[10];
  for (int i = 0; i < 10; i++)
    data[i] = i;

  SeqList<int> sl1;
  SeqList<int> sl2(data, 10);
  SeqList<int> sl3 = sl2;

  sl1.InsertElem(10);
  sl1.InsertElem(1, 9);

  std::cout << sl1.Empty() << std::endl;
  sl1.Traverse(Print);

  int e1, e2;
  sl2.DeleteElem(3, e1);
  sl2.SetElem(1, e1);
  sl2.GetElem(7, e2);
  int index = sl2.LocateElem(4);

  std::cout << e1 << " " << e2 << " " << index << std::endl;
  sl2.Traverse(Print);

  int len = sl3.GetLength();
  std::cout << len << std::endl;

  return 0;
}
