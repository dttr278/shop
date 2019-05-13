/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Item;
import model.Product;
import model.User;

/**
 *
 * @author phuong nam
 */
public class CartServlet extends HttpServlet {

    private final ProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

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
        Object u = request.getSession().getAttribute("users");
        if (u == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        HttpSession session = request.getSession();
        String command = request.getParameter("command");
        String productID = request.getParameter("productID");
        Cart cart = (Cart) session.getAttribute("cart");
        int idProduct = Integer.parseInt(productID);
        try {
            Product product = productDao.getProduct(idProduct);
            switch (command) {
                case "plus":
                    if (cart.getCartItems().containsKey(idProduct)) {
                        cart.plusToCart(idProduct, new Item(product,
                                cart.getCartItems().get(idProduct).getQty()));
                    } else {
                        cart.plusToCart(idProduct, new Item(product, 1));
                    }
                    ProductDao.subtract(idProduct, 1);
                    break;
                case "sub":
                    if (cart.getCartItems().containsKey(idProduct)) {
                        cart.subToCart(idProduct, new Item(product,
                                cart.getCartItems().get(idProduct).getQty()));
                    } else {
                        cart.removeToCart(idProduct);
                    }
                    ProductDao.add(idProduct, 1);
                    break;
                case "remove":
                    ProductDao.add(idProduct, cart.getCartItems().get(idProduct).getQty());
                    cart.removeToCart(idProduct);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String c = URLEncoder.encode(gson.toJson(cart), "UTF-8");
        Cookie cookie = new Cookie("cart", c);
        cookie.setMaxAge(36000);
        response.addCookie(cookie);
        
        session.setAttribute("cart", cart);
        response.sendRedirect("singgle.jsp?productID=" + idProduct);
    }
}

/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
