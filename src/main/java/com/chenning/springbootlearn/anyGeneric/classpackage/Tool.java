package com.chenning.springbootlearn.anyGeneric.classpackage;
/**
 * @Author nchen
 * @Date 2021/4/23 15:39
 * @Version 1.0
 * @Description
 */

public class Tool<E> {
    private E e;

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }

    public Tool() {

    }

    public Tool(E e) {
        this.e = e;
    }
}
