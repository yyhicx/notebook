# cpplint

## Windows

Install: pip3 install cpplint

Run:

```bash
cpplint file
cpplint --recursive document
# Header files are located in the same folder, exclude the build directory, recursively process the current directory
cpplint --filter=-build/include_subdir --exclude=./build --recursive .
```
