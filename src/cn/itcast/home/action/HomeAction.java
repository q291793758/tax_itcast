package cn.itcast.home.action;

import cn.itcast.core.constant.Constant;
import cn.itcast.core.util.PageResult;
import cn.itcast.core.util.QueryHelper;
import cn.itcast.nsfw.complain.entity.Complain;
import cn.itcast.nsfw.complain.service.ComplainService;
import cn.itcast.nsfw.info.entity.Info;
import cn.itcast.nsfw.info.service.InfoService;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionContext;
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
    private InfoService infoService;

    private Info info;
    private Complain comp;

    private Map<String, Object> return_map;


    //跳转到首页
    public String execute() throws Exception {
        //加载信息列表
        Map<String, Object> contextMap = ActionContext.getContext().getContextMap();
        contextMap.put("infoTypeMap", Info.INFO_TYPE_MAP);
        QueryHelper queryHelper = new QueryHelper(Info.class, "i");
        queryHelper.addOrderByCondition("i.createTime", QueryHelper.ORDER_BY_DESC);
        PageResult pageResult = infoService.getPageResult(queryHelper, 1, 5);
        List infoList = pageResult.getItems();  //获取最新5条info
        contextMap.put("infoList", infoList);

        //加载我的投诉列表
        //获取当前登录user
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute(Constant.USER);
        if (user != null) {
            contextMap.put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
            QueryHelper queryHelper1 = new QueryHelper(Complain.class, "c");
            queryHelper1.addQueryCondition("c.compName = ?", user.getName());
            //投诉时间升序,投诉早的在前边
            queryHelper1.addOrderByCondition("c.compTime", QueryHelper.ORDER_BY_ASC);
            //投诉状态,降序排序  失效2-已受理1-未受理0
            queryHelper1.addOrderByCondition("c.state", QueryHelper.ORDER_BY_DESC);
            PageResult pageResult1 = complainService.getPageResult(queryHelper1, 1, 5);
            List complainList = pageResult1.getItems();  //获取最新5条info
            contextMap.put("complainList", complainList);

            return "home";
        }
        else {
            return null;
        }
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
                return_map = new HashMap<String, Object>();
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
    //查看公告信息
    public String infoViewUI() {
        Map<String, Object> contextMap = ActionContext.getContext().getContextMap();
        contextMap.put("infoTypeMap", Info.INFO_TYPE_MAP);
        //根据infoId查找info
        if (info.getInfoId() != null) {
             info = infoService.findObjectById(this.info.getInfoId());
        }
        return "infoViewUI";
    }

    public String complainViewUI() {
        ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
        if (comp != null) {
            comp=complainService.findObjectById(comp.getCompId());
        }
        return "complainViewUI";
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

    public InfoService getInfoService() {
        return this.infoService;
    }

    public void setInfoService(InfoService infoService) {
        this.infoService = infoService;
    }

    public Info getInfo() {
        return this.info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
