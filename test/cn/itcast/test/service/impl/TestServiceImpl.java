package cn.itcast.test.service.impl;

import cn.itcast.test.dao.TestDao;
import cn.itcast.test.entity.Person;
import cn.itcast.test.service.TestService;

import javax.annotation.Resource;
import java.io.Serializable;


public class TestServiceImpl implements TestService {
    @Resource
    TestDao testDao;
    @Override
    public void say() {
        System.out.println("serviceTest success!");
    }

    @Override
    public void save(Person person) {
        testDao.save(person);
//        int i =1/0;  //测试回滚事务
    }

    @Override
    public Person findById(Serializable id) {
//        testDao.save(new Person("人员2"));
        return testDao.findByID(id);
    }

    public void setTestDao(TestDao testDao) {
        this.testDao = testDao;
    }
}
