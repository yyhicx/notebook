#ifndef DATA_STRUCTURE_LINK_STACK_
#define DATA_STRUCTURE_LINK_STACK_

#include "falat_error.h"
#include "node.h"

template <typename ElemType>
class LinkStack {
 public:
  LinkStack();
  LinkStack(const ElemType* elems, int n);
  ~LinkStack();
  LinkStack(const LinkStack& other);
  LinkStack& operator=(const LinkStack& other);
  bool Empty() const;
  void Clear();
  void Push(const ElemType& e);
  void Pop(ElemType& e);
  void GetTop(ElemType& e);
  int GetLength() const;
  void Traverse(void (*visit)(const ElemType& e)) const;

 private:
  Node<ElemType>* head_;
  int length_;
};

template <typename ElemType>
LinkStack<ElemType>::LinkStack() {
  head_ = new Node<ElemType>;
  if (head_ == nullptr)
    FalatError("Out of space");
  length_ = 0;
}

template <typename ElemType>
LinkStack<ElemType>::LinkStack(const ElemType* elems, int n) {
  head_ = new Node<ElemType>;
  if (head_ == nullptr)
    FalatError("Out of space");
  for (int i = 0; i < n; i++) {
    Node<ElemType>* p = new Node<ElemType>(elems[i]);
    if (p == nullptr)
      FalatError("Out of space");
    p->next = head_->next;
    head_->next = p;
  }
  length_ = n;
}

template <typename ElemType>
LinkStack<ElemType>::~LinkStack() {
  Clear();
  delete head_;
}

template <typename ElemType>
LinkStack<ElemType>::LinkStack(const LinkStack& other) {
  length_ = other.length_;
  head_ = new Node<ElemType>;
  if (head_ == nullptr)
    FalatError("Out of space");
  Node<ElemType>* q = other.head_->next;
  Node<ElemType>* p = head_;
  while (q) {
    p->next = new Node<ElemType>(q->data);
    if (p->next == nullptr)
      FalatError("Out of space");
    q = q->next;
    p = p->next;
  }
}

template <typename ElemType>
LinkStack<ElemType>& LinkStack<ElemType>::operator=(const LinkStack<ElemType>& other) {
  Clear();
  length_ = other.length_;
  Node<ElemType>* q = other.head_->next;
  Node<ElemType>* p = head_;
  while (q) {
    p->next = new Node<ElemType>(q->data);
    if (p->next == nullptr)
      FalatError("Out of space");
    q = q->next;
    p = p->next;
  }
  return *this;
}

template <typename ElemType>
bool LinkStack<ElemType>::Empty() const {
  if (length_ == 0)
    return true;
  else
    return false;
}

template <typename ElemType>
void LinkStack<ElemType>::Clear() {
  length_ = 0;
  Node<ElemType>* p = head_->next;
  while (p) {
    head_->next = p->next;
    delete p;
    p = head_->next;
  }
}

template <typename ElemType>
void LinkStack<ElemType>::Push(const ElemType& e) {
  Node<ElemType>* p = new Node<ElemType>(e);
  if (p == nullptr)
    FalatError("Out of space");
  p->next = head_->next;
  head_->next = p;
  length_++;
}

template <typename ElemType>
void LinkStack<ElemType>::Pop(ElemType& e) {
  if (Empty() == true)
    FalatError("Empty Stack");

  Node<ElemType>* p = head_->next;
  head_->next = p->next;
  e = p->data;
  delete p;
  length_--;
}

template <typename ElemType>
void LinkStack<ElemType>::GetTop(ElemType& e) {
  if (Empty() == true)
    FalatError("Empty Stack");

  Node<ElemType>* p = head_->next;
  e = p->data;
}

template <typename ElemType>
int LinkStack<ElemType>::GetLength() const {
  return length_;
}

template <typename ElemType>
void LinkStack<ElemType>::Traverse(void (*visit)(const ElemType& e)) const {
  Node<ElemType>* p = head_->next;
  while (p) {
    (*visit)(p->data);
    p = p->next;
  }
}

#endif
