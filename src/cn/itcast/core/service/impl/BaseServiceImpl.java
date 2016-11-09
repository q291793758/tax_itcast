package cn.itcast.core.service.impl;

import cn.itcast.core.dao.BaseDao;
import cn.itcast.core.service.BaseService;
import cn.itcast.core.util.PageResult;
import cn.itcast.core.util.QueryHelper;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {

    private BaseDao<T> baseDao;

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void save(T entity) {
        baseDao.save(entity);
    }


    @Override
    public void update(T enetity) {
        baseDao.update(enetity);
    }

    @Override
    public void delete(Serializable id) {
        baseDao.delete(id);
    }


    @Override
    public T findObjectById(Serializable id) {
        return baseDao.findObjectById(id);
    }


    @Override
    public List<T> findObjects() {
        return baseDao.findObjects();
    }
    //根据条件查询
    public List<T> findObjects(String  hql, List<Object> parameters) {
        return  baseDao.findObjects(hql, parameters);
    }

    @Override
    public List<T> findObjects(QueryHelper queryHelper) {
        return baseDao.findObjects(queryHelper);
    }

    //分页
    public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize) {
        return baseDao.getPageResult(queryHelper,pageNo,pageSize);
    }
}
