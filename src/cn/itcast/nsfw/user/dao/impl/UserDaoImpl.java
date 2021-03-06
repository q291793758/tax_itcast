package cn.itcast.nsfw.user.dao.impl;

import cn.itcast.core.dao.impl.BaseDaoImpl;
import cn.itcast.nsfw.user.dao.UserDao;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;
import org.hibernate.Query;

import java.io.Serializable;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public List<User> findUserByAccountAndId(String id, String account) {
        String hql = "FROM User WHERE account = ?";
        //1，判断id是否为空， 为空就是新增校验，不为空就是编辑校验带id查询
        if (id != null && id.length() > 0) {
            hql += "AND id !=?";
        }
        Query query = getSession().createQuery(hql);
        query.setParameter(0, account);
        if (id != null && id.length() > 0) {
            query.setParameter(1, id);
        }
        return query.list();
    }

    @Override
    public void saveUserRole(UserRole userRole) {
        if (userRole != null) {
            getHibernateTemplate().save(userRole);
        }
    }

    @Override
    public void deleteUserRoleByUserId(Serializable id) {
        //String sql="DELETE FROM user_role WHERE userId = ?";
        Query query = getSession().createQuery("DELETE FROM UserRole where userRoleId.userId=?");
        query.setParameter(0, id);
        query.executeUpdate();

    }

    @Override
    public List<UserRole> getUserRolesByUserId(String id) {
        Query query = getSession().createQuery("FROM UserRole where userRoleId.userId=?");
        query.setParameter(0, id);
        return query.list();

    }

    @Override
    public List<User> findUserByAccountAndPassword(String account, String password) {
        Query query = getSession().createQuery("FROM User where account=? AND password=?");
        query.setParameter(0,account).setParameter(1,password);
        return query.list();
    }
}
