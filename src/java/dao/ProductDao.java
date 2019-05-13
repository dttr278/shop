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
import java.util.Date;
import model.Product;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phuong nam
 */
public class ProductDao {

    public static boolean subtract(int productId, int sl) {
        try {
            Connection connection = DBConnect.getConnecttion();
            String slectAmount = "select amount from products where id=?";
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareCall(slectAmount);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            int amount = -1;
            while (rs.next()) {
                amount = rs.getInt("amount");
            }
            amount -= sl;
            String updateAmount = "update products set amount =? where id=?";
            ps = connection.prepareCall(updateAmount);
            ps.setInt(1, amount);
            ps.setInt(2, productId);
            ps.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static boolean add(int productId, int sl) {
        try {
            Connection connection = DBConnect.getConnecttion();
            String slectAmount = "select amount from products where id=?";
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareCall(slectAmount);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            int amount = -1;
            while (rs.next()) {
                amount = rs.getInt("amount");
            }
            amount += sl;
            String updateAmount = "update products set amount =? where id=?";
            ps = connection.prepareCall(updateAmount);
            ps.setInt(1, amount);
            ps.setInt(2, productId);
            ps.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int count() {
        Connection connect = DBConnect.getConnecttion();
        try {
            String sql = "select count(*) from products where deleted = 0";
            PreparedStatement ps = connect.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
        }
        return -1;
    }

    public ArrayList<Product> getListProductByPage(int start, int rowtotal) throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM `products` WHERE deleted = 0 order by id desc limit " + start + "," + rowtotal;
        PreparedStatement ps = conect.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Product> list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setName(rs.getString("name"));
            product.setValue(rs.getDouble("value"));
            product.setImage(rs.getString("image"));
            product.setId(rs.getInt("id"));
            product.setPromotion_price(rs.getFloat("promotion_price"));
            product.setId_type(rs.getInt("id_type"));
            product.setId_url(rs.getInt("id_url"));
            product.setAmount(rs.getInt("amount"));
            list.add(product);
        }
        return list;
    }

    public boolean insertProduct(Product pro) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        try {
            String sql = "INSERT INTO products (id_type,name,detail,value,promotion_price,image,status,new,update_at,amount) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connect.prepareCall(sql);
            ps.setInt(1, pro.getId_type());
            ps.setString(2, pro.getName());
            ps.setString(3, pro.getDetail());
            ps.setDouble(4, pro.getValue());
            ps.setDouble(5, pro.getPromotion_price());
            ps.setString(6, pro.getImage());
            ps.setInt(7, pro.getisStatus());
            ps.setInt(8, pro.getNew_product());
            ps.setDate(9, new java.sql.Date((new Date()).getTime()));
            ps.setInt(10, pro.getAmount());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public int insertProductGetId(Product pro) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        int id = -1;
        try {
            String sql = "INSERT INTO products (id_type,name,detail,value,promotion_price,image,status,new,update_at,amount) VALUES(?,?,?,?,?,?,?,?,?,?)";
            connect.setAutoCommit(false);

            PreparedStatement ps = connect.prepareCall(sql);
//            ps.addBatch("SELECT LAST_INSERT_ID();");
            ps.setInt(1, pro.getId_type());
            ps.setString(2, pro.getName());
            ps.setString(3, pro.getDetail());
            ps.setDouble(4, pro.getValue());
            ps.setDouble(5, pro.getPromotion_price());
            ps.setString(6, pro.getImage());
            ps.setInt(7, pro.getisStatus());
            ps.setInt(8, pro.getNew_product());
            ps.setDate(9, new java.sql.Date((new Date()).getTime()));
            ps.setInt(10, pro.getAmount());
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

    public boolean updateProduct(Product pro) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        try {
            String sql = "UPDATE `product`.`products` SET `id_type` = ?,  `name` = ?, `detail` = ?, `value` = ?, `promotion_price` = ?, `promotion` = ?, `image` = ?, `status` = ?, `new` = ?, `update_at` = ?,`amount`=? WHERE (`id` = ?);";
            PreparedStatement ps = connect.prepareCall(sql);
            ps.setInt(1, pro.getId_type());
            ps.setString(2, pro.getName());
            ps.setString(3, pro.getDetail());
            ps.setDouble(4, pro.getValue());
            ps.setDouble(5, pro.getPromotion_price());
            ps.setString(6, pro.getPromotion());
            ps.setString(7, pro.getImage());
            ps.setInt(8, pro.getisStatus());
            ps.setInt(9, pro.getNew_product());
            ps.setDate(10, new java.sql.Date((new Date()).getTime()));
            ps.setInt(11, pro.getAmount());
            ps.setInt(12, pro.getId());

            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteProduct(int id) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        try {
            String sql = "UPDATE products set deleted = 1 WHERE id = '" + id + "'";
            PreparedStatement ps = connect.prepareCall(sql);
            ps.executeUpdate();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean reloadProduct(int id) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        try {
            String sql = "UPDATE products set deleted = 0 WHERE id = '" + id + "'";
            PreparedStatement ps = connect.prepareCall(sql);
            ps.executeUpdate();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public ArrayList<Product> getListProductByDeleted() throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM `products` WHERE deleted = 1 ";
        PreparedStatement ps = conect.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Product> list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setName(rs.getString("name"));
            product.setValue(rs.getDouble("value"));
            product.setImage(rs.getString("image"));
            product.setId(rs.getInt("id"));
            product.setPromotion_price(rs.getFloat("promotion_price"));
            product.setId_type(rs.getInt("id_type"));
            product.setId_url(rs.getInt("id_url"));
            product.setAmount(rs.getInt("amount"));
            list.add(product);
        }
        return list;
    }

    public ArrayList<Product> getListProductByNew() throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM `products` WHERE `new` = '1' and deleted = 0 ";
        PreparedStatement ps = conect.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Product> list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setName(rs.getString("name"));
            product.setValue(rs.getDouble("value"));
            product.setImage(rs.getString("image"));
            product.setId(rs.getInt("id"));
            product.setPromotion_price(rs.getFloat("promotion_price"));
            product.setId_type(rs.getInt("id_type"));
            product.setId_url(rs.getInt("id_url"));
            product.setAmount(rs.getInt("amount"));
            list.add(product);
        }
        return list;
    }

    public ArrayList<Product> getListProductBySpecial() throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM products WHERE status = 1 and deleted = 0";
        PreparedStatement ps = conect.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Product> list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setName(rs.getString("name"));
            product.setImage(rs.getString("image"));
            product.setValue(rs.getDouble("value"));
            product.setPromotion_price(rs.getFloat("promotion_price"));
            product.setId(rs.getInt("id"));
            product.setId_url(rs.getInt("id_url"));
            product.setAmount(rs.getInt("amount"));
            list.add(product);
        }
        return list;
    }

    public ArrayList<Product> getListProductByPromotionPrice() throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM products WHERE promotion_price > 0 and deleted = 0 ";
        PreparedStatement ps = conect.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Product> list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();

            product.setName(rs.getString("name"));
            product.setImage(rs.getString("image"));
            product.setValue(rs.getDouble("value"));
            product.setPromotion_price(rs.getFloat("promotion_price"));
            product.setId(rs.getInt("id"));
            product.setId_url(rs.getInt("id_url"));
            product.setAmount(rs.getInt("amount"));
            list.add(product);
        }
        return list;
    }

    public static Product getProduct(int productID) throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM products WHERE id='" + productID + "'";
        PreparedStatement ps = conect.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Product product = new Product();
        while (rs.next()) {
            product.setName(rs.getString("name"));
            product.setImage(rs.getString("image"));
            product.setValue(rs.getDouble("value"));
            product.setPromotion_price(rs.getFloat("promotion_price"));
            product.setId(rs.getInt("id"));
            product.setPromotion(rs.getString("promotion"));
            product.setDetail(rs.getString("detail"));
            product.setId_type(rs.getInt("id_type"));
            product.setNew_product(rs.getInt("new"));
            product.setId_url(rs.getInt("id_url"));
            product.setAmount(rs.getInt("amount"));
        }
        return product;
    }

    public ArrayList<Product> getListProductByRelated(int id_type) throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM products WHERE deleted = 0 and id_type = '" + id_type + "'";
        PreparedStatement ps = conect.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Product> list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();

            product.setName(rs.getString("name"));
            product.setImage(rs.getString("image"));
            product.setValue(rs.getDouble("value"));
            product.setPromotion_price(rs.getFloat("promotion_price"));
            product.setId(rs.getInt("id"));
            product.setId_type(rs.getInt("id_type"));
            product.setId_url(rs.getInt("id_url"));
            product.setAmount(rs.getInt("amount"));
            list.add(product);
        }
        return list;
    }

    public ArrayList<Product> getListProductByIdParent(int id) throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM products WHERE id_type in (SELECT id FROM categories WHERE id_parent ='" + id + "') and deleted = 0 ";
        PreparedStatement ps = conect.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Product> list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();

            product.setName(rs.getString("name"));
            product.setImage(rs.getString("image"));
            product.setValue(rs.getDouble("value"));
            product.setPromotion_price(rs.getFloat("promotion_price"));
            product.setId(rs.getInt("id"));
            product.setId_type(rs.getInt("id_type"));
            product.setId_url(rs.getInt("id_url"));
            product.setAmount(rs.getInt("amount"));
            list.add(product);
        }
        return list;
    }

    public int getCountSearch(String keyword) {
        Connection connect = DBConnect.getConnecttion();
        try {
            String sql = "select count(*) from products where deleted = 0 and name LIKE'%" + keyword + "%'";
            PreparedStatement ps = connect.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
        }
        return -1;
    }

    public ArrayList<Product> getListProductBySearch(String keyword) throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM products WHERE deleted = 0 and name LIKE'%" + keyword + "%'";
        PreparedStatement ps = conect.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Product> list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setName(rs.getString("name"));
            product.setImage(rs.getString("image"));
            product.setValue(rs.getDouble("value"));
            product.setPromotion_price(rs.getFloat("promotion_price"));
            product.setId(rs.getInt("id"));
            product.setId_type(rs.getInt("id_type"));
            product.setNew_product(rs.getInt("new"));
            product.setId_url(rs.getInt("id_url"));
            product.setAmount(rs.getInt("amount"));
            list.add(product);
        }
        return list;
    }

    public ArrayList<Product> getListProductBySearchLimit(String keyword, int start, int end) throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM products WHERE deleted = 0 and name LIKE'%" + keyword + "%' limit " + start + "," + end;
        PreparedStatement ps = conect.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Product> list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setName(rs.getString("name"));
            product.setImage(rs.getString("image"));
            product.setValue(rs.getDouble("value"));
            product.setPromotion_price(rs.getFloat("promotion_price"));
            product.setId(rs.getInt("id"));
            product.setId_type(rs.getInt("id_type"));
            product.setNew_product(rs.getInt("new"));
            product.setId_url(rs.getInt("id_url"));
            product.setAmount(rs.getInt("amount"));
            list.add(product);
        }
        return list;
    }

    public static void main(String[] args) throws SQLException {
        ProductDao pr = new ProductDao();
        Product p = new Product();
        boolean flag = pr.deleteProduct(2);
        //int count = 0;
        //for (Product p : pr.getListProductBySearch("iphone")) {
//      System.out.println(p.getValue() + "       " + p.getPromotion_price()+"                     "+p.getId_type());
        //     count++;
        //     }
//          p=pr.getProduct(1);
//          System.out.println(p.getId_type());
        /*p.setId_type(1);
        p.setName("AAB");
        p.setDetail("sada");
        p.setValue(4545);
        p.setPromotion_price(2132);
        p.setImage("asddasda");
        p.setStatus(1);
        p.setNew_product(1);
        boolean flag =pr.insertProduct(p);*/
        System.out.println(flag);
    }

}
