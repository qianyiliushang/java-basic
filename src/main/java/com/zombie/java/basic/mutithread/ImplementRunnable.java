package com.zombie.java.basic.mutithread;

public class ImplementRunnable implements Runnable {
    private String name;
    public ImplementRunnable(String name){
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " "+name + " " + i);
        }
    }

    public static void main(String[] args) {
        new Thread(new ImplementRunnable("zombie"),"Thread-zombie").start();
        new Thread(new ImplementRunnable("james")).start();

    }
}
