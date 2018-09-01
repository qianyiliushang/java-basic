package com.zombie.java.basic.abs;

abstract class AbsClazz {
    int a;
    abstract int getA();
    int getConst (){
        return a;
    }
    AbsClazz(int a){
        this.a = a;
    }
}
