package com.platform.config;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 */
public final class ThreadPooBuild {
    private static ThreadLocal<ThreadPooBuild> instance = new ThreadLocal<ThreadPooBuild>();
    protected static ListeningExecutorService service = null;
    protected static ScheduledThreadPoolExecutor scheduledThreadPool = null;

    public static ListeningExecutorService getService() {
        return service;
    }

    public static ScheduledThreadPoolExecutor getScheduledThreadPool() {
        return scheduledThreadPool;
    }

    static {
        if ((instance.get() == null) || (service == null)) {
            syncInitBak();
        }
    }

    public static void shutdown() {
        service.shutdown();
    }

    //TODO 线程初始化问题
    private static void syncInitBak() {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        service = MoreExecutors.listeningDecorator(poolExecutor);
        scheduledThreadPool = new ScheduledThreadPoolExecutor(16);
        scheduledThreadPool.setRemoveOnCancelPolicy(true);
    }
}
