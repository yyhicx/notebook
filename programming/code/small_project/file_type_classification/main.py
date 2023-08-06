# Copyright 2022 by chnxish. All Rights Reserved.
#
# Distributed under MIT license.
# See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
"""File Type Classification."""
import os
import re
import sys

import ftc
import tabular


def recursively_traverse_directories(dir_name):
  """Recursively traverse all files under the directory.

  Returns:
    A list containing file names and path names.
    For example:

    [[filename1, pathname1], [filename2, pathname2] ...]
  """
  result = []
  for root, _, files in os.walk(dir_name):
    for f in files:
      result.append([f, root])

  return result


if __name__ == '__main__':
  if len(sys.argv) < 2:
    print('Usage: python3 main.py directory_name')
    sys.exit(1)

  directory_name = sys.argv[1]
  info = recursively_traverse_directories(directory_name)

  mylist = []
  for i in info:
    # delete the part of './' or '../' in the pathname
    r = re.search(r'\w', i[1])
    if r is None:
      r = ''
    else:
      r = i[1][r.span()[0]:]

    mylist.append(ftc.Ftc(i[0], r))


  # (Paco) https://www.zhihu.com/question/30389643/answer/412385014
  # ss = [1, 2, 333, 8, 234, 5923, 7, 49]
  # sss = sorted(
  #     ss,
  #     key=functools.cmp_to_key(
  #         lambda a,b: int(str(a)+str(b))-int(str(b)+str(a))),
  #     reverse=True)
  mylist.sort(
      key = lambda ftc: ftc.file_type +
          ftc.file_name[ftc.file_name.rfind('.'):] +
          ftc.file_name.lower())

  tabular.tabular(mylist)
