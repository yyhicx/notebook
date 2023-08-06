#include "mgraph.h"
#include <iostream>

template <typename T>
void Print(const T& t) {
  std::cout << t << std::endl;
}

int main(int argc, char* argv[]) {
  int vertex[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
  int arc_matrix[10][10] = {
    {0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
    {1, 0, 1, 1, 0, 0, 0, 0, 0, 0},
    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 1, 0, 0, 1, 1, 1, 0, 0, 0},
    {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
    {0, 0, 0, 1, 1, 0, 1, 0, 0, 0},
    {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
    {1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
    {0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
    {0, 0, 0, 0, 0, 0, 0, 1, 1, 0}
  };
  int n = 10;
  int* arc[10];
  for (int i = 0; i < n; i++)
    arc[i] = &arc_matrix[i][0];

  MGraph<int> mg(vertex, n, arc);
  std::cout << mg.GetVertexSize() << " " << mg.GetEdgeSize() << std::endl;

  mg.BFSTraverse(Print);
  mg.ClearVisit();
  mg.DFSTraverse(Print);

    return 0;
}
