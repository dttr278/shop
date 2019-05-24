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
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author dttr2
 */
public class CommentDao {

    public static boolean NewComment(String cmt, int product_id, int parent_id, int user_id) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        int rs = 0;
        try {
            String sql = "INSERT INTO `product`.`comment` (product_id,parent_id,content,send_time,user_id) VALUES (?,?,?,?,?);";
            PreparedStatement ps = connect.prepareCall(sql);
            ps.setInt(1, product_id);
            ps.setInt(2, parent_id);
            ps.setString(3, cmt);
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(5, user_id);
            rs = ps.executeUpdate();

        } catch (Exception ex) {
        } finally {
            connect.close();
        }
        if (rs > 0) {
            return true;
        } else {
            return false;
        }
    }
    
}
