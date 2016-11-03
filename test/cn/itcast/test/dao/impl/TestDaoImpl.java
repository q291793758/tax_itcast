package cn.itcast.test.dao.impl;

import cn.itcast.test.dao.TestDao;
import cn.itcast.test.entity.Person;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;

public class TestDaoImpl extends HibernateDaoSupport implements TestDao {


    @Override
    public void save(Person person) {
        getHibernateTemplate().save(person);
    }

    @Override
    public Person findByID(Serializable id) {
        return getHibernateTemplate().get(Person.class,id);

    }
}
