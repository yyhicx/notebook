# OCR

Use Baidu OCR API to recognize characters in pictures.

## Usage

You need to set:

*   install Snipaste software and run it.
*   create api_key.json file and set APP_ID, API_KEY and SECREY_KEY, as follows:

    ```json
    {
      "APP_ID": "12345678",
      "API_KEY": "abcdefgh12345678",
      "SECREY_KEY": "abcdefgh12345678"
    }
    ```

Command Format:

*   option: -l, -u, -s.

    | Option | Image Location       |
    | ------ | -------------------- |
    | -l     | Local                |
    | -u     | Network              |
    | -s     | Screenshots Required |

```bash
> python3 main.py option empty_or_path_or_url
```

Example:

```bash
> python3 main.py -l ./image/string.png

> python3 main.py -u url_of_the_image

> python3 main.py -s
```
