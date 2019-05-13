/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author phuong nam
 */
public class UserServlet extends HttpServlet {

    UserDao us = new UserDao();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Object obj = request.getParameter("logout");
        if (obj != null) {
            request.getSession().removeAttribute("users");
            request.getSession().removeAttribute("cart");
            Cookie cookie = new Cookie("userId", "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);

        }
//        RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
//        rd.forward(request, response);
        response.sendRedirect("home.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String command = request.getParameter("command");
        String url = "";
        User users = null;
        switch (command) {
            case "insert": {
                users = new User();
                users.setUsername(request.getParameter("username"));
                users.setPassword(request.getParameter("password"));
                users.setEmail(request.getParameter("email"));
                users.setAddress(request.getParameter("address"));
                users.setPhone(request.getParameter("phone"));
                try {
                    if (us.insertUser(users)) {
                        session.setAttribute("users", users);
                        url = "home.jsp";
                    } else {
                        url = "register.jsp?error=Error";
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "login": {
                try {
                    users = us.login(request.getParameter("email"), request.getParameter("password"));
                    if (users != null) {
                        session.setAttribute("users", users);
                        Cookie cookie = new Cookie("userId", "" + users.getId());
                        cookie.setMaxAge(36000);
                        response.addCookie(cookie);
                        url = "home.jsp";
                    } else {
                        request.setAttribute("error", "Error email or password!");
                        url = "login.jsp";
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
//        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
//        rd.forward(request, response);
        response.sendRedirect(url);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
