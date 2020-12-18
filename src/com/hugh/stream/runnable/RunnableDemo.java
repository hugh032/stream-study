package com.hugh.stream.runnable;

/**
 * 线程
 */
public class RunnableDemo {
    public static void main(String[] args) {
        /**
         *
         */
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " say hello");
            }
        },"111");

        /**
         * lambda
         */
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " say hello");
        },"222");


        thread1.start();
        thread2.start();
    }
}
