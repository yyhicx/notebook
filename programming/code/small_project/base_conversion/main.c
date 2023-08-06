/**
 * Copyright 2022 by chnxish. All Rights Reserved.
 *
 * Distributed under MIT license.
 * See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
 */
#include <stdarg.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "number.h"

#define OPTION_ARRAY_SIZE 5
#define NUMBER_ARRAY_SIZE 70

static void Report(const char *fmt, va_list params) {
  vfprintf(stderr, fmt, params);
}

static void Die(const char *fmt, ...) {
  va_list params;

  va_start(params, fmt);
  Report(fmt, params);
  va_end(params);
  exit(1);
}

int main(int argc, char *argv[]) {
  Number number;
  int base;
  char option[OPTION_ARRAY_SIZE];
  char number_array[NUMBER_ARRAY_SIZE];

  if (argc < 3) {
    Die("Too few arguments\n");
  } else if (argc > 3) {
    Die("Too many arguments\n");
  } else {
    strncpy(option, argv[1], OPTION_ARRAY_SIZE - 1);
    strncpy(number_array, argv[2], NUMBER_ARRAY_SIZE - 1);
    if (option[0] == '-' && option[2] == '\0') {
      switch (option[1]) {
        case 'b':
          base = 2;
          break;
        case 'o':
          base = 8;
          break;
        case 'd':
          base = 10;
          break;
        case 'h':
          base = 16;
          break;
        default:
          Die("Unknown argument '%s'\n", option);
      }
    } else {
      Die("Unknown argument '%s'\n", option);
    }
  }

  if (!Check(number_array, base))
    Die("Invalid number '%s'\n", number_array);

  Process(number_array, base, &number);

  printf("bin: %s\noct: %s\ndec: %lld\nhex: %s\n",
         number.bin, number.oct, number.dec, number.hex);

  return 0;
}
