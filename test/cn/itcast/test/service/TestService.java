package cn.itcast.test.service;

import cn.itcast.test.entity.Person;

import java.io.Serializable;

public interface TestService {
    void say();
    //保存
    void save(Person person);
    //查找
    Person findById(Serializable id);
}
