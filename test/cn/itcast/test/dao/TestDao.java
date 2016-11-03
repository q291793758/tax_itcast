package cn.itcast.test.dao;

import cn.itcast.test.entity.Person;

import java.io.Serializable;

public interface TestDao {
    void save(Person person);

    Person findByID(Serializable id);

}
