/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.ProductDao;
import dao.UserDao;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import model.Product;
import model.User;

/**
 *
 * @author DO TAN TRUNG
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Object object = httpServletRequest.getSession().getAttribute("users");
        if (object == null) {
            User user = (User) object;
            Cookie cookies[] = httpServletRequest.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("userId")) {
                        try {
                            user = UserDao.loadUserById(Integer.valueOf(c.getValue()));
                            c.setMaxAge(36000);
                            httpServletResponse.addCookie(c);
                            httpServletRequest.getSession().setAttribute("users", user);
                        } catch (SQLException ex) {
                            Logger.getLogger(LoginFilter.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (c.getName().equals("cart") && httpServletRequest.getSession().getAttribute("users") != null) {
                        String j = URLDecoder.decode(c.getValue(), "UTF-8");
                        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                        Cart cart = gson.fromJson(j, Cart.class);
                        List<Integer> l = new ArrayList<>(cart.getCartItems().keySet());
                        int productId;
                        Product p;
                        for (Integer i : l) {
                            productId = i;
                            try {
                                p = ProductDao.getProduct(productId);
                                if (cart.getCartItems().get(i).getQty() <= p.getAmount()) {
                                    cart.getCartItems().get(i).setProduct(p);
                                }else{
                                    cart.getCartItems().remove(i);
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(LoginFilter.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        httpServletRequest.getSession().setAttribute("cart", cart);
                    }
                }
            }
        }
        chain.doFilter(httpServletRequest, response);
    }

    @Override
    public void destroy() {
    }

}
