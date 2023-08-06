# React中常见问题

1.  [React生命周期](#react生命周期)

## React生命周期

生命周期（Lifecycle）指React组件从装载至卸载的全过程，这个过程内置多个函数供开发者在组件的不同阶段执行需要的逻辑。

状态组件主要通过3个生命周期阶段来管理，分别是`装载阶段（MOUNTING）`，`更新阶段（UPDATING）`和`卸载阶段（UNMOUNT）`。从纵向划分，可以划分为`Render阶段`和`Commit阶段`。

![React生命周期](../resources/react_lifecycle.jpg)

装载阶段：

*   组件的渲染并且构造DOM元素插入到页面的过程称为组件的装载。
*   装载阶段执行的函数会在组件实例被创建和插入DOM中时被触发，这个过程主要实现组件状态的初始化。
