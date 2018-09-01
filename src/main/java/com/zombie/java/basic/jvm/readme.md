[TOC]
# 一、JVM内存模型
根据JVM规范，JVM内存共分为虚拟机栈、堆、方法区、程序计数器、本地方法五个部分
![img](http://o86koa12m.bkt.clouddn.com/cb0nz-qt17d.png)
## 1. 虚拟机栈
每个线程都有一个私有的栈，随着线程的创建而创建，栈里存着栈帧，栈帧中存放局部变量列表(基本数据类型和对象引用)、
大于JVM所允许的范围时，会抛出StackOverflowError，不过这个深度不是一个固定值。

栈溢出测试：
```java
package com.zombie.java.basic.jvm;

public class StackErrorMock {
    private static int index = 1;

    private void call() {
        index++;
        call();
    }

    public static void main(String[] args) {
        StackErrorMock stackErrorMock = new StackErrorMock();
        try {
            stackErrorMock.call();
        } catch (Throwable e) {
            System.out.println("stack heap: " + index);
            e.printStackTrace();
        }
    }
}
```

运行多次，每次结果都不一样，输出如下

![img](http://o86koa12m.bkt.clouddn.com/stackdeep1.png)
![img](http://o86koa12m.bkt.clouddn.com/stackdeep2.png)

虚拟机栈除了上述错误，还有一种报错，就是当申请不到空间时，抛出OutOfMemoryError。
**这里catch捕获的是Throwable而不是Exception**.StackOverflowError和OutOfMemoryError不属于Exception子类。
## 2.本地方法栈
这部分主要与虚拟机用到的Native方法相关
## 3.PC寄存器
PC寄存器，也叫程序计数器。JVM支持多个线程同时运行，每个线程都有自己的程序计数器。如果当前执行的是JVM的方法，则该寄存器中保存当前指令的地址；
如果执行的是native方法，则PC寄存器中为空。
## 4.堆
堆内存是JVM所有线程共享部分，在虚拟机启动的时候就已经创建。所有的对象和数组都在堆上进行分配。这部分空间通过GC进行回收。当申请不到空间是会招聘OutOfMemoryError.
代码示例
```java
public class HeapOomMock {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<byte[]>();
        int i = 0;
        boolean flag = true;
        while (flag){
            try {
                i++;
                list.add(new byte[1024 * 1024]);
            }catch (Throwable e){
                e.printStackTrace();
                flag = false;
                System.out.println("count: " + i);
            }
        }
    }
}
```
输出结果示例
![oom](http://o86koa12m.bkt.clouddn.com/oom.png)
## 5.方法区
方法区也是所有线程共享。主要用于存储类的信息、常量池、方法代码等。方法区逻辑上属于堆的一部分，
但是为了与堆区分，通常又叫非堆。
# 二、PermGen(永久代)
绝大部分 Java 程序员应该都见过 "java.lang.OutOfMemoryError: PermGen space "这个异常。
这里的 “PermGen space”其实指的就是方法区。不过方法区和“PermGen space”又有着本质的区别。
前者是 JVM 的规范，而后者则是 JVM 规范的一种实现，并且只有 HotSpot 才有 “PermGen space”，而对于其他类型的虚拟机，
如 JRockit（Oracle）、J9（IBM） 并没有“PermGen space”。由于方法区主要存储类的相关信息，所以对于动态生成类的情况比较容易出现永久代的内存溢出。
最典型的场景就是，在 jsp 页面比较多的情况，容易出现永久代内存溢出。
我们现在通过动态生成类来模拟 “PermGen space”的内存溢出：
```java
public class PerGenOomMock {
    public static void main(String[] args) {
        URL url = null;
        List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();
        try {
            url = new File("tmp").toURI().toURL();
            URL [] urls = {url};
            while (true){
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                loader.loadClass("com.zombie.java.basic.jvm.Test");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
```
![imgs](http://o86koa12m.bkt.clouddn.com/cbvm0-cyig8.png)
本例中使用的 JDK 版本是 1.7，指定的 PermGen 区的大小为 8M。
通过每次生成不同URLClassLoader对象来加载Test类，从而生成不同的类对象，这样就能看到我们熟悉的 "java.lang.OutOfMemoryError: PermGen space " 异常了。
这里之所以采用 JDK 1.7，是因为在 JDK 1.8 中， HotSpot 已经没有 “PermGen space”这个区间了，取而代之是一个叫做 Metaspace（元空间） 的东西。下面我们就来看看 Metaspace 与 PermGen space 的区别。

