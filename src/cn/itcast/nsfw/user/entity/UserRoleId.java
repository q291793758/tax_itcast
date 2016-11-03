package cn.itcast.nsfw.user.entity;

import cn.itcast.nsfw.role.entity.Role;

import java.io.Serializable;

public class UserRoleId  implements Serializable{
    private Role role;
    private String userId;


    public UserRoleId() {
    }

    public UserRoleId(Role role, String userId) {
        this.role = role;
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoleId that = (UserRoleId) o;

        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        return userId != null ? userId.equals(that.userId) : that.userId == null;

    }

    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}


