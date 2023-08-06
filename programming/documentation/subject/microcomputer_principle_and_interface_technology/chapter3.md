# 8086汇编语言程序设计

1.  [8086指令寻址方式](#8086指令寻址方式)
2.  [汇编语言源程序基础](#汇编语言源程序基础)
3.  [8086汇编伪指令与操作数运算符](#8086汇编伪指令与操作数运算符)
4.  [8086汇编指令与汇编语言程序](#8086汇编指令与汇编语言程序)

## 8086指令寻址方式

指令格式：操作码 + 操作数（0个或多个）。

指令的寻址方式：

*   寻找指令中操作数的方式。
*   分为两种：数据寻址，转移操作寻址。

数据寻址：

*   立即寻址：`mov ax, 1234H`。
*   寄存器寻址：`mov ds，ax`。
*   存储器寻址：
    *   直接寻址：`mov ax, [1234H]`，`mov bl, ss:[10H]`。
    *   寄存器间接寻址：
        *   `mov ax, [bx]/[si]/[di]`：访问ds。
        *   `mov bl, [bp]`：访问ss。
        *   `add ax, es:[bx]`。
    *   基址寻址：
        *   `mov ax, [bx+10H]`：访问`ds:[bx+10H]`。
        *   `mov bl, 10H[bp]`：访问`ss:[bp+10H]`。
        *   `add ax, es:[bx+200H]`：访问`es:[bx+200H]`。
    *   变址寻址：
        *   `mov ax, [si+120H]`：访问`ds:[si+120H]`。
        *   `mov ax, [di+100H]`：访问`ds:[di+100H]`。
    *   基址变址寻址：
        *   `mov ax, 10H[bx][si]`：访问`ds:[bx+si+10H]`。
        *   `mov al, [bp+di+10H]`：访问`ss:[bp+di+10H]`。
*   I/O端口寻址：
    *   I/O端口直接寻址：
        *   `in al, 20H`：从20H端口读入1个字节。
        *   `out 60H, ax`：将ax中一个字送到60H和61H中。
    *   I/O端口间接寻址：
        *   `mov dx, 3FCH`和`in al, dx`将#FCH端口读入1字节。

转移操作寻址：指令在顺序执行时，下一条指令的偏移地址由IP自动递增而得。程序发生转移时，需要给出即将转移去执行的那条指令的有效地址，并用它去取代IP原有内容；如果转去执行的指令与原来执行的指令不在同一个代码段，还需用新的代码段基址去取代CS中的内容。此时操作数作为转移地址使用，称为转移操作寻址。

*   段内相对寻址：`jmp LABEL`。
*   段内间接寻址：
    *   `jmp bx`。
    *   `jmp var`。
    *   `jmp var[si]`。
*   段间直接寻址：`jmp LABEL`。
*   段间间接寻址：
    *   `jmp var` 从var处获取两个字，第一个字代表偏移地址，第二个字代表段地址。
    *   `jmp var[si]` 从`ds:[si+var]`获取两个字。

## 汇编语言源程序基础

src: src/hello.asm

```asm
.model small                    ;规定内存模式
.data                           ;定义数据段
msg db 'This is an example.$'   ;$是字符串结束符
.stack                          ;定义堆栈段
.code                           ;定义代码段
start:
  mov ax, @data                 ;@data得到.data的段基址
  mov ds, ax
  mov dx, offset msg            ;返回数据的偏移量
  mov ah, 9                     ;显示DS:DX处一个字符
  int 21h
  mov ax, 4c00h                 ;退出应用程序
  int 21h
end start
```

语句：

*   指令性语句，即符号指令。指令语句汇编时产生目标代码，对应一条机器指令，通过CPU进行某种操作，由硬件完成其功能。

    ```txt
    [标号:] 指令助记符 [操作数1, ][操作数2, ...][;注释]
    ```

*   指示性语句，即伪指令。为汇编程序提供编译信息，为链接程序提供链接信息，其功能由相应软件完成。

    ```txt
    [变量] 命令 [操作数1, ][操作数2, ...][;注释]
    ```

标识符：由程序员自定义，可作为段名，过程名，变量名，标号，模块名等。

常量：0101B，234Q，85，2FH，'a'。

## 8086汇编伪指令与操作数运算符

简化段定义伪指令：

*   存储模式伪指令：`.model small`。
*   数据段定义伪指令：`.data`。
    *   生成的数据段段名为：`_data`。
    *   获取数据段的段基址：`mov ax, @data  mov ds, ax`。
*   代码段定义伪指令：`.code [name]`。
*   堆栈段定义伪指令：`.stack [size]`。

数据定义伪指令：为一个数据项分配存储单元，用一个符号名与这个或这些存储单元相关联，并为这个数据项提供一个任选的初始值。

*   命令：
    *   `db`：一个字节。
    *   `dw`：一个字。
    *   `dd`：两个字。

```txt
[变量] 命令 [操作数1, ][操作数2, ...][;注释]
```

```asm
nlist db 11B, 'B', -1 ;03H，42H，0FFH，占3个字节
      db 'PAS', ?     ;占4个字节
nls dw 1, 2, 'AB'     ;0001H，0002H，4241H，占3个字
buffer dw 100 dup(?)              ;占100个字节
sw db 3 dup('A5', 2), 1           ;占10个字节
array db 2 dup('abc', 3 dup(?))   ;占12个字节
```

地址定位伪指令与地址计数器：

```asm
.data
data1 db 31H
data2 db 'abc'
org 6               ;偏移至0006H
data3 db 86H, 5FH
org $+2             ;当前偏移地址0008H+2，为000AH
.code
```

符号定义伪指令：

```asm
const equ 100*2
num=32
```

过程定义伪指令：

```asm
proc_name proc [near/far]
;过程体
proc_name endp
```

宏指令：

```asm
macro_name macro [param1, param2, ...]
;宏体
endm

;定义一个宏
addup macro ad1,ad2,ad3
  mov ax, ad1
  add ax, ad2
  add ax, ad3
  endm

;调用一个宏
addup bx, 2, count
```

操作数运算符：

*   算数运算符：+，-，*，/，mod。
*   逻辑与移位运算符：and，or，xor，not，shl，shr。
*   关系运算符：eq，ne，le，ge，lt，gt。
*   数值回送运算符：
    *   `mov ax, seg msg    ;返回变量段地址`。
    *   `mov ax, offset msg ;返回变量偏移地址`。
    *   `mov si, type block     ;假设block为dw，则返回2`。
    *   `mov si, lengthof block ;返回元素个数`。
    *   `mov si, sizeof block   ;返回字节数，即type*lengthof`。
    *   `mov ax, word ptr x         ;修改变量的读取类型为左侧数据类型`。

## 8086汇编指令与汇编语言程序

程序：完成一个特定功能的指令序列，为解决某个问题的算法描述。

人机交互程序：键盘数据的输入，屏幕数据的输出。

调用bios系统或dos系统功能产生交互：

*   中断指令：int n，n为中断类型号，取值范围0~255。
*   中断返回指令：iret。
*   BIOS：基本输入/输出系统，一组管理程序，包括上电自检程序，系统引导程序，日时钟管理程序和基本I/O设备的驱动程序等。
*   DOS：磁盘操作系统，单用户单任务的操作系统。
*   用INT指令进行BIOS和DOS功能调用的步骤：
    *   功能号送到AH寄存器。
    *   按要求将所有入口参数送到指定寄存器。
    *   发送`int n`软中断指令。

从键盘输入一个字符：

```asm
;dos系统功能，键盘输入的一个字符会存入al寄存器
mov ah, 1
int 21h

;dos系统功能，不等待，不回显
mov ah, 6
int 21h

;bios系统功能，等待，不回显
mov ah, 0
int 16h
```

向屏幕输出一个字符：

```asm
;dos系统功能，从dl寄存器输出一个字符
mov ah, 2
mov dl, 'a'
int 21h
```

向屏幕输出一个字符串：

*   src: src/hello_world.asm

```asm
.data
  msg db 'This is an example.$'
.code
  mov ax, @data
  mov ds, ax
  mov dx, offset msg
  mov ah, 9
  int 21h
```

从键盘输入一个字符串：

```asm
.data
  maxlen db 80         ;字符缓冲区最大长度
  actlen db ?          ;缓冲区实际输入的字符个数
  string db 80 dup(?)  ;80字符空间作为字符缓冲区
.code
  mov ax, @data
  mov ds, ax
  mov dx, offset maxlen
  mov ah, 0AH
  int 21h
```

8086的基本指令（顺序）：

*   数据传送指令：mov，push，pop，xchg，xlat，in，out。
*   算数运算指令：add，adc，sub，sbb，mul，imul，div，idiv。
*   逻辑运算指令：and，or，xor，not，test。
*   移位与循环指令：shl，sal，shr，sar，rol，ror。
*   处理器控制指令：wait，nop。

8086的转移指令（判断和循环）：

*   无条件转移指令：jmp。
*   有条件转移指令：jnz，jns，jcxz。

```asm
  mov cx, 10
delay:
  push cx
  mov cx, 2801
wait:
  loop wait
  pop cx
  loop delay
```

子程序的设计：

*   子程序相关指令：call，ret。
*   为了保证主程序在调用子程序后，主程序所用的寄存器的内容不被破坏，子程序在完成自己功能前，将要用到的寄存器的值先保存起来，这个过程称为现场保护。等子程序功能执行后，再将这些寄存器的值恢复，这个过程称为现场恢复。
