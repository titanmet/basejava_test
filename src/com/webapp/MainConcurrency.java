package com.webapp;

public class MainConcurrency {
    private static int counter;
    private static final Object LOCK = new Object();
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();

        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", " +
                Thread.currentThread().getState())).start();

        System.out.println(thread0.getState());
        final MainConcurrency mainConcurrency = new MainConcurrency();

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            }).start();
        }

        Thread.sleep(500);
        System.out.println(counter);
    }

    private void inc() {
//        synchronized (this) {
//        synchronized (MainConcurrency.class) {
            double a = Math.sin(13.);
        try {
            synchronized (this) {
                counter++;
                wait();
//                readFile;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        }
//        }
    }
}
