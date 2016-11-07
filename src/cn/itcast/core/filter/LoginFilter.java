package cn.itcast.core.filter;

import cn.itcast.core.constant.Constant;
import cn.itcast.core.permission.PermissionCheck;
import cn.itcast.nsfw.user.entity.User;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute(Constant.USER);
        String uri = request.getRequestURI().replaceAll(request.getContextPath(), "");

        //判断当前地址是否是登录请求地址
        if (!uri.contains("sys/login")) {
            //非登录请求
            if (user != null) {
                //已经登录
                //判断是否访问纳税服务子系统
                if (uri.contains("/nsfw/")) {
                    //获取用户是否有权限

                    //获取当前应用的IOC容器
                    WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());

                    PermissionCheck pc = (PermissionCheck) context.getBean("permissionCheck");

                    if (pc.isAccessible(user, Constant.PRIVILEGE_NSFW)) {
                        //有权限放行
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        //没有纳税服务权限,跳转没有权限提示页面
                        response.sendRedirect(request.getContextPath()+"/sys/login_toNoPermissionUI.action");
                    }

                } else {

                    filterChain.doFilter(request, response);
                }
            } else {
                //没有登录,跳转登录页面
                response.sendRedirect(request.getContextPath() + "/sys/login_toLoginUI.action");
            }
        } else {
            //登录请求,放行
            filterChain.doFilter(request, response);

        }

    }

    @Override
    public void destroy() {

    }
}
