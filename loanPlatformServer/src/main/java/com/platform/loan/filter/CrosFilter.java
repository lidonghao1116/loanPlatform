package com.platform.loan.filter;

import com.platform.loan.constant.CommonConstants;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  允许跨域请求设置
 * @author caogu.wyp
 * @version $Id: AccessTokenFilter.java, v 0.1 2018-05-09 下午6:05 caogu.wyp Exp $$
 */
@Component
@WebFilter(filterName = "crosFilter", urlPatterns = "/api/*")
public class CrosFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
                                                                                          throws IOException,
                                                                                          ServletException {
        String all = CommonConstants.IMAGE_CODE_HEADER_KEY + ","
                     + CommonConstants.AUTHORIZATION_HEARDER_KEY + ",*";

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", all);
        response.setHeader("Access-Control-Expose-Headers", all);
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
