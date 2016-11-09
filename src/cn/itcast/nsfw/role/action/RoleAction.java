package cn.itcast.nsfw.role.action;

import cn.itcast.core.action.BaseAction;
import cn.itcast.core.constant.Constant;
import cn.itcast.core.exception.SysException;
import cn.itcast.core.util.QueryHelper;
import cn.itcast.nsfw.role.entity.Role;
import cn.itcast.nsfw.role.entity.RolePrivilege;
import cn.itcast.nsfw.role.entity.RolePrivilegeId;
import cn.itcast.nsfw.role.service.RoleService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.List;

public class RoleAction extends BaseAction {

    private RoleService roleService;
    private Role role;
    private List<Role> roleList;
    private String[] privilegeIds;


    //=================FUNCTION START=======================//
    //列表页面
    public String listUI() throws SysException, UnsupportedEncodingException {
        //加载权限集合
        ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
        QueryHelper queryHelper = new QueryHelper(Role.class, "r");

        if (searchString != null) {
            searchString = URLDecoder.decode(searchString, "utf-8");
            if (StringUtils.isNotBlank(searchString)) {
                queryHelper.addQueryCondition("r.name like ?", "%" + searchString + "%");
            }
        }
        pageResult = roleService.getPageResult(queryHelper, getPageNo(), getPageSize());

        return "listUI";
    }

    //跳转到新增页面
    public String addUI() {
        //加载权限集合
        ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);

        return "addUI";
    }

    //保存新增
    public String add() {
        try {
            HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
            //角色权限处理
            if (privilegeIds != null) {
                for (String privilegeId : privilegeIds) {

                    set.add(new RolePrivilege(new RolePrivilegeId(role, privilegeId)));
                }
                role.setRolePrivileges(set);
            }
            roleService.save(role);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }

    //跳转到编辑页面
    public String editUI() throws SysException {
        ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
        if (role != null && role.getRoleId() != null) {
            //加载权限集合
            role = roleService.findObjectById(role.getRoleId());
            //处理权限回显

            if ( role.getRolePrivileges() != null) {
                privilegeIds = new String[ role.getRolePrivileges().size()];
                int i=0;
                for (RolePrivilege rp : role.getRolePrivileges()) {
                    privilegeIds[i++]=rp.getId().getCode();
                }
            }
        }
        return "editUI";
    }

    //保存编辑
    public String edit() {
        try {
            if (privilegeIds != null) {

                HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
                //角色权限处理
                for (String privilegeId : privilegeIds) {
                    set.add(new RolePrivilege(new RolePrivilegeId(role, privilegeId)));
                }
                role.setRolePrivileges(set);
            }
            roleService.update(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }

    //删除
    public String delete() {
        if (role != null && role.getRoleId() != null) {
            roleService.delete(role.getRoleId());
        }
        return "list";
    }

    //批量删除
    public String deleteSelected() {
        if (selectedRow != null) {
            for (String id : selectedRow) {
                roleService.delete(id);
            }
        }
        return "list";
    }


    //=====================END=====================//


    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String[] getPrivilegeIds() {
        return privilegeIds;
    }

    public void setPrivilegeIds(String[] privilegeIds) {
        this.privilegeIds = privilegeIds;
    }
}
