package com.zombie.java.basic.reflect;

public class MyRefelect {
    public static void main(String[] args) {
        System.out.println(ClassA.name);
    }
}

class ClassA {
    public static String name = "zombie";
    static {
        System.out.println("static block");
    }

    public ClassA(){
        System.out.println("constructor");
    }
}