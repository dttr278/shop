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
import java.sql.Statement;
import java.util.ArrayList;
import model.Categories;

/**
 *
 * @author phuong nam
 */
public class CategoriesDao {

    public static ArrayList<Categories> getAll() throws SQLException {
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

    public static ArrayList<Categories> getListBySearchLimit(String keyword, int start, int rowtotal) throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM categories "
                + "where name LIKE'%" + keyword + "%' "
                + "order by id desc limit " + start + "," + rowtotal;
        PreparedStatement ps = conect.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Categories> list = new ArrayList<>();
        while (rs.next()) {
            Categories b = new Categories();
            b.setId(rs.getInt("id"));
            b.setId_parent(rs.getInt("id_parent"));
            b.setIcon(rs.getString("icon"));
            b.setName(rs.getString("name"));
            list.add(b);
        }
        return list;
    }

    public static ArrayList<Categories> getListByPage(int start, int rowtotal) throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM categories "
                + "order by id desc limit " + start + "," + rowtotal;
        PreparedStatement ps = conect.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Categories> list = new ArrayList<>();
        while (rs.next()) {
            Categories b = new Categories();
            b.setId(rs.getInt("id"));
            b.setId_parent(rs.getInt("id_parent"));
            b.setIcon(rs.getString("icon"));
            b.setName(rs.getString("name"));
            list.add(b);
        }
        return list;
    }

    public static int countOfSearch(String keyword) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        try {
            String sql = "SELECT count(*) FROM categories"
                    + "where name LIKE'%" + keyword + "%' ";
            PreparedStatement ps = connect.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
        } finally {
            connect.close();
        }
        return -1;
    }

    public static int count() throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        try {
            String sql = "select count(*) from categories";
            PreparedStatement ps = connect.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
        } finally {
            connect.close();
        }
        return -1;
    }

    public static ArrayList<Categories> getMenu() throws SQLException {
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

    public static int getIdParent(int productID) throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "select id_parent from categories where id  = (Select id_type from products where id = '" + productID + "')";
        PreparedStatement ps = conect.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int so = -1;
        while (rs.next()) {
            so = rs.getInt("id_parent");
        }
        return so;
    }

    public static Categories getCate(int cateID) throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "select * from categories where id  =  '" + cateID + "'";
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

    public static boolean update(Categories c) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        try {
            String sql = "UPDATE `product`.`categories` SET `id_parent` = ?, `name` = ?, `icon` = ? WHERE (`id` = ?);";
            PreparedStatement ps = connect.prepareCall(sql);
            ps.setObject(1, c.getId_parent());
            ps.setString(2, c.getName());
            ps.setString(3, c.getIcon());
            ps.setInt(4, c.getId());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static int insert(Categories c) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        int id = -1;
        try {
            String sql = "INSERT INTO `product`.`categories` (`id_parent`, `name`, `icon`) VALUES (?, ?, ?);";
            connect.setAutoCommit(false);

            PreparedStatement ps = connect.prepareCall(sql);
//            ps.addBatch("SELECT LAST_INSERT_ID();");
            ps.setInt(1, c.getId_parent());
            ps.setString(2, c.getName());
            ps.setString(3, c.getIcon());
            ps.executeUpdate();
            Statement s = connect.createStatement();
            s.execute("SELECT LAST_INSERT_ID();");
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            connect.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
        return id;
    }

    public static void main(String[] args) throws SQLException {
        CategoriesDao cr = new CategoriesDao();
        Categories c = cr.getCate(4);
        System.out.println(c.getName());
    }
}
