Q: 参加阿里的一个测试开发岗位的面试被问到`String`,`StringBuilder`和`StringBuffer`的区别

回答的时候只考虑了使用场景和执行效率网上搜索了一些资料发现还是有其它方面区别的。
首先是执行速度方面StringBuilder > StringBuffer > String,先看示例代码
```java
    public void Strings(){
        String str = "hello";
        System.out.println(str);
        str += "world";
        System.out.println(str);
    }
```

>   究其原因，String是对象，是字符串常量，是不可更改的，也就是immutable的概念.也就是说每次对String作操作实际上是创建了新的对象，分析其执行过程如下图所示，上述操作开辟了三次内存
分别用来存储"Hello","world","Hello World"，最终str指针指向"Hello world"，而`StringBuffer`和`StringBuilder`是字符串常量，可以进行多次修改而不用开辟新的空间或者产生未被使用的对象
所以**当字符串需要经常被修改时，建议使用`StringBuilder`或者`StringBuffer`**

![strings](http://o86koa12m.bkt.clouddn.com/strings.png)
对于`StringBuilder`和`StringBuffer`的主要区别是`StringBuffer`是线程安全的，不可被同步访问。
>   总结：一般情况下
>   * 当字符串不会被频繁修改时，使用`String`，
>   * 单线程字符串缓冲区操作大量数据使用`StringBuilder`
>   * 多线程字符串缓冲区操作大量数据使用`StringBuffer`