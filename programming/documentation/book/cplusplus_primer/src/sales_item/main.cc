#include "sales_item.h"
#include <iostream>
#include <string>

int main(int argc, char* argv[]) {
  SalesItem si1, si2;

  /**
   * test data:
   *   0-201-78345-X 3 20.00
   *   0-201-78345-X 2 25.00
   */
  std::cin >> si1 >> si2;

  if (CompareIsbn(si1, si2)) {
    SalesItem total = si1 + si2;
    std::cout << total.Isbn() << " " << total.GetUnitsSold()
        << " " << total.GetRevenue() << " ";
    if (total.GetUnitsSold() != 0)
      std::cout << total.AvgPrice() << std::endl;
    else
      std::cout << "(no sales)" << std::endl;
  } else {
    std::cerr << "Data must refer to the same ISBN"
        << std::endl;

    return -1;
  }

  return 0;
}
