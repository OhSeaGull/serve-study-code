package com.mileisub.java2;

/*
* 创建线程的方式四：创建线程池
*
* 优点：
* 1.提高响应速度（减少了创建线程的时间）
* 2.降低了资源消耗（重复利用线程池中的线程，不需要每次都创建）
* 3.便于线程管理
*       corePoolSize：核心池的大小
*       maximumPoolSize：最大线程
*       keepAliveTime：线程没有任务是最多保持多长时间后会终止
*
* 面试题：创建多线程有几种方式? 四种
* */


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class NumberThread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 ==0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

public class ThreadPool {

    public static void main(String[] args) {
        //1.造线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;

        //设置线程池的属性
//        System.out.println(service.getClass());
//        service1.setCorePoolSize(15);

        //2.执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
        service.execute(new NumberThread());//适用于Runnable
        service.execute(new NumberThread());//适用于Runnable

//        service.submit(Callable callable);//适用于Callable
        //3.关闭线程池
        service.shutdown();
    }

}
