package cn.itcast.home.action;

import cn.itcast.core.util.QueryHelper;
import cn.itcast.nsfw.complain.entity.Complain;
import cn.itcast.nsfw.complain.service.ComplainService;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeAction extends ActionSupport {

    private UserService userService;
    private ComplainService complainService;
    private Complain comp;

    private Map<String,Object> return_map;


    //跳转到首页
    public String execute() throws Exception {
        return "home";
    }

    //跳转我要投诉页面(写在公共主页,不需要特殊权限)
    public String complainAddUI() {

        return "complainAddUI";
    }


    //json1根据dept返回json类型用户列表(不依赖SSH框架,引入commons-beanutils-1.8.0.jar, ezmorph-1.0.6.jar, json-lib-2.3-jdk12.jar 三个jar包,)
    public void getUserJson1() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            //1,获取部门
            String dept = request.getParameter("dept");
            if (StringUtils.isNotBlank(dept)) {
                //2,根据部门获取用户列表
                QueryHelper queryHelper = new QueryHelper(User.class, "u");
                queryHelper.addQueryCondition("u.dept = ?", dept);
                List<User> userList = userService.findObjects(queryHelper);
                JSONObject jso = new JSONObject();
                jso.put("msg", "success");
                jso.accumulate("userList", userList);
                //3,输出用户列表(json格式字符串)
                response.setContentType("text/json");
                ServletOutputStream out = response.getOutputStream();
                out.write(jso.toString().getBytes("utf-8"));
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //json2 依赖SSH框架中根据dept返回json类型用户列表
    public String getUserJson2() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            //1,获取部门
            String dept = request.getParameter("dept");
            if (StringUtils.isNotBlank(dept)) {
                //2,根据部门获取用户列表
                QueryHelper queryHelper = new QueryHelper(User.class, "u");
                queryHelper.addQueryCondition("u.dept = ?", dept);
                return_map=new HashMap<String,Object>();
                return_map.put("msg", "success");
                return_map.put("userList", userService.findObjects(queryHelper));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    //保存投诉信息
    public void complainAdd() {
        try {
            if (comp != null) {
                //设置默认投诉状态为待受理
                comp.setState(Complain.COMPLAIN_STATE_UNDONE);
                //设置投诉时间为当前时间
                comp.setCompTime(new Timestamp(new Date().getTime()));
                //保存投诉信息
                complainService.save(comp);


                //输出投诉保存结果
                HttpServletResponse response = ServletActionContext
                        .getResponse();
                response.setContentType("text/html");
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write("success".getBytes("utf-8"));
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*===============GET SET===================*/
    public UserService getUserService() {
        return this.userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Complain getComp() {
        return this.comp;
    }

    public void setComp(Complain comp) {
        this.comp = comp;
    }

    public ComplainService getComplainService() {
        return this.complainService;
    }

    public void setComplainService(ComplainService complainService) {
        this.complainService = complainService;
    }

    public Map<String, Object> getReturn_map() {
        return return_map;
    }

    public void setReturn_map(Map<String, Object> returnMap) {
        return_map = returnMap;
    }
}
