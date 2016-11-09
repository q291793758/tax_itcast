package cn.itcast.nsfw.user.action;

import cn.itcast.core.action.BaseAction;
import cn.itcast.core.exception.ActionException;
import cn.itcast.core.exception.ServiceException;
import cn.itcast.core.exception.SysException;
import cn.itcast.core.util.QueryHelper;
import cn.itcast.nsfw.role.service.RoleService;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;
import cn.itcast.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;

public class UserAction extends BaseAction {

    private UserService userService;
    private RoleService roleService;
    private User user;
    private String[] userRoleIds; //角色权限id

    //文件上传三要素
    private File headImg;
    private String headImgContentType;
    private String headImgFileName;

    //导入导入Excel
    private File userExcel;
    private String userExcelContentType;
    private String userExcelFileName;


    //=================FUNCTION START=======================//
    //列表页面
    public String listUI() throws SysException {
        try {
            QueryHelper queryHelper = new QueryHelper(User.class, "u");

            if (searchString != null) {
                searchString = URLDecoder.decode(searchString, "utf-8");
                if (StringUtils.isNotBlank(searchString)) {
                    queryHelper.addQueryCondition("u.name like ?", "%" + searchString + "%");
                }
            }
            pageResult = userService.getPageResult(queryHelper, getPageNo(), getPageSize());
        } catch (Exception e) {
            throw new ActionException(e.getMessage());
        }
        return "listUI";
    }

    //跳转到新增页面
    public String addUI() throws ServiceException {
        //查找用户的角色 显示到添加页面
        ActionContext.getContext().getContextMap().put("roleList", roleService.findObjects());

        return "addUI";
    }

    //保存新增
    public String add() {
        try {
            if (user != null) {
                //处理头像
                if (headImg != null) {
                    //获取保存路径的绝对地址
                    String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
                    String fileName = UUID.randomUUID().toString().replaceAll("-", "") + headImgFileName.substring(headImgFileName.lastIndexOf("."));
                    //复制文件
                    FileUtils.copyFile(headImg, new File(filePath, fileName));

                    //2、设置用户头像路径
                    user.setHeadImg("user/" + fileName);

                }
                userService.saveAndRole(user, userRoleIds);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "list";
    }

    //跳转到编辑页面
    public String editUI() throws SysException {
        ActionContext.getContext().getContextMap().put("roleList", roleService.findObjects());
        if (user != null && user.getId() != null) {
            //查找用户的角色 显示到修改用户页面
            user = userService.findObjectById(user.getId());
            //设置用户角色 用来回显
            List<UserRole> list = userService.getUserRolesByUserId(user.getId());
            if (list != null && list.size() > 0) {
                //查找出来的RoleId保存在 userRoleIds中
                userRoleIds = new String[list.size()];
                int i = 0;
                for (UserRole userRole : list) {
                    userRoleIds[i++] = userRole.getUserRoleId().getRole().getRoleId();
                }
            }

        }
        return "editUI";
    }

    //保存编辑
    public String edit() throws Exception {

        try {
            if(user != null){
                //处理头像
                if(headImg != null){
                    //1、保存头像到upload/user
                    //获取保存路径的绝对地址
                    String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
                    String fileName = UUID.randomUUID().toString().replaceAll("-", "") + headImgFileName.substring(headImgFileName.lastIndexOf("."));
                    //复制文件
                    FileUtils.copyFile(headImg, new File(filePath, fileName));

                    //2、设置用户头像路径
                    user.setHeadImg("user/" + fileName);
                }

                userService.updateUserAndRole(user, userRoleIds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }

    //删除
    public String delete() {
        if (user != null && user.getId() != null) {
            userService.delete(user.getId());
        }
        return "list";
    }

    //批量删除
    public String deleteSelected() {
        if (selectedRow != null) {
            for (String id : selectedRow) {
                userService.delete(id);
            }
        }
        return "list";
    }

    //导出用户列表
    public void exportExcel() {
        try {
            //1、查找用户列表
//            userService.findObjects()
            //2、导出
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("application/x-execl");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户列表.xls".getBytes(), "ISO-8859-1"));
            ServletOutputStream outputStream = response.getOutputStream();
            userService.exportExcel(userService.findObjects(), outputStream);
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //导入用户列表
    public String importExcel() {
        //1、获取excel文件
        if (userExcel != null) {
            //是否是excel
            if (userExcelFileName.matches("^.+\\.(?i)((xls)|.+\\.(?i)(xlsx))$")) {
                //2、导入
                userService.importExcel(userExcel, userExcelFileName);
            }
        }
        return "list";
    }

    //账号唯一性校验
    public String verifyAccount() throws Exception {

        //获得参数 account
        if (user.getAccount() != null) {
            //查询数据库中account是否唯一
            List<User> list = userService.findUserByAccountAndId(user.getId(), user.getAccount());
            String strResult = "true";
            if (list != null && list.size() > 0) {
                //说明该账号已经存在
                strResult = "false";
            }
            //反馈客户端字符串
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html");
            try {
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(strResult.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return null;
    }


    //=====================END=====================//


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public File getHeadImg() {
        return headImg;
    }

    public void setHeadImg(File headImg) {
        this.headImg = headImg;
    }

    public String getHeadImgContentType() {
        return headImgContentType;
    }

    public void setHeadImgContentType(String headImgContentType) {
        this.headImgContentType = headImgContentType;
    }

    public String getHeadImgFileName() {
        return headImgFileName;
    }

    public void setHeadImgFileName(String headImgFileName) {
        this.headImgFileName = headImgFileName;
    }

    public File getUserExcel() {
        return userExcel;
    }

    public void setUserExcel(File userExcel) {
        this.userExcel = userExcel;
    }

    public String getUserExcelContentType() {
        return userExcelContentType;
    }

    public void setUserExcelContentType(String userExcelContentType) {
        this.userExcelContentType = userExcelContentType;
    }

    public String getUserExcelFileName() {
        return userExcelFileName;
    }

    public void setUserExcelFileName(String userExcelFileName) {
        this.userExcelFileName = userExcelFileName;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public String[] getUserRoleIds() {
        return userRoleIds;
    }

    public void setUserRoleIds(String[] userRoleIds) {
        this.userRoleIds = userRoleIds;
    }

}
