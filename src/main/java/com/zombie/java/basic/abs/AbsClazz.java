package com.zombie.java.basic.abs;

public abstract class AbsClazz {
    int a;
    abstract int getA();
    int getConst (){
        return a;
    }
    public AbsClazz(int a){
        this.a = a;
    }
}
