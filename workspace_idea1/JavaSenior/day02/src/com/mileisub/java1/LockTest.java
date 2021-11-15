package com.mileisub.java1;

/*
* 解决现成的安全问题的方式三：Lock锁 --- JDK5.0新特性
*
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
