package com.mileisub.java;

/*
* 1. 同步方法任然涉及到同步监视器，只是不需要我们显示的声明
* 2. 非静态的同步方法，同步监视器是：this
*    静态的同步方法，同步监视器是：当前类本身
*
* */

class Window3 implements Runnable{

    private int ticket = 100;
    //因为线程共用了同一个 w对象，自然就只有一个属性obj了。
    Object obj = new Object();

    @Override
    public synchronized void run() {
        show();
    }

    private void show(){//同步监视器:this
        while (true) {
            if (ticket > 0) {
                try {
                    Thread.sleep(100);
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

public class WindowTest3 {
    public static void main(String[] args) {
        Window2 w = new Window2();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t2.setName("窗口2");
        t1.setName("窗口1");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
