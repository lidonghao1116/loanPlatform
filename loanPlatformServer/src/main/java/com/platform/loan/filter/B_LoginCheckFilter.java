/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.filter;

import com.alibaba.fastjson.JSONArray;
import com.platform.loan.constant.ResultCodeEnum;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.result.BaseResult;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author caogu.wyp
 * @version $Id: LoginCheckFilter.java, v 0.1 2018-05-17 下午11:57 caogu.wyp Exp $$
 */
@Component
@WebFilter(filterName = "B_LoginCheckFilter", urlPatterns = "/api/*")
public class B_LoginCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {



        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        System.out.println("===========B_LoginCheckFilter"+req.getRequestURI());


        if (needCheck(req)) {
            //非登录页面进行验证
            try {
                JwtUtil.getLoginSession(req);
            } catch (Exception e) {
                //验证失败，返回
                initFailedResult(res);
                return;
            }

        }
        filterChain.doFilter(req, res);

    }

    private boolean needCheck(HttpServletRequest req) {

        if (!req.getRequestURI().startsWith("/api")) {
            return false;
        }

        if (req.getRequestURI().contains("/api/borrower/login")) {
            return false;
        }

        if (req.getRequestURI().contains("/api/imagecode/")) {
            return false;
        }

        if (req.getRequestURI().contains("/api/sms/send")) {
            return false;
        }

        return true;

    }

    private void initFailedResult(HttpServletResponse httpServletResponse) throws IOException {

        httpServletResponse.setContentType("application/json;charset=utf-8");
        OutputStream out = httpServletResponse.getOutputStream();
        BaseResult result = new BaseResult();
        result.setResultMessage("登录验证失败！");
        result.setResultCode(ResultCodeEnum.TOKEN_VERIFY_FAILED.getCode());
        result.setSuccess("false");
        out.write(JSONArray.toJSONString(result).getBytes());
        out.flush();
        out.close();
    }

    @Override
    public void destroy() {

    }
}
