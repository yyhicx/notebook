/**
 * Copyright 2022 by chnxish. All Rights Reserved.
 *
 * Distributed under MIT license.
 * See file LICENSE for detail or copy at https://opensource.org/licenses/MIT
 */
#include "number.h"

#include <stdlib.h>
#include <string.h>

static const char kZeroToF[16] = {'0', '1', '2', '3', '4', '5', '6', '7',
                                  '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

bool Check(char *nptr, int base) {
  char temp;

  if (base == 10 && nptr[0] == '-')
    nptr++;

  while (*nptr) {
    temp = *nptr;
    if (base != 16 && temp >= kZeroToF[0] && temp < kZeroToF[base]) {
      nptr++;
    } else if (base == 16 &&
               ((temp >= kZeroToF[0] && temp <= kZeroToF[9]) ||
               (temp >= kZeroToF[10] && temp <= kZeroToF[15]))) {
      nptr++;
    } else if (base == 16 && temp >= 'a' && temp <= 'f') {
      *nptr -= 32;
      nptr++;
    } else {
      return false;
    }
  }

  return true;
}

void Process(const char *nptr, int base, Number *number) {
  switch (base) {
    case 2:
      strncpy(number->bin, nptr, BIN_ARRAY_SIZE);
      (number->bin)[BIN_ARRAY_SIZE - 1] = '\0';
      OtherBasesToDecimal(number->bin, 2, &(number->dec));
      DecimalToOtherBases(number->dec, 8, number->oct);
      DecimalToOtherBases(number->dec, 16, number->hex);
      break;
    case 8:
      strncpy(number->oct, nptr, OCT_ARRAY_SIZE);
      (number->oct)[OCT_ARRAY_SIZE - 1] = '\0';
      OtherBasesToDecimal(number->oct, 8, &(number->dec));
      DecimalToOtherBases(number->dec, 2, number->bin);
      DecimalToOtherBases(number->dec, 16, number->hex);
      break;
    case 10:
      number->dec = atoll(nptr);
      DecimalToOtherBases(number->dec, 2, number->bin);
      DecimalToOtherBases(number->dec, 8, number->oct);
      DecimalToOtherBases(number->dec, 16, number->hex);
      break;
    case 16:
      strncpy(number->hex, nptr, HEX_ARRAY_SIZE);
      (number->hex)[HEX_ARRAY_SIZE - 1] = '\0';
      OtherBasesToDecimal(number->hex, 16, &(number->dec));
      DecimalToOtherBases(number->dec, 2, number->bin);
      DecimalToOtherBases(number->dec, 8, number->oct);
      break;
  }
}

void OtherBasesToDecimal(const char *src, int base, int64_t *dst) {
  *dst = 0;

  while (*src) {
    *dst *= base;
    if (*src <= '9') {
      *dst += *src - '0';
    } else {
      *dst += *src - 'A' + 10;
    }
    src++;
  }
}

void DecimalToOtherBases(int64_t src, int base, char *dst) {
  uint64_t temp = (uint64_t)src;
  int i = 0;

  if (temp == 0) {
    dst[0] = '0';
    dst[1] = '\0';
    return;
  }

  while (temp) {
    dst[i] = kZeroToF[temp % base];
    temp /= base;
    i++;
  }
  dst[i] = '\0';

  ReverseString(dst);
}

void ReverseString(char *src) {
  int i, len = strlen(src);
  char temp;

  for (i = 0; i < len / 2; i++) {
    temp = src[i];
    src[i] = src[len - i - 1];
    src[len - i - 1] = temp;
  }
}
