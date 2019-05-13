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
public class Categories {
    int id;
    int id_parent;
    String name;
    int id_url;
    String icon;

    public Categories(int id, int id_parent, String name, int id_url, String icon) {
        this.id = id;
        this.id_parent = id_parent;
        this.name = name;
        this.id_url = id_url;
        this.icon = icon;
    }

    public Categories() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_parent() {
        return id_parent;
    }

    public void setId_parent(int id_parent) {
        this.id_parent = id_parent;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_url() {
        return id_url;
    }

    public void setId_url(int id_url) {
        this.id_url = id_url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    
}
