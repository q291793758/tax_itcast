package cn.itcast.nsfw.user.service;

import cn.itcast.core.service.BaseService;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.util.List;

public interface UserService extends BaseService<User> {

    //导入EXCEL
    public void importExcel(File userExcel, String userExcelFileName);
    //导出Excel
    public void exportExcel(List<User> userList, ServletOutputStream outputStream);
    //校验 user.account唯一性
    public List<User> findUserByAccountAndId(String id, String account);
    //保存用户和角色
    public void saveAndRole(User user, String[] userRoleIds);
    //更新用户和角色
    public void updateUserAndRole(User user, String[] userRoleIds);
    //获得用户所有角色
    public List<UserRole> getUserRolesByUserId(String id);
    //根据 账号密码 查找用户
    public List<User> findUserByAccountAndPassword(String account,String password);
}
