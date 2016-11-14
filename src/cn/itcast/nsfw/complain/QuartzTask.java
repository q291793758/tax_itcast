package cn.itcast.nsfw.complain;

import java.util.Date;

public class QuartzTask {
    public void doSimpleTriggerTask() {
        System.out.println(new Date().toLocaleString()+"========doSimpleTriggerTask Run========");
    }
    public void doCronTriggerTask() {
        System.out.println(new Date().toLocaleString()+"========doCronTriggerTask Run========");
    }
}
