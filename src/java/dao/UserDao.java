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
import java.util.Date;
import java.util.logging.Logger;
import model.Product;
import model.User;

public class UserDao {

    public static boolean updateUser(User u) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        try {
            String sql = "UPDATE `users` SET `username` = ?, `password` = ?, `email` = ?, `address` = ?, `phone` = ?, `role` = ? WHERE (`id` = ?);";
            PreparedStatement ps = connect.prepareCall(sql);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getAddress());
            ps.setString(5, u.getPhone());
            ps.setInt(6, u.getRole());
            ps.setInt(7, u.getId());
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            connect.close();
        }
        return true;
    }

    public static boolean deleteUser(int id) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        try {
            String sql = "DELETE FROM `users` WHERE (`id` = '" + id + "');";
            PreparedStatement ps = connect.prepareCall(sql);
            ps.executeUpdate();

        } catch (Exception ex) {
            return false;
        } finally {
            connect.close();
        }
        return true;
    }

    public ArrayList<User> getListUserByPage(int start, int rowtotal) throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM `users` order by id desc limit " + start + "," + rowtotal;
        PreparedStatement ps = conect.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<User> list = new ArrayList<>();
        while (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setEmail(rs.getString("email"));
            u.setAddress(rs.getString("address"));
            u.setPhone(rs.getString("phone"));
            u.setRole(rs.getInt("role"));
            list.add(u);
        }
        conect.close();
        return list;
    }

    public static String getRole(int userId) throws SQLException {
        String role = null;
        Connection connect = DBConnect.getConnecttion();
        String sql = "SELECT role.id,role.role FROM users, role where users.id=" + userId + " and users.role=role.id";
        PreparedStatement ps;
        ps = connect.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            role = rs.getString("role");
        }
        if(role==null||role.isEmpty())
            role="customer";
        connect.close();
        return role;
    }

    public ArrayList<User> getListUserBySearchLimit(String keyword, int start, int end) throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM users WHERE username LIKE'%" + keyword + "%' or email LIKE'%" + keyword + "%' limit " + start + "," + end;
        PreparedStatement ps = conect.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<User> list = new ArrayList<>();
        while (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setEmail(rs.getString("email"));
            u.setAddress(rs.getString("address"));
            u.setPhone(rs.getString("phone"));
            u.setRole(rs.getInt("role"));
            list.add(u);
        }
        conect.close();
        return list;
    }

    public int getCountSearch(String keyword) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        try {
            String sql = "select count(*) from users where username LIKE'%" + keyword + "%'";
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
            String sql = "select count(*) from users";
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

    public static User loadUserById(int id) throws SQLException {
        Connection con = DBConnect.getConnecttion();
        String sql = "SELECT * FROM users WHERE id='" + id + "'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setEmail(rs.getString("email"));
            u.setAddress(rs.getString("address"));
            u.setPhone(rs.getString("phone"));
            u.setRole(rs.getInt("role"));
            con.close();
            return u;
        }
        return null;
    }

    public boolean checkMailExist(String email) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM users WHERE email='" + email + "'";
        PreparedStatement ps;
        ps = connect.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            connect.close();
            return true;
        }
        return false;
    }

    public static boolean insertUser(User u) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        String sql = "INSERT INTO `product`.`users` (`username`, `password`, `email`, `phone`, `address`,`role`) VALUES (?, ?, ?, ?, ?,?)";
        PreparedStatement ps = connect.prepareCall(sql);
        ps.setString(1, u.getUsername());
        ps.setString(2, u.getPassword());
        ps.setString(3, u.getEmail());
        ps.setString(4, u.getAddress());
        ps.setString(5, u.getPhone());
        ps.setInt(6, u.getRole());
        try {
            ps.executeUpdate();
            connect.close();
            return true;
        } catch (Exception e) {
            connect.close();
            Logger.getLogger(UserDao.class.getName()).info(e.getMessage());
            return false;
        }
    }

    public static int insertUserGetId(User u) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        int id = -1;
        try {
            String sql = "INSERT INTO `product`.`users` (`username`, `password`, `email`, `phone`, `address`,`role`) VALUES (?, ?, ?, ?, ?,?)";
            connect.setAutoCommit(false);

            PreparedStatement ps = connect.prepareCall(sql);
//            ps.addBatch("SELECT LAST_INSERT_ID();");
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getAddress());
            ps.setString(5, u.getPhone());
            ps.setInt(6, u.getRole());
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

    public static User login(String email, String password) throws SQLException {
        Connection con = DBConnect.getConnecttion();
        String sql = "SELECT * FROM users WHERE email='" + email + "' AND password='" + password + "'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setEmail(rs.getString("email"));
            u.setAddress(rs.getString("address"));
            u.setPhone(rs.getString("phone"));
            u.setRole(rs.getInt("role"));
            con.close();
            return u;
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        UserDao us = new UserDao();
        User u = new User(16, "nam123", "123", "namnguyen1234@gmail.com", "16 tamtam", "123456");
//       u = us.login("namnguyenphuong561@yahoo.com", "123");
//        System.out.println(u.getEmail()+ "        " + u.getUsername());
        boolean test = us.insertUser(u);
        System.out.println(test);
    }
}
