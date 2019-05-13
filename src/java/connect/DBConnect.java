/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Trung
 */
public class DBConnect {
     public static Connection getConnecttion() {
//        Connection cons = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            cons = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:8888;databasename=shop0903"+ "username=root;password=");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return cons;
        Connection conn = null;
        try {
            String dbURL = "jdbc:mysql://localhost:3306/product?useUnicode=true&characterEncoding=UTF-8";
            String username = "root";
            String password = "123456";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {
//                System.out.println("Kết nối thành công");
            }
//            String sql = "select * from bill_detail";
//            Statement stm = conn.createStatement();
//            ResultSet rs = stm.executeQuery(sql);
//            while(rs.next()){
//                int id = rs.getInt("id");
//                System.out.println("id:"+id);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        System.out.println(getConnecttion());
    }

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
