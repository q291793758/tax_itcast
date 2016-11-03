import org.junit.Test;

public class MyThreadDemo implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 25; i++) {
            System.out.println(Thread.currentThread().getName() + "正在运行！" + i);
            if (i == 10) {
                try {
                    new Thread(new MyThreadDemo(), "大牛").join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test1() {
        new Thread(new MyThreadDemo(),"刘昭").start();
        new Thread(new MyThreadDemo(),"章泽天").start();
    }


}
