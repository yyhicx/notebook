# Copyright 2023 by chnxish. All Rights Reserved.
#
# Distributed under MIT license.
# See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
"""Naming Convention."""
import re
import sys

if __name__ == "__main__":
    if len(sys.argv) < 3:
        print("Usage: python3 main.py option text")
        sys.exit(1)

    text = " ".join(sys.argv[2:])
    substrings = re.split(r"[^a-zA-Z0-9]", text)
    # filter out empty strings
    substrings = [substring for substring in substrings if substring]

    option = sys.argv[1]
    if option == "-c":
        substrings[0] = substrings[0].lower()
        for index in range(len(substrings) - 1):
            substrings[index + 1] = substrings[index + 1].title()
    elif option == "-k":
        for index in range(len(substrings) - 1):
            substrings[index] = substrings[index].lower() + "-"
        substrings[-1] = substrings[-1].lower()
    elif option == "-m":
        for index in range(len(substrings) - 1):
            substrings[index] = substrings[index].upper() + "_"
        substrings[-1] = substrings[-1].upper()
    elif option == "-p":
        for index in range(len(substrings)):
            substrings[index] = substrings[index].title()
    elif option == "-s":
        for index in range(len(substrings) - 1):
            substrings[index] = substrings[index].lower() + "_"
        substrings[-1] = substrings[-1].lower()
    else:
        print(option + ": invalid option")
        sys.exit(1)

    new_text = "".join(substrings)
    print(new_text)
