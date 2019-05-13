/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Categories;

/**
 *
 * @author phuong nam
 */
public class CategoriesDao {
    public ArrayList<Categories> getAll() throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM categories";
        PreparedStatement ps = conect.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Categories> list = new ArrayList<>();
        while (rs.next()) {
            Categories menu = new Categories();
            menu.setId(rs.getInt("id"));
            menu.setName(rs.getString("name"));
            menu.setId_parent(rs.getInt("id_parent"));
            menu.setIcon(rs.getString("icon"));
            list.add(menu);
        }
        return list;
    }
     public ArrayList<Categories> getMenu() throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM categories WHERE id_parent IS NULL";
        PreparedStatement ps = conect.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Categories> list = new ArrayList<>();
        while (rs.next()) {
            Categories menu = new Categories();
            menu.setId(rs.getInt("id"));
            menu.setName(rs.getString("name"));
            menu.setId_parent(rs.getInt("id_parent"));
            menu.setIcon(rs.getString("icon"));
            list.add(menu);
        }
        return list;
    }
    public int getIdParent(int productID) throws SQLException{
           Connection conect = DBConnect.getConnecttion();
           String sql ="select id_parent from categories where id  = (Select id_type from products where id = '"+productID+"')";
           PreparedStatement ps = conect.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           int so = -1;
           while (rs.next()) {
               so = rs.getInt("id_parent");
           }
           return so;
    }
    public Categories getCate(int cateID) throws SQLException{
           Connection conect = DBConnect.getConnecttion();
           String sql ="select * from categories where id  =  '"+cateID+"'";
           PreparedStatement ps = conect.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           Categories cate = new Categories();
           while (rs.next()) {
               cate.setId(rs.getInt("id"));
               cate.setName(rs.getString("name"));
               cate.setId_parent(rs.getInt("id_parent"));
               cate.setIcon(rs.getString("icon"));
           }
           return cate;
    }
     public static void main(String[] args) throws SQLException {
       CategoriesDao cr = new CategoriesDao();
             Categories c = cr.getCate(4);
           System.out.println(c.getName());
    }
}
