#include "sales_item.h"

SalesItem& SalesItem::operator+=(const SalesItem& rhs) {
  units_sold_ += rhs.units_sold_;
  revenue_ += rhs.revenue_;
  return *this;
}

SalesItem operator+(const SalesItem& lhs, const SalesItem& rhs) {
  SalesItem ret(lhs);
  ret += rhs;
  return ret;
}

double SalesItem::AvgPrice() const {
  if (units_sold_)
    return revenue_ / units_sold_;
  else
    return 0;
}

std::istream& operator>>(std::istream& in, SalesItem& rhs) {
  double price;
  in >> rhs.book_no_ >> rhs.units_sold_ >> price;
  if (in)
    rhs.revenue_ = rhs.units_sold_ * price;
  else
    rhs = SalesItem();

  return in;
}

std::ostream & operator<<(std::ostream& out, const SalesItem& rhs) {
  out << rhs.Isbn() << " " << rhs.units_sold_ << " "
      << rhs.revenue_ << " " << rhs.AvgPrice();

  return out;
}
