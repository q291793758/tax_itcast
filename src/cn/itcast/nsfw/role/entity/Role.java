package cn.itcast.nsfw.role.entity;

import java.io.Serializable;
import java.util.Set;

public class Role implements Serializable {
    private String roleId;                       //角色编号
    private String name;                         //角色名字
    private String state;                        //角色状态
    private Set<RolePrivilege> rolePrivileges;   //角色拥有的权限

    //角色状态
    private static String ROLE_STATE_VALIDE="1";
    private static String ROLE_STATE_INVALIDE="0";

    //构造
    public Role() {
    }

    public Role(String roleId) {
        this.roleId = roleId;
    }

    //全参构造
    public Role(String roleId, String name, String state, Set<RolePrivilege> rolePrivileges) {
        this.roleId = roleId;
        this.name = name;
        this.state = state;
        this.rolePrivileges = rolePrivileges;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<RolePrivilege> getRolePrivileges() {
        return rolePrivileges;
    }

    public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
        this.rolePrivileges = rolePrivileges;
    }
}
