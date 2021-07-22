package com.webapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private static volatile int counter;
    private final AtomicInteger atomicCounter = new AtomicInteger();
    private static final Object LOCK = new Object();
    private static final Lock lock = new ReentrantLock();

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat();
        }
    };

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
//                throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", " +
                Thread.currentThread().getState())).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletionService completionService = new ExecutorCompletionService(executorService);

//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> future = executorService.submit(() ->
//            Thread thread = new Thread(() ->
            {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    System.out.println(threadLocal.get().format(new Date()));
                }
                latch.countDown();
                return 5;
            });
            completionService.poll();
            System.out.println(future.isDone());
            System.out.println(future.get());
//            thread.start();
//           threads.add(thread);
//            thread.join();
//        }
//        threads.forEach(t-> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
            latch.await(10, TimeUnit.SECONDS);
            executorService.shutdown();
            System.out.println(mainConcurrency.counter);
            System.out.println(mainConcurrency.atomicCounter.get());
        }

//        final String lock1 = "lock1";
//        final String lock2 = "lock2";
//        deadLock(lock1, lock2);
//        deadLock(lock2, lock1);

    }

    public int[] minValue(int[] values) {
       return values;
    }

    public List<Integer> oddOrEven(List<Integer> integers) {
        return integers;
    }

    private static void deadLock(String lock1, String lock2) {
        new Thread(() -> {
            System.out.println("Waiting " + lock1);
            synchronized (lock1) {
                System.out.println("Holding " + lock1);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting " + lock2);
                synchronized (lock2) {
                    System.out.println("Holding " + lock2);
                }
            }
        }).start();
    }

    private void inc() {
//        synchronized (this) {
//        synchronized (MainConcurrency.class) {
//            double a = Math.sin(13.);
//        try {
//            synchronized (this) {
        atomicCounter.incrementAndGet();
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
//                wait();
//                readFile;
//        }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        }
//        }
    }
}

