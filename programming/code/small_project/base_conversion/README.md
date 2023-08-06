# Base Conversion

Converts an integer to binary, octal, decimal or hexadecimal.

## Usage

Command Format:

*   option: -b, -o, -d, -h.

    | Option | Meaning |
    | ------ | ------- |
    | -b     | bin     |
    | -o     | oct     |
    | -d     | dec     |
    | -h     | hex     |

*   number: please enter valid integer value.

```bash
> base_conversion option number
```

Example:

```bash
> base_conversion -b 10101010
bin: 10101010
oct: 252
dec: 170
hex: AA

> base_conversion -o 1777777777777777777777
bin: 1111111111111111111111111111111111111111111111111111111111111111
oct: 1777777777777777777777
dec: -1
hex: FFFFFFFFFFFFFFFF

> base_conversion -d -1
bin: 1111111111111111111111111111111111111111111111111111111111111111
oct: 1777777777777777777777
dec: -1
hex: FFFFFFFFFFFFFFFF

> base_conversion -d 9223372036854775807
bin: 111111111111111111111111111111111111111111111111111111111111111
oct: 777777777777777777777
dec: 9223372036854775807
hex: 7FFFFFFFFFFFFFFF

> base_conversion -h 7FFFFFFFFFFFFFFF
bin: 111111111111111111111111111111111111111111111111111111111111111
oct: 777777777777777777777
dec: 9223372036854775807
hex: 7FFFFFFFFFFFFFFF
```
