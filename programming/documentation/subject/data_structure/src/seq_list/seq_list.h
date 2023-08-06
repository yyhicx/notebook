#ifndef DATA_STRUCTURE_SEQ_LIST_
#define DATA_STRUCTURE_SEQ_LIST_

#include "falat_error.h"

#define DEFAULT_SIZE 100

template <typename ElemType>
class SeqList {
 public:
  SeqList(int size=DEFAULT_SIZE);
  SeqList(ElemType* data, int n, int size=DEFAULT_SIZE);  // `size` must be greater than `n`
  ~SeqList();
  SeqList(const SeqList& other);
  SeqList& operator=(const SeqList& other);
  bool Empty() const;
  void Clear();
  bool InsertElem(const ElemType& e);
  bool InsertElem(int i, const ElemType& e);
  bool DeleteElem(int i, ElemType& e);
  bool SetElem(int i, const ElemType& e);
  bool GetElem(int i, ElemType& e) const;
  int LocateElem(const ElemType& e) const;
  int GetLength() const;
  void Traverse(void (*visit)(const ElemType& e)) const;

 private:
  ElemType* elems_;
  int length_;
  int max_length_;
};

template <typename ElemType>
SeqList<ElemType>::SeqList(int size) {
  elems_ = new ElemType[size];
  if (elems_ == nullptr)
    FalatError("Out of space");
  length_ = 0;
  max_length_ = size;
}

template <typename ElemType>
SeqList<ElemType>::SeqList(ElemType* data, int n, int size) {
  // `size` must be greater than `n`
  elems_ = new ElemType[size];
  if (elems_ == nullptr)
    FalatError("Out of space");
  length_ = n;
  max_length_ = size;
  for (int i = 0; i < n; i++)
    elems_[i] = data[i];
}

template <typename ElemType>
SeqList<ElemType>::~SeqList() {
  if (elems_ != nullptr)
    delete[] elems_;
}

template <typename ElemType>
SeqList<ElemType>::SeqList(const SeqList& other) {
  elems_ = nullptr;
  *this = other;
}

template <typename ElemType>
SeqList<ElemType>& SeqList<ElemType>::operator=(const SeqList<ElemType>& other) {
  length_ = other.length_;
  max_length_ = other.max_length_;
  if (elems_ != nullptr)
    delete[] elems_;
  elems_ = new ElemType[max_length_];
  if (elems_ == nullptr)
    FalatError("Out of space");
  for (int i = 0; i < length_; i++)
    elems_[i] = other.elems_[i];
  return *this;
}

template <typename ElemType>
bool SeqList<ElemType>::Empty() const {
  if (length_ == 0)
    return true;
  else
    return false;
}

template <typename ElemType>
void SeqList<ElemType>::Clear() {
  length_ = 0;
}

template <typename ElemType>
bool SeqList<ElemType>::InsertElem(const ElemType& e) {
  if (length_ == max_length_)
    return false;

  elems_[length_] = e;
  length_++;
  return true;
}

template <typename ElemType>
bool SeqList<ElemType>::InsertElem(int i, const ElemType& e) {
  if (length_ == max_length_)
    return false;
  else if (i < 1 || i > length_ + 1)
    return false;
  else {
    for (int j = length_; j >= i; j--)
      elems_[j] = elems_[j - 1];
    elems_[i - 1] = e;
    length_++;
    return true;
  }
}

template <typename ElemType>
bool SeqList<ElemType>::DeleteElem(int i, ElemType& e) {
  if (i < 1 || i > length_)
    return false;

  e = elems_[i - 1];
  for (int j = i; j < length_; j++)
    elems_[j - 1] = elems_[j];
  length_--;
  return true;
}

template <typename ElemType>
bool SeqList<ElemType>::SetElem(int i, const ElemType& e) {
  if (i < 1 || i > length_)
    return false;

  elems_[i] = e;
  return true;
}

template <typename ElemType>
bool SeqList<ElemType>::GetElem(int i, ElemType& e) const {
  if (i < 1 || i > length_)
    return false;

  e = elems_[i - 1];
  return true;
}

template <typename ElemType>
int SeqList<ElemType>::LocateElem(const ElemType& e) const {
  for (int i = 0; i < length_; i++)
    if (elems_[i] == e)
      return ++i;
  return 0;
}

template <typename ElemType>
int SeqList<ElemType>::GetLength() const {
  return length_;
}

template <typename ElemType>
void SeqList<ElemType>::Traverse(void (*visit)(const ElemType& e)) const {
  for (int i = 0; i < length_; i++)
    (*visit)(elems_[i]);
}

#endif
