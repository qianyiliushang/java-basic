package com.zombie.java.basic.abs;

public class AbsTest extends AbsClazz {

    private AbsTest(int a) {
        super(a);
    }

    @Override
    int getA() {
        return a;
    }

    @Override
    public int getConst() {
        return a * 10;
    }

    public static void main(String[] args) {
        AbsTest absTest = new AbsTest(10);
        System.out.println(absTest.getA());
        System.out.println(absTest.getConst());
    }
}
