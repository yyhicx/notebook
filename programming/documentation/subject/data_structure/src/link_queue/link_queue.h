#ifndef DATA_STRUCTURE_LINK_QUEUE_
#define DATA_STRUCTURE_LINK_QUEUE_

#include "falat_error.h"
#include "node.h"

template <typename ElemType>
class LinkQueue {
 public:
  LinkQueue();
  LinkQueue(const ElemType* elems, int n);
  ~LinkQueue();
  LinkQueue(const LinkQueue& other);
  LinkQueue& operator=(const LinkQueue& other);
  bool Empty() const;
  void Clear();
  void EnQueue(const ElemType& e);
  void DeQueue(ElemType& e);
  void GetTop(ElemType& e);
  int GetLength() const;
  void Traverse(void (*visit)(const ElemType& e)) const;

 private:
  Node<ElemType>* front_;
  Node<ElemType>* rear_;
  int length_;
};

template <typename ElemType>
LinkQueue<ElemType>::LinkQueue() {
  front_ = rear_ = new Node<ElemType>;
  if (front_ == nullptr)
    FalatError("Out of space");
  length_ = 0;
}

template <typename ElemType>
LinkQueue<ElemType>::LinkQueue(const ElemType* elems, int n) {
  front_ = rear_ = new Node<ElemType>;
  if (front_ == nullptr)
    FalatError("Out of space");
  for (int i = 0; i < n; i++) {
    rear_->next = new Node<ElemType>(elems[i]);
    if (rear_->next == nullptr)
      FalatError("Out of space");
    rear_ = rear_->next;
  }
  length_ = n;
}

template <typename ElemType>
LinkQueue<ElemType>::~LinkQueue() {
  Clear();
  delete front_;
}

template <typename ElemType>
LinkQueue<ElemType>::LinkQueue(const LinkQueue& other) {
  length_ = other.length_;
  front_ = rear_ = new Node<ElemType>;
  if (front_ == nullptr)
    FalatError("Out of space");
  Node<ElemType>* q = other.front_->next;
  while (q) {
    rear_->next = new Node<ElemType>(q->data);
    if (rear_->next == nullptr)
      FalatError("Out of space");
    q = q->next;
    rear_ = rear_->next;
  }
}

template <typename ElemType>
LinkQueue<ElemType>& LinkQueue<ElemType>::operator=(const LinkQueue<ElemType>& other) {
  Clear();
  length_ = other.length_;
  Node<ElemType>* q = other.front_->next;
  while (q) {
    rear_->next = new Node<ElemType>(q->data);
    if (rear_->next == nullptr)
      FalatError("Out of space");
    q = q->next;
    rear_ = rear_->next;
  }
  return *this;
}

template <typename ElemType>
bool LinkQueue<ElemType>::Empty() const {
  if (length_ == 0)
    return true;
  else
    return false;
}

template <typename ElemType>
void LinkQueue<ElemType>::Clear() {
  length_ = 0;
  rear_ = front_;
  Node<ElemType>* p = front_->next;
  while (p) {
    front_->next = p->next;
    delete p;
    p = front_->next;
  }
}

template <typename ElemType>
void LinkQueue<ElemType>::EnQueue(const ElemType& e) {
  Node<ElemType>* p = new Node<ElemType>(e);
  if (p == nullptr)
    FalatError("Out of space");
  rear_->next = p;
  rear_ = p;
  length_++;
}

template <typename ElemType>
void LinkQueue<ElemType>::DeQueue(ElemType& e) {
  if (Empty() == true)
    FalatError("Empty Queue");

  if (length_ == 1) rear_ = front_;
  Node<ElemType>* p = front_->next;
  front_->next = p->next;
  e = p->data;
  delete p;
  length_--;
}

template <typename ElemType>
void LinkQueue<ElemType>::GetTop(ElemType& e) {
  if (Empty() == true)
    FalatError("Empty Queue");

  Node<ElemType>* p = front_->next;
  e = p->data;
}

template <typename ElemType>
int LinkQueue<ElemType>::GetLength() const {
  return length_;
}

template <typename ElemType>
void LinkQueue<ElemType>::Traverse(void (*visit)(const ElemType& e)) const {
  Node<ElemType>* p = front_->next;
  while (p) {
    (*visit)(p->data);
    p = p->next;
  }
}

#endif
