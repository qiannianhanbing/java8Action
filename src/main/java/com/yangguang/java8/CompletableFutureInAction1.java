package com.yangguang.java8;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureInAction1 {

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args)
            throws ExecutionException, InterruptedException {

        //异步
        supplyAsync();

        //执行其他操作
        System.out.println("执行其他操作");
    }

    static void supplyAsync(){

        //supplyAsync
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            double value = get();
            completableFuture.complete(value);
        }).start();

        System.out.println("===no===block....");

//        completableFuture.get()同步阻塞

        //异步执行  非阻塞
        completableFuture.whenComplete((v, t) -> {
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(x -> x.printStackTrace());
        });

        System.out.println("===no===block....");


    }
    static double get() {
        try {
            Thread.sleep(RANDOM.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double result = RANDOM.nextDouble();
        System.out.println(result);
        return result;
    }
}