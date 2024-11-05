package com.fdi17.common.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public enum SingletonThreadPool {
    INSTANCE;

    private final ExecutorService threadPool;

    SingletonThreadPool() {
        threadPool = new ThreadPoolExecutor(4, 8, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(128), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static ExecutorService getThreadPool() {
        return INSTANCE.threadPool;
    }

}
