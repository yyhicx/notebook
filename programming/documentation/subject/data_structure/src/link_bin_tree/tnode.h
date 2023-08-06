#ifndef DATA_STRUCTURE_LINK_BIN_TREE_NODE_
#define DATA_STRUCTURE_LINK_BIN_TREE_NODE_

template <typename ElemType>
struct TNode {
  ElemType data;
  TNode* lchild;
  TNode* rchild;

  TNode();
  TNode(const ElemType& e, TNode* l=nullptr, TNode* r=nullptr);
};

template <typename ElemType>
TNode<ElemType>::TNode() {
  lchild = rchild = nullptr;
}

template <typename ElemType>
TNode<ElemType>::TNode(const ElemType& e, TNode* l, TNode* r) {
  data = e;
  lchild = l;
  rchild = r;
}

#endif
