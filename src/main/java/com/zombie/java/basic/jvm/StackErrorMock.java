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
            System.out.println("stack deep: " + index);
            e.printStackTrace();
        }
    }
}
