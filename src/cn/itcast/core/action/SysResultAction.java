package cn.itcast.core.action;

import com.opensymphony.xwork2.ActionInvocation;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SysResultAction extends StrutsResultSupport {


    @Override
    protected void doExecute(String s, ActionInvocation actionInvocation) throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        BaseAction action = (BaseAction) actionInvocation.getAction();


        //do sth 已获取request，response，当前执行的action ,由于捕获异常进入整理，可以进行页面跳转
        response.sendRedirect("/index.jsp");
        System.out.println("进入了 自定义结果类型：SysResultAction");


    }
}
