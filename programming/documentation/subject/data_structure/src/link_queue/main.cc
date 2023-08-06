#include "link_queue.h"
#include <iostream>

template <typename T>
void Print(const T& t) {
  std::cout << t << std::endl;
}

int main(int argc, char* argv[]) {
  int data[10];
  for (int i = 0; i < 10; i++)
    data[i] = i;
  
  LinkQueue<int> lq1;
  LinkQueue<int> lq2(data, 10);
  LinkQueue<int> lq3 = lq2;

  std::cout << lq1.Empty() << std::endl;

  int e1, e2;
  lq2.EnQueue(11);
  lq2.GetTop(e1);
  lq3.DeQueue(e2);

  std::cout << e1 << " " << e2 << " " << lq3.GetLength() << std::endl;
  lq2.Traverse(Print);
  lq3.Traverse(Print);

  return 0;
}
