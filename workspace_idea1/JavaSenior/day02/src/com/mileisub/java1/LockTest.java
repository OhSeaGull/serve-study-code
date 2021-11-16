package com.mileisub.java1;

/*
* 解决现成的安全问题的方式三：Lock锁 --- JDK5.0新特性
*
* 1.面试题：synchronized 与 Lock 的异同
*   相同：二者都可以解决线程安全问题
*   不同：symchronized 机制在执行完相应的同步代码块后自动地释放监视器
*        Lock需要手动的启动同步（lock()），同时结束同步也需要手动的实现（unlock()）
*
* */

import java.util.concurrent.locks.ReentrantLock;

class Window implements Runnable{

    private int ticket = 100;
    //1.创建一个对象
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){

            //2.调用锁方法：lock()
            lock.lock();

            try {
                if (ticket > 0) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Thread.currentThread().getName() + ':售票，票号为' + ticket");
                    ticket--;
                } else {
                    break;
                }
            }finally {
                //3.调用解锁方法
                lock.unlock();
            }
        }
    }
}

public class LockTest{

}
