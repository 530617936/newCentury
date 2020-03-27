package com.newCentury.web.filter;

import com.alibaba.fastjson.JSONObject;
import com.newCentury.web.Enum.ResponseEnum;
import com.newCentury.web.util.Response;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @ClassName TokenFilter
 * @Description: TODO
 * @Author: 53061
 * @Date:2020/3/26
 */
@Order(1)
@WebFilter(filterName = "tokenFilter",urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest  = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;

        String requestURI = httpServletRequest.getRequestURI();
        String[] notFilter = {"/test1/login","/test1/getUserInfo"};
        if (Arrays.asList(notFilter).contains(requestURI)){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            String authorization = httpServletRequest.getHeader("Authorization");
            if (StringUtils.isEmpty(authorization)){
                httpServletResponse.setContentType("application/json;charset=utf-8");
                httpServletResponse.setCharacterEncoding("utf-8");
                String jsonString = JSONObject.toJSONString(Response.error(ResponseEnum.Unauthorized));
                PrintWriter writer = servletResponse.getWriter();
                writer.write(jsonString);
                return;
            }else {
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
