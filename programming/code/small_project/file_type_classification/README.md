# File Type Classification

## Usage

Example:

*   Assume that there are three files `.gitconfig`, `hello.cc` and `License` in the current directory.

```bash
> python3 main.py .
+-----------+------------+--------------------+
| file_type | file_name  | file_realtive_path |
+-----------+------------+--------------------+
|   .XXXX   | .gitconfig |    .gitconfig      |
|   XX.XX   |  hello.cc  |     hello.cc       |
|    XXXX   |  License   |     License        |
+-----------+------------+--------------------+
```
