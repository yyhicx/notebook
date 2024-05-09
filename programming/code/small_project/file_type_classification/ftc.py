# Copyright 2022 by chnxish. All Rights Reserved.
#
# Distributed under MIT license.
# See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
"""Define File Type Classification Class."""
import os


class Ftc:
    """File Type Classification.

    Attributes:
      file_type, like XXXX, .XXXX, XX.XX
      file_name
      file_relative_path
    """

    FILE_TYPES = [
        ".XXXX",  # The file name string starts with a period, like .gitconfig.
        "XX.XX",  # Regular file name, like hello.cc.
        "XXXX",  # The file name string does not contain a period, like License.
    ]

    def __init__(self, file_name, realtive_path_name):
        self.file_name = file_name

        index_of_period = self.file_name.find(".")
        if index_of_period == 0:
            self.file_type = self.FILE_TYPES[0]
        elif index_of_period > 0:
            self.file_type = self.FILE_TYPES[1]
        else:
            self.file_type = self.FILE_TYPES[2]

        self.file_realtive_path = os.path.join(realtive_path_name, self.file_name)

    def __repr__(self):
        return f"{self.__class__.__name__} {self.file_realtive_path}"

    def __str__(self):
        return str(self.file_realtive_path)

    def member_function_names(self):
        return ["file_type", "file_name", "file_realtive_path"]

    def to_list(self):
        return [self.file_type, self.file_name, self.file_realtive_path]


if __name__ == "__main__":
    ftc = Ftc("hello.cc", "cpp")
    print(ftc)
    print(ftc.member_function_names())
    print(ftc.to_list())
