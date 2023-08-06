# 栈和队列

1.  [栈和队列的基本概念](#栈和队列的基本概念)
2.  [栈和队列的顺序存储结构](#栈和队列的顺序存储结构)
3.  [栈和队列的链式存储结构](#栈和队列的链式存储结构)
4.  [栈和队列的应用](#栈和队列的应用)
5.  [特殊矩阵的压缩存储](#特殊矩阵的压缩存储)

## 栈和队列的基本概念

栈：

*   先进后出。
*   基本术语：入栈，出栈，栈顶（top），栈底（bottom）。
*   函数调用时，系统使用栈保存必要的信息。
*   入栈次序不能决定出栈次序。

队列：

*   先进先出。
*   基本术语：入队，出队，队头，队尾。

栈、队列和线性表的关系：栈和队列是受限的线性表，具体表现为栈只能在头结点后插入和删除，队列只在头结点删除，尾结点插入。

双端队列：

*   双端队列是与队列类似的项的有序集合。
*   双端队列有两个端部，首部和尾部，并且项在集合中保持不变。
*   双端队列不同的地方是添加和删除项是非限制性的。可以在前面或后面添加新项；同样，可以在任一端移除现有项。
*   回文问题。

## 栈和队列的顺序存储结构

```c
typedef struct {
  ElemType data[MaxSize];
  int top;
} SqStack;
```

```c
typedef struct {
  ElemType data[MaxSize];
  int front;
  int rear;
} SqQueue;
```

## 栈和队列的链式存储结构

链式栈和顺序栈之间的对比：

*   对于顺序栈和链式栈，其进栈和出栈的时间复杂度都是O(1)。
*   对于空间性能，顺序栈需要事先确定一个固定长度（虽然后面可以扩展），若是这个初始长度过大，可能造成内存空间的浪费。但是他的优势是存取时定位较方便。而链式栈则需要每个元素都有指针域，这样同时也增加了一些内存开销，但是对于栈的长度无限制。

链式栈和顺序栈的使用情况选择：

*   如果栈的使用过程中元素变化不可预测，有时小，有时大，变化频繁，那么最好是使用链式栈。
*   如果栈的变化是可控范围内，建议使用顺序栈。

```c
typedef struct StackNode {
  ElemType data;
  struct StackNode *next;
} StackNode, *LinkStackPtr;
typedef struct {
  LinkStackPtr top;
  int count;
} LinkStack;
```

```c
typedef struct QueueNode {
  ElemType data;
  struct QueueNode *next;
} QueueNode, *LinkQueuePtr;
typedef struct {
  LinkQueuePtr front, rear;
} LinkQueue;
```

## 栈和队列的应用

前缀，中缀，后缀：

*   中缀表达式转化为前缀或后缀表达式：
    *   按照运算符的优先级对所有的运算单位加括号。
    *   将运算符移动到对应括号的前面（前缀表达式）或后面（后缀表达式）。
    *   去掉括号，得到前缀或后缀表达式。

二叉树的层次遍历：可以借助一个队列，首先将二叉树的根结点入队，然后出队并访问出队结点，如果有左孩子结点，左孩子结点也入队；如果有右孩子结点，右孩子结点也入队。然后出队并访问出队结点，直到队列为空为止。

## 特殊矩阵的压缩存储

特殊矩阵：

*   含有大量相同数据元素的矩阵，比如对称矩阵。
*   含有大量0元素的矩阵，比如稀疏矩阵、上（下）三角矩阵，对角矩阵。

三元组和十字链表：

```c
/* 三元组 */
typedef struct {
  int row, col;
  ElemType e;
} Tripe;
typedef struct {
  Tripe data[MAX_SIZE];
  int m, n, len;  // 矩阵的行、列和非零元素个数
} TSMatrix;
```

```c
/* 十字链表 */
typedef struct OLNode {
  int row, col;
  ElemType e;
  struct OLNode *right, *down;  // 同一行、同一列中的下一个非零元素
} *OLink;
typedef struct {
  OLink *row_head, *col_head;
  int m, n, len;
}
```
