# Copyright 2022 by chnxish. All Rights Reserved.
#
# Distributed under MIT license.
# See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
"""Sort css property."""
import argparse
import json
import queue
import sys


def find_first_letter(s):
  """Find the index of first letter in a String."""
  try:
    return s.find(next(filter(str.isalpha, s)))
  except StopIteration:
    return -1


def process_declaration_block(declaration_block, properties):
  first_letter_index = find_first_letter(declaration_block)
  if first_letter_index == -1:
    return declaration_block
  indent_length = first_letter_index - 1
  properties_and_values = {}

  while first_letter_index != -1:
    colon_index = declaration_block.find(':')
    semicolon_index = declaration_block.find(';')
    properties_and_values.update({
        declaration_block[first_letter_index:colon_index]:
        declaration_block[first_letter_index:semicolon_index+1]})
    declaration_block = declaration_block[semicolon_index+1:]
    first_letter_index = find_first_letter(declaration_block)

  sorted_properties_and_values = sorted(
      properties_and_values.items(),
      key=lambda x:properties.index(x[0]))

  new_declaration_block = ''
  line_break_and_spaces = '\n' + ' ' * indent_length
  for i in sorted_properties_and_values:
    new_declaration_block += line_break_and_spaces + i[1]
  new_declaration_block += '\n' + ' ' * (indent_length - 2)

  return new_declaration_block


def process_data(input_data, properties):
  new_data = queue.Queue()

  left_brace_index = 0
  while left_brace_index != -1:
    new_data.put(input_data[0:left_brace_index])
    input_data = input_data[left_brace_index:]

    right_brace_index = input_data.find('}')
    if right_brace_index == -1:
      sys.exit('Failed to capture right brace')
    left_brace_index = input_data.rfind('{', 0, right_brace_index)
    if left_brace_index == -1:
      sys.exit('Failed to capture left brace')

    new_data.put(input_data[0:left_brace_index+1])
    declaration_block = input_data[left_brace_index+1:right_brace_index]
    new_declaration_block = process_declaration_block(
        declaration_block, properties)
    new_data.put(new_declaration_block)
    input_data = input_data[right_brace_index:]
    left_brace_index = input_data.find('{')
  new_data.put(input_data)

  result = ''
  while not new_data.empty():
    d = new_data.get()
    result += d

  return result


def main(filename):
  with open('properties.json', 'r', encoding='utf-8') as p:
    properties = json.load(p)

  with open(filename, 'r', encoding='utf-8') as f:
    input_data = f.read()

  output_data = process_data(input_data, properties)

  ext_length = len('.css')
  with open(filename[:-ext_length] + '-processed' + filename[-ext_length:],
            'w', encoding='utf-8') as f:
    f.write(output_data)


if __name__ == '__main__':
  arg_parser = argparse.ArgumentParser()
  arg_parser.add_argument('input', type=str, help='Input file')
  args = arg_parser.parse_args()
  main(args.input)
