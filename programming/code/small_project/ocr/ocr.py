# Copyright 2022 by chnxish. All Rights Reserved.
#
# Distributed under MIT license.
# See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
"""Call Baidu OCR API."""
import json

from aip import AipOcr


class Ocr:
  """Baidu OCR API."""

  def __init__(self, image_path):
    self.path = image_path

    with open('api_key.json', 'r', encoding='utf-8') as f:
      data = json.load(f)
      app_id = data['APP_ID']
      api_key = data['API_KEY']
      secrey_key = data['SECREY_KEY']
    self.client = AipOcr(app_id, api_key, secrey_key)

  def __get_image_content(self, file_path):
    with open(file_path, 'rb') as fp:
      return fp.read()

  def run(self):
    self.options = {}
    self.options['language_type']    = 'CHN_ENG'
    self.options['detect_direction'] = 'true'
    self.options['detect_language']  = 'true'
    self.options['probability']      = 'true'

    image_content = self.__get_image_content(self.path)
    try:
      response = self.client.basicAccurate(image_content, self.options)
      string = response['words_result']
      self.words_result = ''
      length = len(string)
      for s in string:
        self.words_result = self.words_result + s['words']
        length -= 1
        if length > 0:
          self.words_result += '\n'
    except KeyError:
      self.words_result = 'failure'

  def result(self):
    return self.words_result
