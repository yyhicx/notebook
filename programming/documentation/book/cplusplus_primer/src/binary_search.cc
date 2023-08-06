#include <iostream>
#include <string>
#include <vector>

template <typename Container>
typename Container::difference_type BinarySearch(const Container& data, const typename Container::value_type& sought) {
  typename Container::const_iterator beg = data.cbegin();
  typename Container::const_iterator end = data.cend();
  typename Container::const_iterator mid = data.cbegin() + (end - beg) / 2;

  while (mid != end && *mid != sought) {
    if (sought < *mid) {
      end = mid;
    } else {
      beg = mid + 1;
    }
    mid = beg + (end - beg) / 2;
  }

  return *mid == sought ? mid - data.cbegin() : -1;
}

int main(int argc, char* argv[]) {
  std::string str = "abcde";
  std::vector<int> ivec = {1, 2, 3, 4, 5};

  std::cout << BinarySearch<std::string>(str, 'd') << std::endl;
  std::cout << BinarySearch<std::vector<int>>(ivec, 1) << std::endl;

  return 0;
}
