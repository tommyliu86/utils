package com.lwf.common.utils.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-24 09:38
 */
public class ThreadPoolExecutorUseCase {
    public class ShardJdbcExecutorService extends java.util.concurrent.ThreadPoolExecutor {
        public ShardJdbcExecutorService() {
            super(Runtime.getRuntime().availableProcessors() * 2, Runtime.getRuntime().availableProcessors() * 30, 10L, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(1000), Executors.defaultThreadFactory());
        }
        public ShardJdbcExecutorService(int corePoolSize, int maximumPoolSize, long keepAliveTime,int queueLength) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueLength), Executors.defaultThreadFactory());
        }

    }

}
