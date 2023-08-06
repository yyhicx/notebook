# Copyright 2022 by chnxish. All Rights Reserved.
#
# Distributed under MIT license.
# See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
"""Realize screenshot with snipaste."""
import os
import time

import keyboard
from PIL import ImageGrab


def screenshot(folder_name = './image'):
  if not keyboard.wait(hotkey='f1'):
    if not keyboard.wait(hotkey='ctrl+c'):
      time.sleep(0.01)
      image = ImageGrab.grabclipboard()
      tm = time.localtime(time.time())
      file_name = (  # line too long
          'ss' + str(tm.tm_hour) + str(tm.tm_min) + str(tm.tm_sec) + '.png')
      file_path = os.path.join(folder_name, file_name)
      image.save(file_path)

  return file_path


if __name__ == '__main__':
  screenshot()
