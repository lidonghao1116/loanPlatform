/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.filter;

import com.alibaba.fastjson.JSONArray;
import com.platform.loan.constant.ResultCodeEnum;
import com.platform.loan.jwt.JwtUtil;
import com.platform.loan.pojo.result.BaseResult;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * @author caogu.wyp
 * @version $Id: LoginCheckFilter.java, v 0.1 2018-05-17 下午11:57 caogu.wyp Exp $$
 */
public class LoginCheckFilter implements Filter {

    private static Set<String> noNeedCheckUrl = new HashSet<String>();

    static {
        //不需要登陆验证的url
        noNeedCheckUrl.add("/api/borrower/login");
        noNeedCheckUrl.add("/api/imagecode/");
        noNeedCheckUrl.add("/api/sms/send");
        noNeedCheckUrl.add("/api/manager/order/grab");
        noNeedCheckUrl.add("/api/imagecode/generate");
        //======//
        noNeedCheckUrl.add("/api/manager/orders");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        System.out.println("===========B_LoginCheckFilter" + req.getRequestURI()
                           + ",request.getMethod()=" + req.getMethod());

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
        if (noNeedCheckUrl.contains(req.getRequestURI())) {
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
