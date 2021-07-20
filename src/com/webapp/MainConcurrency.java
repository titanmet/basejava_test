package com.webapp;

import com.webapp.model.Section;
import com.webapp.util.LazySingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);

//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
                latch.countDown();
            });
            thread.start();
//            threads.add(thread);
            thread.join();
        }
//        threads.forEach(t-> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
        latch.await(10, TimeUnit.SECONDS);
        System.out.println(mainConcurrency.counter);

        final String lock1 = "lock1";
        final String lock2 = "lock2";
//        deadLock(lock1, lock2);
//        deadLock(lock2, lock1);


    }

    private static void deadLock(String lock1, String lock2) {
        new Thread(()-> {
            System.out.println("Waiting " + lock1);
            synchronized (lock1) {
                System.out.println("Holding " + lock1);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting " + lock2);
                synchronized (lock2){
                    System.out.println("Holding " + lock2);
                }
            }
        }).start();
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
