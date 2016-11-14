package cn.itcast.timerTask;

import org.junit.Test;

import java.util.Timer;

public class MyTimerTaskTest {

    @Test
    public void testRun() throws Exception {
        Timer timer = new Timer();
        timer.schedule(new MyTimerTask(),2000,5000);
    }


    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new MyTimerTask(),2000,5000);
    }
}