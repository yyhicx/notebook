# Copyright 2022 by chnxish. All Rights Reserved.
#
# Distributed under MIT license.
# See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
"""Printing lists as table data."""
import prettytable

import ftc


def tabular(mylist):
    """Printing lists as tabular data."""
    first_element = mylist[0]

    if hasattr(first_element, "member_function_names") and hasattr(
        first_element, "to_list"
    ):
        mfn = first_element.member_function_names()
        t = prettytable.PrettyTable(mfn)
        for x in mylist:
            t.add_row(x.to_list())
        print(t)
    else:
        print("The list data does not support printing as a table")


if __name__ == "__main__":
    f1 = ftc.Ftc("hello.cc", "")
    f2 = ftc.Ftc(".gitconfig", "")
    f3 = ftc.Ftc("LICENSE", "")

    l = []
    l.append(f1)
    l.append(f2)
    l.append(f3)

    tabular(l)
