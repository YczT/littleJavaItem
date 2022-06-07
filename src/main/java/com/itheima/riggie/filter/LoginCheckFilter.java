package com.itheima.riggie.filter;

import com.alibaba.fastjson.JSON;
import com.itheima.riggie.common.BaseContext;
import com.itheima.riggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否已经登录
 */

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        1.获取本次请求的URI
        String requestURI = httpServletRequest.getRequestURI();
        log.info("拦截到请求: {}",requestURI);
//        2.判断本次请求是否需要处理
//        定义不需要处理请求的路径
        String[] urls = new String[]{
                "/employee/login", //登录界面
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };
        boolean check = check(urls, requestURI);
//        3.不需要处理，直接放行
        if (check) {
            log.info("本次请求{}不需要处理",requestURI);
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
//        4-1.需要处理，判断登录状态，若已登录，直接放行
        if (httpServletRequest.getSession().getAttribute("employee") != null) {
            log.info("用户已登录，用户id为:{}",httpServletRequest.getSession().getAttribute("employee"));
//            获取ThreadLocal中的id(也就是当前登录的用户的id)
            Long empId = (Long) httpServletRequest.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
//        4-2.需要处理，判断登录状态，若已登录，直接放行
        if (httpServletRequest.getSession().getAttribute("user") != null) {
            log.info("用户已登录，用户id为:{}",httpServletRequest.getSession().getAttribute("user"));
//            获取ThreadLocal中的id(也就是当前登录的用户的id)
            Long userId = (Long) httpServletRequest.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);

            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
//        5.未登录，则返回登录页面，通过输出流方式向客户端页面响应数据
        log.info("用户未登录");
        httpServletResponse.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     *
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
