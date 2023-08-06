#ifndef DATA_STRUCTURE_LINK_STACK_NODE_
#define DATA_STRUCTURE_LINK_STACK_NODE_

template <typename ElemType>
struct Node {
  ElemType data;
  Node* next;

  Node();
  Node(const ElemType& e, Node* p=nullptr);
};

template <typename ElemType>
Node<ElemType>::Node() {
  next = nullptr;
}

template <typename ElemType>
Node<ElemType>::Node(const ElemType& e, Node* p) {
  data = e;
  next = p;
}

#endif 
