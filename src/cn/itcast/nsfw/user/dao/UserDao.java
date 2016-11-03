package cn.itcast.nsfw.user.dao;

import cn.itcast.core.dao.BaseDao;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;

import java.util.List;

public interface UserDao extends BaseDao<User> {
    //校验user.account唯一性
    public List<User> findUserByAccountAndId(String id, String account);

    public void saveUserRole(UserRole userRole);

    public void deleteUserRoleByUserId(String id);

    public List<UserRole> getUserRolesByUserId(String id);
}
