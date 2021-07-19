package com.webapp.util;

public class LazySingleton {
    int i;
    volatile private static LazySingleton INSTANCE;

    double sin = Math.sin(13.0);

    public LazySingleton() {
    }

    private static class LazySingletonHolder {
        private static final LazySingleton INSTANCE = new LazySingleton();
    }

    public static LazySingleton getINSTANCE() {
        return LazySingletonHolder.INSTANCE;
//        if (INSTANCE == null) {
//            synchronized (LazySingleton.class) {
//                if (INSTANCE == null) {
//                    int i = 15;
//                    INSTANCE = new LazySingleton();
//                }
//            }
//        }
//        return INSTANCE;
    }
}
