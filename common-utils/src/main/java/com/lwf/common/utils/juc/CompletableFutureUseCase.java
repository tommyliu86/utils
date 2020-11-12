package com.lwf.common.utils.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-12 13:59
 */
public class CompletableFutureUseCase {
    public static void main(String[] args) {
        List<CompletableFuture> completableFutureList=new ArrayList<>();
        //进行 异步任务创建
        for (int i = 0; i < 100; i++) {
            Integer index=i;
            completableFutureList.add(CompletableFuture.runAsync(()->{
                System.out.println(index);
            }));
        }
        CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[completableFutureList.size()])).whenComplete(
                (o, e) -> {
                    if (e != null) {

                        System.out.println("run is error"+e);

                    } else {

                        System.out.println("run is ok");
                    }

                }

        ).join();//此处注意需要使用join来把最后一个异步步骤合并到主线程上来，才能起到同步的作用。
    }
}
