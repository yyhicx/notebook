#ifndef DATA_STRUCTURE_MGRAPH_
#define DATA_STRUCTURE_MGRAPH_

#include "link_queue.h"

#define MAX_SIZE 20

template <typename ElemType>
class MGraph {
 public:
  MGraph();
  MGraph(ElemType* vertex, int n, int* arc[]);
  ~MGraph();
  MGraph(const MGraph& other);
  MGraph& operator=(const MGraph& other);
  bool Empty() const;
  void Clear();
  bool InsertElem(const ElemType& e, int* arc);
  int GetVertexSize() const;
  int GetEdgeSize() const;
  void ClearVisit();
  void BFSTraverse(void (*visit)(const ElemType& e), int v=0);
  void DFSTraverse(void (*visit)(const ElemType& e), int v=0);

 private:
  ElemType vertex_[MAX_SIZE];
  int arc_[MAX_SIZE][MAX_SIZE];
  int vertex_num_;
  int arc_num_;
  int visited_[MAX_SIZE];
};

template <typename ElemType>
MGraph<ElemType>::MGraph() {
  for (int i = 0; i < MAX_SIZE; i++)
    for (int j = 0; j < MAX_SIZE; j++)
      arc_[i][j] = 0;
  vertex_num_ = 0;
  arc_num_ = 0;
  for (int i = 0; i < MAX_SIZE; i++)
    visited_[i] = 0;
}

template <typename ElemType>
MGraph<ElemType>::MGraph(ElemType* vertex, int n, int* arc[]) {
  vertex_num_ = n;
  arc_num_ = 0;
  for (int i = 0; i < n; i++)
    vertex_[i] = vertex[i];
  for (int i = 0; i < n; i++)
    for (int j = 0; j < n; j++) {
      arc_[i][j] = arc[i][j];
      if (arc_[i][j] == 1)
        arc_num_++;
    }
  for (int i = 0; i < MAX_SIZE; i++)
    visited_[i] = 0;
}

template <typename ElemType>
MGraph<ElemType>::~MGraph() {
}

template <typename ElemType>
MGraph<ElemType>::MGraph(const MGraph& other) {
  vertex_num_ = other.vertex_num_;
  arc_num_ = other.arc_num_;
  for (int i = 0; i < vertex_num_; i++)
    vertex_[i] = other.vertex_[i];
  for (int i = 0; i < vertex_num_; i++)
    for (int j = 0; j < vertex_num_; j++)
    arc_[i][j] = other.arc_[i][j];
  for (int i = 0; i < MAX_SIZE; i++)
    visited_[i] = 0;
}

template <typename ElemType>
bool MGraph<ElemType>::Empty() const {
  if (vertex_num_ == 0)
    return true;
  else
    return false;
}

template <typename ElemType>
void MGraph<ElemType>::Clear() {
  vertex_num_ = 0;
  arc_num_ = 0;
  for (int i = 0; i < MAX_SIZE; i++)
    for (int j = 0; j < MAX_SIZE; j++)
      arc_[i][j] = 0;
  for (int i = 0; i < MAX_SIZE; i++)
    visited_[i] = 0;
}

template <typename ElemType>
bool MGraph<ElemType>::InsertElem(const ElemType& e, int* arc) {
  if (vertex_num_ == MAX_SIZE)
    return false;

  vertex_[vertex_num_] = e;
  for (int j = 0; j < vertex_num_; j++) {
    arc_[vertex_num_][j] = arc[j];
    if (arc_[vertex_num_][j] == 1)
      arc_num_++;
  }
  vertex_num_++;
}

template <typename ElemType>
int MGraph<ElemType>::GetVertexSize() const {
  return vertex_num_;
}

template <typename ElemType>
int MGraph<ElemType>::GetEdgeSize() const {
  return arc_num_;
}

template <typename ElemType>
void MGraph<ElemType>::ClearVisit() {
  for (int i = 0; i < vertex_num_; i++)
    visited_[i] = 0;
}

template <typename ElemType>
void MGraph<ElemType>::BFSTraverse(void (*visit)(const ElemType& e), int v) {
  LinkQueue<int> queue;
  (*visit)(vertex_[v]);
  visited_[v] = 1;
  queue.EnQueue(v);
  // queue.EnQueue(v+1);
  while (queue.GetLength() != 0) {
    queue.DeQueue(v);
    for (int i = 0; i < vertex_num_; i++) {
      if (arc_[v][i] == 1 && visited_[i] == 0) {
        (*visit)(vertex_[i]);
        visited_[i] = 1;
        queue.EnQueue(i);
      }
    }
  }
}

template <typename ElemType>
void MGraph<ElemType>::DFSTraverse(void (*visit)(const ElemType& e), int v) {
  (*visit)(vertex_[v]);
  visited_[v] = 1;
  for (int i = 0; i < vertex_num_; i++)
    if (arc_[v][i] == 1 && visited_[i] == 0)
      DFSTraverse(visit, i);
}

#endif
