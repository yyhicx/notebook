# 关系与函数

## 关系及其性质

关系及其表示：

*   设A，B为集合，A×B的任何子集叫做`从A到B的二元关系`，当A=B时则叫做`A上的二元关系`，简称为`关系`，记作`R`（从此开始，如果没有明确说明，R表示某种关系，而不是实数集）。
*   关系是一种集合，是笛卡尔乘积的子集（集合中元素是有序对）。

设A为任意集合：

*   Ø是A上的关系，称为`空关系`。
*   E<sub>A</sub>称为`全域关系`，定义是：<code>E<sub>A</sub> = {<x, y> | x∈A ∧ y∈A} = A×A</code>。
*   I<sub>A</sub>称为`恒等关系`，定义是：<code>I<sub>A</sub> = {<x, x> | x∈A}</code>。

其他的一些关系：

*   小于等于关系：<code>L<sub>A</sub> = {<x, y> | x,y∈A ∧ x≤y}</code>，A⊆R，R为实数集合。
*   整除关系：<code>D<sub>A</sub> = {<x, y> | x,y∈B ∧ x整除y}</code>，B⊆Z<sup>*</sup>，Z<sup>*</sup>为非0整数集（6÷2=3，其中2整除6，6能被2整除）。
*   包含关系：<code>R<sub>⊆</sub> = {<x, y> | x,y∈A ∧ x⊆y}</code>，A是集合族。
*   类似的还可以定义大于等于关系、小于关系、大于关系、真包含关系等等。

定义域、值域和域：

*   定义域：`domR = {x | ∃y(<x, y>∈R)}`。
*   值域：`ranR = {y | ∃x(<x, y>∈R)}`。
*   域：`fldR = domR ∪ ranR`。

关系的表示方式：

*   集合方式：序偶的集合。
*   代数方式：关系矩阵（适合计算）。
*   几何方式：关系图（更加直观）。

关系矩阵：若<code>A={x<sub>1</sub>, x<sub>2</sub>, ..., x<sub>m</sub>}</code>，<code>B={y<sub>1</sub>, y<sub>2</sub>, ..., y<sub>n</sub>}</code>，R是从A到B的关系（R⊆A×B），R的关系矩阵是布尔矩阵<code>M<sub>R</sub>=[r<sub>ij</sub>]<sub>m×n</sub></code>，其中<code>r<sub>ij</sub>=1 ⇔ <x<sub>i</sub>, y<sub>j</sub>>∈R</code>。

关系图：若<code>A={x<sub>1</sub>, x<sub>2</sub>, ..., x<sub>m</sub>}</code>，<code>B={y<sub>1</sub>, y<sub>2</sub>, ..., y<sub>n</sub>}</code>，R是从A到B的关系（R⊆A×B）：

*   集合中的元素x<sub>1</sub>，x<sub>2</sub>，...，x<sub>m</sub>和y<sub>1</sub>，y<sub>2</sub>，...，y<sub>n</sub>分别作为图中的结点，用`○`表示。
*   如果<x<sub>i</sub>, y<sub>j</sub>>∈R，则从x<sub>i</sub>到y<sub>j</sub>用一条有向边相连。

关系的性质：

*   自反性与反自反性：
    *   设R为A上的关系：
        *   若`∀x(x∈A → <x, x>∈R)`，则称R在A上`自反`的。
        *   若`∀x(x∈A → <x, x>∉R)`，则称R在A上`反自反`的。
    *   关系R在A上，要么是`自反`，要么是`反自反`，要么`既不是自反也不是反自反`。
*   对称性与反对称性：
    *   设R为A上的关系：
        *   若`(∀<x, y>)(<x, y>∈R → <y, x>∈R)`，则称R为A上的`对称`关系。
        *   若`(∀<x, y>)(<x, y>∈R∧<y, x>∈R → x=y)`，则称R为A上的`反对称`关系。
    *   关系R在A上，可以同时有对称性和反对称性，可以同时没有，也可以只有一个另一个没有。
*   传递性：
    *   设R为A上的关系：
        *   若`(∀<x, y>)(∀<y, z>)(<x, y>∈R∧<y, z>∈R → <x, z>∈R)`，则称R为A上的`传递`关系。

![关系的性质](resources/relation_properties.png)

## 关系的运算

关系是一个集合，因此，所有集合的运算及规律均适用于关系，除此以外还有如下运算。

布尔运算：

*   若矩阵A中每个元素要么是1要么是0，那么A称为布尔矩阵（Boolean Matrix）。
*   设A=[a<sub>ij</sub>]，B=[b<sub>ij</sub>]均为m×n的布尔矩阵，定义<code>A∨B=C=[c<sub>ij</sub>]</code>，称之为A和B的`并`：
    *   如果a<sub>ij</sub>=1或b<sub>ij</sub>=1，则c<sub>ij</sub>=1。
    *   如果a<sub>ij</sub>=0且b<sub>ij</sub>=0，则c<sub>ij</sub>=0。
*   设A=[a<sub>ij</sub>]，B=[b<sub>ij</sub>]均为m×n的布尔矩阵，定义<code>A∧B=C=[c<sub>ij</sub>]</code>，称之为A和B的`交`：
    *   如果a<sub>ij</sub>=1且b<sub>ij</sub>=1，则c<sub>ij</sub>=1。
    *   如果a<sub>ij</sub>=0或b<sub>ij</sub>=0，则c<sub>ij</sub>=0。
*   设A=[a<sub>ij</sub>]为m×p的布尔矩阵，B=[b<sub>ij</sub>]为p×n的布尔矩阵，定义<code>A⊙B=C=[c<sub>ij</sub>]</code>，称之为A和B的`布尔积`：
    *   如果∃k，1≤k≤n，使得a<sub>ik</sub>=1且b<sub>kj</sub>=1，则c<sub>ij</sub>=1。
    *   否则c<sub>ij</sub>=0。

逆运算：

*   设R是集合A到B的二元关系，称`R<sup>-1</sup>={<y, x> | <x, y>∈R}为R的逆`。
*   设R和S是集合A到B的二元关系，则：
    1.  M<sub>R∩S</sub> = M<sub>R</sub> ∧ M<sub>S</sub>。
    2.  M<sub>R∪S</sub> = M<sub>R</sub> ∨ M<sub>S</sub>。
    3.  M<sub>R<sup>-1</sup></sub> = (M<sub>R</sub>)<sup>T</sup>。
*   设R和S是集合A到B的二元关系，则（R<sup>c</sup>表示R的补）：
    1.  若R⊆S，则R<sup>-1</sup>⊆S<sup>-1</sup>。
    2.  若R⊆S，则S<sup>c</sup>⊆R<sup>c</sup>。
    3.  (R∩S)<sup>-1</sup>=R<sup>-1</sup>∩S<sup>-1</sup>，(R∪S)<sup>-1</sup>=R<sup>-1</sup>∪S<sup>-1</sup>。
    4.  (R∩S)<sup>c</sup>=R<sup>c</sup>∪S<sup>c</sup>，(R∪S)<sup>c</sup>=R<sup>c</sup>∩S<sup>c</sup>。

复合运算：设R是从A到B的二元关系，S是从B到C的二元关系，定义`R○S={<x, z> | ∃y(<x, y>∈R∧<y, z>∈S)}`，称`R○S为R和S的复合关系`。

重要提示：

*   上述定义的关系复合属于右复合，本课程如无特殊说明，均指右复合。
*   可以类似的定义左复合。
*   本课程中，函数的复合也定义为右复合。

## 等价关系与序关系

## 函数
