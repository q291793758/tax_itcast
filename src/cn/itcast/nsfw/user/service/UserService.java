package cn.itcast.nsfw.user.service;

import cn.itcast.core.exception.ServiceException;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.List;

public interface UserService {
    //新增
    public void save(User user);
    //更新
    public void update(User user);
    //根据id删除
    public void delete(Serializable id);
    //根据id查找
    public User findObjectById(Serializable id) throws ServiceException;
    //查找列表
    public List <User> findObjects() throws ServiceException;
    //导入EXCEL
    public void importExcel(File userExcel, String userExcelFileName);
    //导出Excel
    public void exportExcel(List<User> userList, ServletOutputStream outputStream);
    //校验 user.account唯一性
    public List<User> findUserByAccountAndId(String id, String account);

    public void saveAndRole(User user, String[] userRoleIds);

    public void updateUserAndRole(User user, String[] userRoleIds);

    public List<UserRole> getUserRolesByUserId(String id);

    public List<User> findUserByAccountAndPassword(String account,String password);
}
