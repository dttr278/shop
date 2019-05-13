/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author phuong nam
 */
public class Cart {
    @Expose
    HashMap<Integer, Item> cartItems;
    public Cart() { 
        cartItems = new HashMap<>();
    }
    
    public Cart(HashMap<Integer, Item> cartItems) {
        this.cartItems = cartItems;
    }

    public HashMap<Integer, Item> getCartItems() {
        return cartItems;
    }

    public void setCartItems(HashMap<Integer, Item> cartItems) {
        this.cartItems = cartItems;
    }
    
    public void plusToCart(int key, Item item) {
        boolean check = cartItems.containsKey(key);
        //System.out.println(check);
        if (check) {
            int quantity_old = item.getQty();
            item.setQty(quantity_old + 1);
            cartItems.put(key, item);
           // System.out.println(cartItems.put(key, item));
        } else {
            cartItems.put(key, item);
        }
    }
    
     public void subToCart(int key, Item item) {
        boolean check = cartItems.containsKey(key);
        if (check) {
            int quantity_old = item.getQty();
            if (quantity_old <= 1) {
                cartItems.remove(key);
            } else {
                item.setQty(quantity_old - 1);
                cartItems.put(key, item);
            }
        }
    }
     
    public void removeToCart(int key) {
        boolean check = cartItems.containsKey(key);
        if (check) {
            cartItems.remove(key);
        }
    }
    
    public int countItem() {
        return cartItems.size();
    }
    
    public double totalCart() {
        double count = 0;
        // count = price * quantity
        for (Map.Entry<Integer, Item> list : cartItems.entrySet()) {
            count += list.getValue().getProduct().getValue() * list.getValue().getQty();
        }
        return count+5;
    }
    
    public static void main(String[] args) {
        Product p = new Product(1, 2, 3, "a", "b", 4, 5, "d", "c", 1, 1, "d", 1);
        Item i = new Item(p, 2);
       
       Cart c = new Cart();
        c.plusToCart(1, i);
        
    }
}
