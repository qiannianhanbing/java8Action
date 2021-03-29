package com.yangguang.java8;


import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class CompletableFutureInAction2 {


    public static void main(String[] args)
            throws InterruptedException {
        AtomicBoolean finished = new AtomicBoolean(false);


        //非守护线程
//        ExecutorService executor = Executors.newFixedThreadPool(2);
        //非守护线程
//        Future<Double> submit = executor.submit(CompletableFutureInAction1::get);


        //默认守护线程
        ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            t.setDaemon(false);
            return t;
        });
        //利用工厂类方法使其变成非守护线程
        CompletableFuture.supplyAsync(CompletableFutureInAction1::get,executor)
                .whenComplete((v, t) -> {
                    Optional.of(v).ifPresent(System.out::println);
                    finished.set(true);
                });

        System.out.println("====i am no ---block----");
        executor.shutdown();
/*        while (!finished.get()) {
            Thread.sleep(1);
        }*/
    }

}
