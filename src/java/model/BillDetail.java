/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author phuong nam
 */
public class BillDetail {

    int billDetailID;
    int billID;
    int productID;
    double price;
    int quantity;

    public BillDetail() {
    }

    public BillDetail(int billDetailID, int billID, int productID, double price, int quantity) {
        this.billDetailID = billDetailID;
        this.billID = billID;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
    }

    public int getBillDetailID() {
        return billDetailID;
    }

    public void setBillDetailID(int billDetailID) {
        this.billDetailID = billDetailID;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
