package com.eric.yyxz.android.ui.auto.test.handler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolHandler {

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            10,
            100,
            0L,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100));

    public static void execute(Runnable runnable){
        executor.execute(runnable);
    }

    public static Future submit(Runnable runnable){
        return executor.submit(runnable);
    }

    public static <T>Future<T> submit(Runnable runnable, T result){
        return executor.submit(runnable, result);
    }

    public static <T>Future<T> submit(Callable<T> callable){
        return executor.submit(callable);
    }
}
