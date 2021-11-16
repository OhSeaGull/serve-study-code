package com.mileisub.java2;

/*
* 例子：两个线程交替打印
*
* 1.wait(),notify(),notifuAll()三个方法必须使用在同步代码块或同步方法中
* 2.wait(),notify(),notifuAll()三个方法必须使用在同步代码块或同步方法中的同步监视器，
*  否则，会出现IllegalMonitorStateException异常
* 3.wait(),notify(),notifuAll()三个方法是定义在java.lang.Object类中（推理：任何一个
*   对象都能充当同步监视器，所以：有③）
*
* 面试题：sleep() 和 wait()的异同？
* 1.相同点：都使得线程阻塞
* 2.不同：1）两个方法生命的位置不同：Thread类中声明sleep(), Object类中声明wait()
*        2）调用的要求不同：sleep()可以在任何的场景下使用。wait()必须使用在同步代码块或同步方法中
*        3）使得调用sleep()的线程进入阻塞状态，但是并不释放锁。使得调用sleep()的线程进入阻塞状态，并释放同步监视器

* */

class  Number implements Runnable{
    private int number = 1;
    private  Object obj = new Object();
    @Override
    public void run() {

        while (true){

            synchronized (obj) {
                //通过其他线程唤醒一个被wait的线程
                notify();
                if (number <= 100){

                    try {
                        //是的调用sleep()的线程进入阻塞状态，但是并不释放锁
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ':' + number);
                    number ++;

                    try {
                        //并释放同步监视器
                        //使得调用如下wait()方法的线程阻塞
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    break;
                }
            }
        }
    }
}


public class CommunicationTest {
    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
    }



}
