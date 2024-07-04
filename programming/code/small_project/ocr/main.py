# Copyright 2022 by chnxish. All Rights Reserved.
#
# Distributed under MIT license.
# See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
"""Implement ocr."""
import sys

from ocr import Ocr

import download
import screenshot

if __name__ == '__main__':
  if len(sys.argv) < 2:
    print('Usage: python3 main.py option file_path')
    sys.exit(1)

  option = sys.argv[1]
  if option == '-l':
    if len(sys.argv) < 3:
      print('Usage: python3 main.py -l path_of_the_image')
      sys.exit(1)
    file_path = sys.argv[2]
  elif option == '-u':
    if len(sys.argv) < 3:
      print('Usage: python3 main.py -u url_of_the_image')
      sys.exit(1)
    image_url = sys.argv[2]
    file_path = download.download_image(image_url)
  elif option == '-s':
    file_path = screenshot.screenshot()
  else:
    print(option + ': invalid option')
    sys.exit(1)

  o = Ocr(file_path)
  o.run()
  result = o.result()

  print(result)
