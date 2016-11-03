package cn.itcast.nsfw.role.entity;

public class RolePrivilege {
    private RolePrivilegeId id;

    public RolePrivilege(RolePrivilegeId id) {
        this.id = id;
    }

    public RolePrivilege() {
    }


    public RolePrivilegeId getId() {
        return id;
    }

    public void setId(RolePrivilegeId id) {
        this.id = id;
    }
}
