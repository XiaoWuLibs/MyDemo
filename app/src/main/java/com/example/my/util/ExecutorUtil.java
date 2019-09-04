package com.example.my.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by  wsl
 * on 2019/5/10 10:34
 * 线程池工具类，用于解决阿里代码检测不允许使用Executors静态方法的问题
 */
public class ExecutorUtil {
    /**
     * 私有构造函数
     */
    private ExecutorUtil() {
    }

    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class ExecutorHolder {
        private static ExecutorUtil executorUtil = new ExecutorUtil();
    }

    public static ExecutorUtil getInstance() {
        return ExecutorHolder.executorUtil;
    }

    /**
     * 线程池-缓存，可以无限制的创建线程，实现异步
     *
     * @return ThreadPoolExecutor
     */
    public ThreadPoolExecutor newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new CustomThreadFactory());
    }

    /**
     * 线程池-周期执行 使用scheduleAtFixedRate方法可以替代Timer
     *
     * @param corePoolSize 核心线程数
     * @return ScheduledThreadPoolExecutor
     */
    public ScheduledThreadPoolExecutor newScheduledThreadPool(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize, new CustomThreadFactory());
    }

    /**
     * 线程池-单一线程
     * 有且仅有一个工作线程执行任务
     * 所有任务按照指定顺序执行，即遵循队列的入队出队规则
     *
     * @return ThreadPoolExecutor
     */
    public ThreadPoolExecutor newSingleThreadExecutor() {
        return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new CustomThreadFactory());
    }

    /**
     * the factory to use when the executor creates a new thread
     */
    private class CustomThreadFactory implements ThreadFactory {
        private AtomicInteger count = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            String threadName = ExecutorUtil.class.getSimpleName() + count.addAndGet(1);
            thread.setName(threadName);
            return thread;
        }
    }
}
