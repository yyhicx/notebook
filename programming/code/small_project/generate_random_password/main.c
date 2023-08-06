/**
 * Copyright 2022 by chnxish. All Rights Reserved.
 *
 * Distributed under MIT license.
 * See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
 */
#include <ctype.h>
#include <stdarg.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define GRAPH_ASCII_ARRAY_SIZE 94

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

bool Check(const char *src) {
  while (*src) {
    if (*src < '0' || *src > '9') return false;
    src++;
  }
  return true;
}

int main(int argc, char *argv[]) {
  int i, length;
  char graph_ascii[GRAPH_ASCII_ARRAY_SIZE];

  if (argc < 2) {
    Die("Too few arguments\n");
  } else if (argc > 2) {
    Die("Too many arguments\n");
  } else {
    if (!Check(argv[1]))
      Die("'%s' is not a positive integer", argv[1]);

    length = atoi(argv[1]);

    int j = 0;
    for (i = 0; i < 127; i++)
      if (isgraph(i))
        graph_ascii[j++] = i;

    srand((unsigned)time(NULL));
    for (i = 0; i < length; i++)
      putchar(graph_ascii[rand() % GRAPH_ASCII_ARRAY_SIZE]);
    putchar('\n');
  }

  return 0;
}
