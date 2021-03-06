package com.platform.loan.filter;

import com.platform.loan.constant.CommonConstants;
import com.platform.loan.util.LoanLogUtil;

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

        LoanLogUtil.getLogger(CrosFilter.class).info(
            "===========A_CrosFilter" + request.getRequestURI() + ",request.getMethod()="
                    + request.getMethod());

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", sb.toString());
        response.setHeader("Access-Control-Expose-Headers", sb.toString());
        response.setHeader("Access-Control-Max-Age", "3600");

        if ("OPTIONS".equals(request.getMethod())) {

            response.setStatus(HttpServletResponse.SC_OK);
        } else {

            filterChain.doFilter(req, res);
        }

    }

    @Override
    public void destroy() {

    }
}
