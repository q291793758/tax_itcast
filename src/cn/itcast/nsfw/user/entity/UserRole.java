package cn.itcast.nsfw.user.entity;

import java.io.Serializable;

public class UserRole implements Serializable {
    private UserRoleId userRoleId;


    public UserRole() {
    }

    public UserRole(UserRoleId userRoleId) {
        this.userRoleId = userRoleId;
    }

    public UserRoleId getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(UserRoleId userRoleId) {
        this.userRoleId = userRoleId;
    }
}
