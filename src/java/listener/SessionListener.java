/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import dao.ProductDao;
import java.util.Map;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import model.Cart;
import model.Item;

/**
 *
 * @author DO TAN TRUNG
 */
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session was created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
         System.out.println("session was closed");
        Cart cart = (Cart) se.getSession().getAttribute("cart");
        for (Map.Entry<Integer, Item> c : cart.getCartItems().entrySet()) {
            ProductDao.add(c.getValue().getProduct().getId(), c.getValue().getQty());
        }
    }

}
