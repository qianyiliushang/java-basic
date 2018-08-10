package com.zombie.java.basic.abs;

public class InterfaceTest implements MyInterface {

    public int getA() {
        return a;
    }

    public int getConst() {
        return a*10;
    }

    public static void main(String[] args) {
        InterfaceTest interfaceTest = new InterfaceTest();
        System.out.println(interfaceTest.getA());
        System.out.println(interfaceTest.getConst());
    }
}
