package cn.itcast.nsfw.role.entity;

import java.io.Serializable;

public class RolePrivilegeId implements Serializable {
    private  Role role;
    private String code;


    public RolePrivilegeId() {
    }

    public RolePrivilegeId(Role role, String code) {
        this.role = role;
        this.code = code;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolePrivilegeId that = (RolePrivilegeId) o;

        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        return code != null ? code.equals(that.code) : that.code == null;

    }

    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
