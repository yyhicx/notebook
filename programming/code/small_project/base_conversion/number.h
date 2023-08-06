/**
 * Copyright 2022 by chnxish. All Rights Reserved.
 *
 * Distributed under MIT license.
 * See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
 */
#ifndef BASE_CONVERSION_NUMBER_H_
#define BASE_CONVERSION_NUMBER_H_

#include <stdbool.h>
#include <stdint.h>

#define BIN_ARRAY_SIZE 65
#define OCT_ARRAY_SIZE 23
#define HEX_ARRAY_SIZE 17

typedef struct Number {
  char bin[BIN_ARRAY_SIZE];
  char oct[OCT_ARRAY_SIZE];
  int64_t dec;
  char hex[HEX_ARRAY_SIZE];
} Number;

bool Check(char *nptr, int base);
void Process(const char *nptr, int base, Number *number);

void OtherBasesToDecimal(const char *src, int base, int64_t *dst);
void DecimalToOtherBases(int64_t src, int base, char *dst);

void ReverseString(char *src);

#endif  // BASE_CONVERSION_NUMBER_H_
