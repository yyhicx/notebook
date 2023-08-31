# Naming Convention

## Usage

Command Format:

*   option: -c, -k, -m, -p, -s.

    | Option | Meaning     |
    | ------ | ----------- |
    | -c     | camel case  |
    | -k     | kebab case  |
    | -m     | macro case  |
    | -p     | pascal case |
    | -s     | snake case  |

```bash
> python3 main.py option text
```

Example:

```bash
> python3 main.py -c Hello world520.-_520hello World
helloWorld520520HelloWorld

> python3 main.py -k Hello world520.-_520hello World
hello-world520-520hello-world

> python3 main.py -m Hello world520.-_520hello World
HELLO_WORLD520_520HELLO_WORLD

> python3 main.py -p Hello world520.-_520hello World
HelloWorld520520HelloWorld

> python3 main.py -s Hello world520.-_520hello World
hello_world520_520hello_world
```
