package com.example.my.util;

/**
 * Created by Administrator on 2018/3/30.
 */

public class Singleton {
    public Singleton() {
    }

    public static Singleton getInstance() {
        return SingleHolder.instance;
    }

    private static class SingleHolder {
        private static final Singleton instance = new Singleton();
    }
}
