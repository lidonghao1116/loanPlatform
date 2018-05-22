package com.platform.loan.filter;

import com.platform.loan.constant.CommonConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  允许跨域请求设置
 * @author caogu.wyp
 * @version $Id: AccessTokenFilter.java, v 0.1 2018-05-09 下午6:05 caogu.wyp Exp $$
 */
public class CrosFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
                                                                                          throws IOException,
                                                                                          ServletException {
        StringBuilder sb = new StringBuilder();
        sb.append(CommonConstants.IMAGE_CODE_HEADER_KEY);
        sb.append(",");
        sb.append(CommonConstants.AUTHORIZATION_HEARDER_KEY);
        sb.append(",");
        sb.append(CommonConstants.CONTENT_TYPE_HEADER_KEY);

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        System.out.println("===========A_CrosFilter"+((HttpServletRequest) req).getRequestURI()+",request.getMethod()="+request.getMethod());

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", sb.toString());
        response.setHeader("Access-Control-Expose-Headers", sb.toString());
        response.setHeader("Access-Control-Max-Age", "3600");

        if (request.getMethod().equals("OPTIONS")) {

            response.setStatus(HttpServletResponse.SC_OK);
        } else {

            filterChain.doFilter(req, res);
        }

    }

    @Override
    public void destroy() {

    }
}
