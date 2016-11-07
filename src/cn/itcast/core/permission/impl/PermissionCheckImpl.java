package cn.itcast.core.permission.impl;

import cn.itcast.core.permission.PermissionCheck;
import cn.itcast.nsfw.role.entity.Role;
import cn.itcast.nsfw.role.entity.RolePrivilege;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;
import cn.itcast.nsfw.user.service.UserService;

import java.util.List;
import java.util.Set;

public class PermissionCheckImpl implements PermissionCheck {

    UserService userService;

    @Override
    public boolean isAccessible(User user, String code) {
        //1,获取用户所有角色
        List<UserRole> roleList = user.getUserRoles();

        //以防没有取到值
        if (roleList == null) {
            roleList = userService.getUserRolesByUserId(user.getId());
        }
        //2,根据用户角色对应权限对比
        if (roleList != null && roleList.size() > 0) {

            for (UserRole userRole : roleList) {
                Role role = userRole.getUserRoleId().getRole();
                Set<RolePrivilege> rps = role.getRolePrivileges();
                for (RolePrivilege rp : rps) {
                    String code1 = rp.getId().getCode();
                    if (code1.equals(code)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
