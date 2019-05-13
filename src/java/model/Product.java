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
public class Product {
    @Expose
    int id;
    int id_type;
    int id_url;
    String name;
    String detail;
    double value;
    float promotion_price;
    String promotion;
    String image;
    int status;
    int new_product;
    String date;
    int update;
    int amount=0;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public Product() {
    }

    public Product(int id, int id_type, int id_url, String name, String detail, double value, float promotion_price, String promotion, String image, int status, int new_product, String date, int update) {
        this.id = id;
        this.id_type = id_type;
        this.id_url = id_url;
        this.name = name;
        this.detail = detail;
        this.value = value;
        this.promotion_price = promotion_price;
        this.promotion = promotion;
        this.image = image;
        this.status = status;
        this.new_product = new_product;
        this.date = date;
        this.update = update;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public int getId_url() {
        return id_url;
    }

    public void setId_url(int id_url) {
        this.id_url = id_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public float getPromotion_price() {
        return promotion_price;
    }

    public void setPromotion_price(float promotion_price) {
        this.promotion_price = promotion_price;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getisStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNew_product() {
        return new_product;
    }

    public void setNew_product(int new_product) {
        this.new_product = new_product;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int isUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }

   

    

}
