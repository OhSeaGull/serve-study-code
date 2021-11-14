package com.mileisub.java;

import static java.lang.Thread.*;

/*
* 例子：常见三个窗口买票，总票数为100张，使用实现Runnable接口的方式
*
* 1.问题：买票过程中，出现了冲票、错票---》出现了线程安全的问题
* 2.问题出现的原因：当某个线程操作ticket的过程中，尚未操作完成时，其他线程参与进来，也操作ticket
* 3.解决方案：当一个线程a在操作ticket的时候，其他线程不能参与进来。直到线程a操作完ticket，其他线
*           程才可以开始操作ticket。这种情况即使线程a出现了阻塞，也不能被改变。
* 4.
*
*  方式一：同步代码
*
*   synchronized(同步监视器){
*       //需要被同步的代码
*   }
*   说明：1.操作共享数据的代码，即为需要内同步的代码
*        2.共享数据：多个线程共同共同操作的变量.比如：ticket就是共享数据
*        3.同步监视器，俗称：锁。任何一个类的对象，都可以充当锁。
*           要求：多给线程必须要公用同一把锁
*
*  5.同步的方式，解决了现成的安全问题。---好处
*   操作同步代码时，只能有一个线程参与，其他线程等待。相当于是一个单线程的过程，效率低。
*
* */
class Window1 implements Runnable{

    private int ticket = 100;
    //因为线程共用了同一个 w对象，自然就只有一个属性obj了。
    Object obj = new Object();

    @Override
    public void run() {
        while(true){
            synchronized (obj) {
                if (ticket > 0) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":卖票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}

class WindowTest {
    public static void main(String[] args) {
        Window1 w = new Window1();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t2.setName("窗口2");
        t1.setName("窗口1");
        t3.setName("窗口3");

        t2.start();
        t1.start();
        t3.start();
    }


}

public class WindowTest1 {
}
