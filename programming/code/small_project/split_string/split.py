# Copyright 2022 by chnxish. All Rights Reserved.
#
# Distributed under MIT license.
# See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
"""Split string."""
import itertools
import sys

import prettytable


def main():
    print("Please enter string: ", end="")
    string = input()
    print("Please enter number of delimiter(s): ", end="")
    number = int(input())
    print("Please enter delimiter(s): ", end="")
    delimiters = list(input().split())
    print("Please enter table header(s): ", end="")
    table_headers = list(input().split())
    print("")

    if string == "" or len(delimiters) != number or len(table_headers) != number:
        print("Input error")
        sys.exit(1)

    old_splited_string = []
    old_splited_string.append(string)
    for d in reversed(delimiters):
        new_splited_string = []
        for s in old_splited_string:
            new_splited_string.extend([x for x in s.split(d) if x])
        old_splited_string = new_splited_string
    splited_string = old_splited_string
    table = prettytable.PrettyTable(table_headers)
    for i in itertools.zip_longest(*([iter(splited_string)] * number), fillvalue=None):
        table.add_row(i)
    print(table)


if __name__ == "__main__":
    main()
