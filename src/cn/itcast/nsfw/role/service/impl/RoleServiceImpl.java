package cn.itcast.nsfw.role.service.impl;

import cn.itcast.core.exception.ServiceException;
import cn.itcast.nsfw.role.dao.RoleDao;
import cn.itcast.nsfw.role.entity.Role;
import cn.itcast.nsfw.role.service.RoleService;

import java.io.Serializable;
import java.util.List;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public void update(Role role) {
        //清空原有权限
        roleDao.deleteRolePrivilegeId(role.getRoleId());
        //保存角色及新权限
        roleDao.update(role);
    }

    @Override
    public void delete(Serializable id) {
        roleDao.delete(id);
    }

    @Override
    public Role findObjectById(Serializable id) throws ServiceException {
        return roleDao.findObjectById(id);
    }

    @Override
    public List<Role> findObjects() throws ServiceException {
        return roleDao.findObjects();
    }


}
