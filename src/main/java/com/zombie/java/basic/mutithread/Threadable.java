package com.zombie.java.basic.mutithread;

public class Threadable extends Thread {
    private String name;

    public Threadable(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " "+name + " " + i);
        }
    }

    public static void main(String[] args) {
        Threadable mt1 = new Threadable("zombie");
        Threadable mt2 = new Threadable("james");
        Threadable mt3 = mt1;
       // mt1.start();
        mt2.start();
        mt3.start();
    }
}
