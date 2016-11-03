package cn.itcast.nsfw.role.dao.impl;

import cn.itcast.core.dao.impl.BaseDaoImpl;
import cn.itcast.nsfw.role.dao.RoleDao;
import cn.itcast.nsfw.role.entity.Role;
import org.hibernate.Query;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao  {


    @Override
    //删除角色对应权限
    public void deleteRolePrivilegeId(String roleId) {
        String hql="DELETE FROM RolePrivilege WHERE id.role.roleId=?";
        Query query = getSession().createQuery(hql);
        query.setParameter(0,roleId);
        query.executeUpdate();

    }
}
