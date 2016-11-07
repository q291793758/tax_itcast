package cn.itcast.nsfw.role.service.impl;

import cn.itcast.core.service.impl.BaseServiceImpl;
import cn.itcast.nsfw.role.dao.RoleDao;
import cn.itcast.nsfw.role.entity.Role;
import cn.itcast.nsfw.role.service.RoleService;

public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        super.setBaseDao(roleDao);
        this.roleDao = roleDao;
    }

    @Override
    public void update(Role role) {
        //清空原有权限
        roleDao.deleteRolePrivilegeId(role.getRoleId());
        //保存角色及新权限
        roleDao.update(role);
    }




}
