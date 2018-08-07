package com.zombie.java.basic.strings;

public class VariasStrings {
    public void strings() {
        //栈内存分配空间给str指针，同时堆内存开辟空间存储"hello"
        String str = "Hello";
        //堆内存开辟新空间存储"world"，再开辟新空间存储"hello world"
        str += "world";
        str += "你好世界";
        System.out.println(str);
    }

    public void stringBuilders() {
        StringBuilder sb = new StringBuilder("hello");
        sb.append("world");
        sb.append("你好世界");
        System.out.println(sb.toString());
    }

    public void stringBuffers() {
        StringBuffer sb = new StringBuffer("hello");
        sb.append("world");
        sb.append("你好世界");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        VariasStrings vs = new VariasStrings();
        long a = System.currentTimeMillis();
        vs.strings();
        long b = System.currentTimeMillis();
        vs.stringBuilders();
        long c = System.currentTimeMillis();
        vs.stringBuffers();
        long d = System.currentTimeMillis();
        System.out.println("string execute time:" + (b - a) + "\n" + "stringbuilder execute time:" + (c - b) + "\n" + "stringbuffer execute time:" + (d - c));
    }
}
