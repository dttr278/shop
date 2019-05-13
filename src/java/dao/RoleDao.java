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
import model.Role;
import model.User;

/**
 *
 * @author DO TAN TRUNG
 */
public class RoleDao {
     public static ArrayList<Role> getAllRole() throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM role";
        PreparedStatement ps = conect.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Role> list = new ArrayList<>();
        while (rs.next()) {
            Role role=new Role();
            role.setId(rs.getInt("id"));
            role.setRole(rs.getString("role"));
            list.add(role);
        }
        return list;
    }
}
