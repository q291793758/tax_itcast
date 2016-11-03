package cn.itcast.test;

import cn.itcast.test.entity.Person;
import cn.itcast.test.service.TestService;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMerge {
    private ApplicationContext ac =new ClassPathXmlApplicationContext("applicationContext.xml");


    TestService testService;

    public void setTestService(TestService testService) {
        this.testService = testService;
    }
    @Test
    public void testSpring() {
        TestService ts = (TestService) ac.getBean("testService");
        ts.say();
    }
    //

    @Test
    public void testHibernate() {

        SessionFactory sf = (SessionFactory) ac.getBean("sessionFactory");
        Session session = sf.openSession();
        Transaction ts = session.beginTransaction();
        session.save(new Person("小韩"));

        ts.commit();
        sf.close();

    }

    @Test
    public void  testServiceAndDao (){
        TestService testService = (TestService) ac.getBean("testService");
        testService.save(new Person("人员1"));
    };

    @Test
    public void testTransAction() {  //测试只读
        TestService testService = (TestService) ac.getBean("testService");
        Person person = testService.findById("ff8080815819b1c9015819b1ce2c0000");
        System.out.println(person.getName());

    }

    @Test
    public void testRollback() {
        TestService testService = (TestService) ac.getBean("testService");
        testService.save(new Person("人员3"));
    }
}
