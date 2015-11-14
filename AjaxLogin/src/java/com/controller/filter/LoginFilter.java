
/* 
 whatsapp: 1-809-978-5552
 email: yoloprogramo22@gmail.com
 kakaotalk: JoanVasquez
 */
package com.controller.filter;

import com.models.login.LoginModel;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest rq = (HttpServletRequest) request;
        HttpSession session = rq.getSession();
        LoginModel login;
        if (session != null) {
            login = (LoginModel) rq.getSession().getAttribute("user");

            if (login != null) {

                chain.doFilter(request, response);

            } else {
                rq.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }

}
