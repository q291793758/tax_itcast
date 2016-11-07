package cn.itcast.core.permission;

import cn.itcast.nsfw.user.entity.User;


/**
 * The interface Privilege Permission check.
 * 模块权限检查
 */
public interface PermissionCheck {
    /**
     * Is accessible boolean.
     *
     * @param user 用户
     * @param code 权限编码
     * @return the boolean 是否有权限
     */
    public boolean isAccessible(User user,String code);
}
