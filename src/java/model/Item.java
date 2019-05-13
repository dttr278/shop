/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.annotations.Expose;

/**
 *
 * @author phuong nam
 */
public class Item {
    @Expose
    Product product;
    @Expose
    int qty;

    public Item(Product product, int qty) {
        this.product = product;
        this.qty = qty;
    }

    public Item() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
    
}
