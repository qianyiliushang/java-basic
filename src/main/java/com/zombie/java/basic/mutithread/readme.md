# 多线程相关
在我的工作经历中，没有自己写过多线程相关的代码，面试过的几家公司全都问到了相关的知识点，在此学习记录

## 多线程实现方式
1. 继承`Thread`类
2. 实现`Runnable`接口
示例分别见Threadable类和ImplementRunnable类

## Runnable相比Thread的优势
* 适合多个相同的程序代码线程处理同一资源
* 避免java中的单继承机制
* 增加程序稳健性，代码可以被多个线程共享，代码和数据独立
* 线程池只能放入实现Runable或Callable类线程，不能直接放入继承Thread的类