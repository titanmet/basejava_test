package com.webapp;

import com.webapp.util.LazySingleton;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private static volatile int counter;
    private static final Object LOCK = new Object();
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", " +
                Thread.currentThread().getState())).start();

        System.out.println(thread0.getState());
        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
            thread.join();
        }
        threads.forEach(t-> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(mainConcurrency.counter);
    }

    private synchronized void inc() {
//        synchronized (this) {
//        synchronized (MainConcurrency.class) {
//            double a = Math.sin(13.);
//        try {
//            synchronized (this) {
                counter++;
//                wait();
//                readFile;
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        }
//        }
    }
}
