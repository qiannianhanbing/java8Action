package com.yangguang.java8;

import java.util.Arrays;
import java.util.concurrent.*;

public class FutureInAction2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
            try {
                Thread.sleep(10000L);
                return "I am finished.";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "Error";
            }
        });
        //一直阻塞，直到方法执行完毕
//        String value = future.get();
        //阻塞等待指定的时间
//        String value = future.get(10, TimeUnit.MICROSECONDS);
//        System.out.println("value = " + value);

        /**
         * 可以异步执行一定逻辑，后续根据isDone获取返回值
         */
        System.out.println("args = " + Arrays.deepToString(args));
        while (!future.isDone()) {
            Thread.sleep(10);
        }
        System.out.println(future.get());
        executorService.shutdown();
    }
}
