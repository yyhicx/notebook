# Copyright 2022 by chnxish. All Rights Reserved.
#
# Distributed under MIT license.
# See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
"""Copy directory."""
import json
import os
import platform
import shutil
import sys

if __name__ == '__main__':
  if len(sys.argv) < 3:
    print(
        'Usage: python3 main.py source_folder_name destination_folder_name [--ignore=<json_file_name>]'
    )  # pylint: disable=line-too-long
    sys.exit(1)

  if platform.system() == 'windows':
    source_folder_name = (  # line to long
        sys.argv[1] if sys.argv[1].endswith('\\') else sys.argv[1] + '\\')
    destination_folder_name = (  # line to long
        sys.argv[2] if sys.argv[2].endswith('\\') else sys.argv[2] + '\\')
  else:
    source_folder_name = (  # line to long
        sys.argv[1] if sys.argv[1].endswith('/') else sys.argv[1] + '/')
    destination_folder_name = (  # line to long
        sys.argv[2] if sys.argv[2].endswith('/') else sys.argv[2] + '/')

  if len(sys.argv) > 3 and sys.argv[3].startswith('--ignore='):
    json_file_name = sys.argv[3][len('--ignore='):]
    with open(json_file_name, 'r', encoding='utf-8') as f:
      json_object = json.load(f)
    ignore_dirs = json_object.get('directories')
    ignore_files = json_object.get('files')
  else:
    ignore_dirs = []
    ignore_files = []

  for root, dirs, files in os.walk(source_folder_name):
    new_root = (
        root if not root.startswith(source_folder_name)  # line to long
        else root[len(source_folder_name):])
    tmp_dirs = []
    for name in dirs:
      if name in ignore_dirs:
        tmp_dirs.append(name)
      else:
        d = os.path.join(destination_folder_name, new_root, name)
        print('mkdir: ' + d)
        os.mkdir(d)
    for name in tmp_dirs:
      dirs.remove(name)
    for name in files:
      if name not in ignore_files:
        sf = os.path.join(root, name)
        df = os.path.join(destination_folder_name, new_root, name)
        print('copy:' + sf + ' -> ' + df)
        shutil.copyfile(sf, df)
