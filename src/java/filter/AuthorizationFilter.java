/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import dao.UserDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author DO TAN TRUNG
 */
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        User users = null;
        HttpSession session = httpServletRequest.getSession();
        boolean access = true;
        String role = null;
        if (session.getAttribute("users") != null) {
            users = (User) session.getAttribute("users");
            UserDao userDao = new UserDao();
            try {
                role = userDao.getRole(users.getId());
            } catch (SQLException ex) {
                Logger.getLogger(AuthorizationFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String path = String.valueOf(httpServletRequest.getServletPath());

        switch (path) {
            case "/productmanagement.jsp":
            case "/productdetail.jsp":
            case "/DeleteProduct":
            case "/billmanagement.jsp":
            case "/DeleteBill":
            case "/categories.jsp":
            case "/categori.jsp":
                if ("admin".equalsIgnoreCase(role) || "employee".equalsIgnoreCase(role)) {
                    access = true;
                } else {
                    access = false;
                }
                break;
            case "/tk.jsp":
            case "/usermanagement.jsp":
            case "/userdetailadm.jsp":
            case "/DeleteUser":
                if ("admin".equalsIgnoreCase(role)) {
                    access = true;
                } else {
                    access = false;
                }
                break;
            case "/checkout.jsp":
                if ("customer".equalsIgnoreCase(role)) {
                    access = true;
                } else {
                    access = false;
                }
                break;
            default:
                break;
        }
        if ("/".equals(path)) {
            httpServletResponse.sendRedirect("home.jsp");
        } else if (access) {
            chain.doFilter(request, response);
        } else {
            httpServletResponse.sendRedirect("login.jsp");
        }
    }

    @Override
    public void destroy() {
    }

}
