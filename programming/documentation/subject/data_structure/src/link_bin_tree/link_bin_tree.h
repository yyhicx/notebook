#ifndef DATA_STRUCTURE_LINK_BIN_TREE_
#define DATA_STRUCTURE_LINK_BIN_TREE_

#include "falat_error.h"
#include "link_queue.h"
#include "tnode.h"

template <typename ElemType>
class LinkBinTree {
 public:
  LinkBinTree();
  ~LinkBinTree();
  LinkBinTree(const LinkBinTree& other);
  LinkBinTree& operator=(const LinkBinTree& other);
  bool Empty() const;
  void Clear();
  void InsertElem(const ElemType& e);
  int GetSize() const;
  int GetHeight() const ;
  void PreOrder(void (*visit)(const ElemType& e)) const;
  void InOrder(void (*visit)(const ElemType& e)) const;
  void PostOrder(void (*visit)(const ElemType& e)) const;
  void LevelOrder(void (*visit)(const ElemType& e)) const;

 private:
  int GetHeight(const TNode<ElemType>* p) const ;
  void PreOrder(void (*visit)(const ElemType& e), const TNode<ElemType>* p) const;
  void InOrder(void (*visit)(const ElemType& e), const TNode<ElemType>* p) const;
  void PostOrder(void (*visit)(const ElemType& e), const TNode<ElemType>* p) const;
  void LevelOrder(void (*visit)(const ElemType& e), const TNode<ElemType>* p) const;
  TNode<ElemType>* root_;
  int size_;
};

template <typename ElemType>
LinkBinTree<ElemType>::LinkBinTree() {
  root_ = nullptr;
  size_ = 0;
}

template <typename ElemType>
LinkBinTree<ElemType>::~LinkBinTree() {
  Clear();
  root_ = nullptr;
}

template <typename ElemType>
LinkBinTree<ElemType>::LinkBinTree(const LinkBinTree& other) {
  size_ = other.size_;
  
  if (other.root_ == nullptr)
    root_ = nullptr;
  else {
    root_ = new TNode<ElemType>(other.root_->data);
    if (root_ == nullptr)
      FalatError("Out of space");
  }

  LinkQueue<TNode<ElemType>*> queue1;
  LinkQueue<TNode<ElemType>*> queue2;
  queue1.EnQueue(other.root_);
  queue2.EnQueue(root_);
  while (queue1.GetLength() != 0) {
    TNode<ElemType>* current1;
    TNode<ElemType>* current2;
    queue1.GetTop(current1);
    queue2.GetTop(current2);
    if (current1->lchild != nullptr) {
      queue1.EnQueue(current1->lchild);
      current2->lchild = new TNode<ElemType>(current1->lchild->data);
      if (current2->lchild == nullptr)
        FalatError("Out of space");
      queue2.EnQueue(current2->lchild);
    }
    if (current1->rchild != nullptr) {
      queue1.EnQueue(current1->rchild);
      current2->rchild = new TNode<ElemType>(current1->rchild->data);
      if (current2->rchild == nullptr)
        FalatError("Out of space");
      queue2.EnQueue(current2->rchild);
    }
    queue1.DeQueue(current1);
    queue2.DeQueue(current2);
  }
}

template <typename ElemType>
LinkBinTree<ElemType>& LinkBinTree<ElemType>::operator=(const LinkBinTree<ElemType>& other) {
  Clear();
  size_ = other.size_;

  if (other.root_ == nullptr)
    root_ = nullptr;
  else {
    root_ = new TNode<ElemType>(other.root_->data);
    if (root_ == nullptr)
      FalatError("Out of space");
  }

  LinkQueue<TNode<ElemType>* > queue1;
  LinkQueue<TNode<ElemType>* > queue2;
  queue1.EnQueue(other.root_);
  queue2.EnQueue(root_);
  while (queue1.GetLength() != 0) {
    TNode<ElemType>* current1;
    TNode<ElemType>* current2;
    queue1.GetTop(current1);
    queue2.GetTop(current2);
    if (current1->lchild != nullptr) {
      queue1.EnQueue(current1->lchild);
      current2->lchild = new TNode<ElemType>(current1->lchild->data);
      if (current2->lchild == nullptr)
        FalatError("Out of space");
      queue2.EnQueue(current2->lchild);
    }
    if (current1->rchild != nullptr) {
      queue1.EnQueue(current1->rchild);
      current2->rchild = new TNode<ElemType>(current1->rchild->data);
      if (current2->rchild == nullptr)
        FalatError("Out of space");
      queue2.EnQueue(current2->rchild);
    }
    queue1.DeQueue(current1);
    queue2.DeQueue(current2);
  }

  return *this;
}

template <typename ElemType>
bool LinkBinTree<ElemType>::Empty() const {
  if (size_ == 0)
    return true;
  else
    return false;
}

template <typename ElemType>
void LinkBinTree<ElemType>::Clear() {
  size_ = 0;
  LinkQueue<TNode<ElemType>*> queue;

  if (root_ == nullptr) 
    return;
  else {
    if (root_->lchild != nullptr)
      queue.EnQueue(root_->lchild);
    if (root_->rchild != nullptr)
      queue.EnQueue(root_->rchild);
    delete root_;
    root_ = nullptr;
  }

  TNode<ElemType>* current;
  while (queue.GetLength() != 0) {
    queue.DeQueue(current);
    if (current->lchild != nullptr)
      queue.EnQueue(current->lchild);
    if (current->rchild != nullptr)
      queue.EnQueue(current->rchild);
    delete current;
  }
}

template <typename ElemType>
void LinkBinTree<ElemType>::InsertElem(const ElemType& e) {
  if (root_ == nullptr) {
    root_ = new TNode<ElemType>(e);
    if (root_ == nullptr)
      FalatError("Out of space");
    size_++;
    return;
  }

  TNode<ElemType>* current = root_;
  while (true) {
    if (e < current->data) {
      if (current->lchild == nullptr) {
        current->lchild = new TNode<ElemType>(e);
        if (current->lchild == nullptr)
          FalatError("Out of space");
        break;
      }
      else {
        current = current->lchild;
      }
    }
    else if (e >= current->data) {
      if (current->rchild == nullptr) {
        current->rchild = new TNode<ElemType>(e);
        if (current->rchild == nullptr)
          FalatError("Out of space");
        break;
      }
      else {
        current = current->rchild;
      }
    }
  }
  size_++;
}

template <typename ElemType>
int LinkBinTree<ElemType>::GetSize() const {
  return size_;
}

template <typename ElemType>
int LinkBinTree<ElemType>::GetHeight() const {
  return GetHeight(root_);
}

template <typename ElemType>
int LinkBinTree<ElemType>::GetHeight(const TNode<ElemType>* p) const {
  if (p == nullptr)
    return 0;
  int l = GetHeight(p->lchild);
  int r = GetHeight(p->rchild);
  return (l > r) ? ++l : ++r;
}

template <typename ElemType>
void LinkBinTree<ElemType>::PreOrder(void (*visit)(const ElemType& e)) const {
  PreOrder(visit, root_);
}

template <typename ElemType>
void LinkBinTree<ElemType>::PreOrder(void (*visit)(const ElemType& e), const TNode<ElemType>* p) const {
  if (p == nullptr)
    return;
  (*visit)(p->data);
  PreOrder(visit, p->lchild);
  PreOrder(visit, p->rchild);
}

template <typename ElemType>
void LinkBinTree<ElemType>::InOrder(void (*visit)(const ElemType& e)) const {
  InOrder(visit, root_);
}

template <typename ElemType>
void LinkBinTree<ElemType>::InOrder(void (*visit)(const ElemType& e), const TNode<ElemType>* p) const {
  if (p == nullptr)
    return;
  InOrder(visit, p->lchild);
  (*visit)(p->data);
  InOrder(visit, p->rchild);
}

template <typename ElemType>
void LinkBinTree<ElemType>::PostOrder(void (*visit)(const ElemType& e)) const {
  PostOrder(visit, root_);
}

template <typename ElemType>
void LinkBinTree<ElemType>::PostOrder(void (*visit)(const ElemType& e), const TNode<ElemType>* p) const {
  if (p == nullptr)
    return;
  PostOrder(visit, p->lchild);
  PostOrder(visit, p->rchild);
  (*visit)(p->data);
}

template <typename ElemType>
void LinkBinTree<ElemType>::LevelOrder(void (*visit)(const ElemType& e)) const {
  LevelOrder(visit, root_);
}

template <typename ElemType>
void LinkBinTree<ElemType>::LevelOrder(void (*visit)(const ElemType& e), const TNode<ElemType>* p) const {
  LinkQueue<TNode<ElemType>*> queue;

  if (p == nullptr)
    return;
  else {
    if (p->lchild != nullptr)
      queue.EnQueue(p->lchild);
    if (p->rchild != nullptr)
      queue.EnQueue(p->rchild);
    (*visit)(p->data);
  }

  TNode<ElemType>* current;
  while (queue.GetLength() != 0) {
    queue.DeQueue(current);
    if (current->lchild != nullptr)
      queue.EnQueue(current->lchild);
    if (current->rchild != nullptr)
      queue.EnQueue(current->rchild);
    (*visit)(current->data);
  }
}

#endif
