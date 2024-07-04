# Copyright 2022 by chnxish. All Rights Reserved.
#
# Distributed under MIT license.
# See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
"""Download image."""
import os
import time

import requests


def download_image(image_url, folder_name='./image'):
  try:
    response = requests.get(image_url, timeout=1.5)
  except requests.exceptions.RequestException as e:
    print(e)

  tm = time.localtime(time.time())
  file_name = 'url' + str(tm.tm_hour) + str(tm.tm_min) + str(
      tm.tm_sec) + '.png'
  file_path = os.path.join(folder_name, file_name)
  with open(file_path, 'wb') as f:
    f.write(response.content)

  return file_path


if __name__ == '__main__':
  url = 'https://avatars.githubusercontent.com/u/27667942?v=4'
  download_image(url)
