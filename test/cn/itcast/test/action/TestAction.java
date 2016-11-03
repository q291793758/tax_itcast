package cn.itcast.test.action;

import cn.itcast.test.service.TestService;
import com.opensymphony.xwork2.ActionSupport;

import javax.annotation.Resource;

public class TestAction extends ActionSupport {
//    测试是Struts 和 Spring


    @Resource
    private TestService testService;
    @Override
    public String execute() throws Exception {
        testService.say();
        return "success";
    }
}
