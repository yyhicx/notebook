# homebrew

## MacOS

Install:

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

Update: brew update --verbose

Run: brew install software_name

Mirrors:

*   replace homebrew mirror

    ```bash
    cd "$(brew --repo)"
    git remote set-url origin https://mirrors.ustc.edu.cn/brew.git
    ```

*   replace homebrew-core mirror

    ```bash
    cd "$(brew --repo)"/Library/Taps/homebrew/homebrew-core
    git remote set-url origin https://mirrors.ustc.edu.cn/homebrew-core.git
    ```

*   replace homebrew-cask mirror

    ```bash
    cd "$(brew --repo)"/Library/Taps/homebrew/homebrew-cask
    git remote set-url origin https://mirrors.ustc.edu.cn/homebrew-cask.git
    ```
