package java;

/*
* 创建多线程的方式二：实现Runnable接口
* 1.创建一个实现了Runnable接口的类
* 2.实现类去实现Runnable中的抽象方法
* 3.创建实现类的对象
* 4.将此类对象作为参数传递到Thread类的构造器中，创建Thread类的对象
* 5.通过Thread类的对象调用start()
*
*比较创建线程的优劣
* 开发中：优先选择：实现Runnable接口的方式
* 原因：1. 实现的方式没有类的单继承性的局限性
*       2. 实现的方式更适合来处理多个线程共有数据的情况
* 理解：
*
*联系：public class Thread implements Runnable
* 相同点：两种方式都需要重写run(),将线程要执行的逻辑声明在run()中
*
* */
//1.创建一个实现了Runnable接口的类
class  MThread implements Runnable{

    //2.实现类去实现Runnable中的抽象方法
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0){
                System.out.println(i);
            }
        }
    }
}

public class ThreadTest1 {
    public static void main(String[] args) {
        //3.创建实现类的对象
        MThread mthread = new MThread();
        //4.将此类对象作为参数传递到Thread类的构造器中，创建Thread类的对象
        Thread t1 = new Thread(mthread);
        t1.setName("线程1");
        //5.通过Thread类的对象调用start():① 启动线程 ② 调用当前线程的run()-->调用了Runnable类型的target中的run()
        t1.start();

        Thread t2 = new Thread(mthread);
        t2.setName("线程2");
        t2.start();
    }


}
