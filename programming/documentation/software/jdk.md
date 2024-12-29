# jdk

## Linux

Install: apt install default-jdk

Set the environment variable:

*   vim ~/.bashrc
*   add the following line:

    ```txt
    export JAVA_HOME=/usr/lib/jvm/default-java
    export PATH=$JAVA_HOME/bin:$PATH
    ```

*   source ~/.bashrc
