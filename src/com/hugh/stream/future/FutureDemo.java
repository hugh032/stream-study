package com.hugh.stream.future;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Future 异步
 */
public class FutureDemo {
    public static void main(String[] args) {
        System.out.println(test());
    }
    
    public static int test() {
        int value;
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                SECONDS.sleep(5);
                return 7;
            }
        });
        value = doSomethingElse(0);
        System.out.println("等待future线程计算结果，main线程计算的值：" + value);
        try {
            Integer result = future.get(3, SECONDS); // 最多等待future线程结果的时间
            value += result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static int doSomethingElse(int v) {
        v++;
        return v;
    }

}
