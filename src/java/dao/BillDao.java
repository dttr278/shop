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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bill;
import model.BillDetail;




public class BillDao {

    public static int countBillOfSearch(String keyword) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        try {
            String sql = "SELECT count(*) FROM bills,users "
                    + "where users.username LIKE'%" + keyword + "%' "
                    + "and id_customer=users.id";
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

    public static ArrayList<Bill> getListBillBySearchLimit(String keyword, int start, int rowtotal) throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM bills,users "
                + "where users.username LIKE'%" + keyword + "%' "
                + "and id_customer=users.id "
                + "order by bills.id desc limit " + start + "," + rowtotal;
        PreparedStatement ps = conect.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Bill> list = new ArrayList<>();
        while (rs.next()) {
            Bill b = new Bill();
            b.setBillID(rs.getInt("id"));
            b.setAddress(rs.getString("address"));
            String d = rs.getString("date_order");
            b.setDate(Timestamp.valueOf(d));
            b.setPayment(rs.getInt("payment_method"));
            b.setTotal(Double.valueOf(rs.getString("total")));
            b.setUserID(rs.getInt("id_customer"));
            b.setNote(rs.getString("note"));
            b.setStatus(rs.getInt("status"));
            b.setPayment_id(rs.getString("payment_id"));
            list.add(b);
        }
        return list;
    }

    public static boolean checkUserHaveBill(int idUser, int idBill) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        String sql = "select * from bills where id=? and id_customer=?  ";
        PreparedStatement ps = connect.prepareCall(sql);
        ps.setInt(1, idBill);
        ps.setInt(2, idUser);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return true;
        }
        return false;
    }

    public static List<TotalPerMonth> totalPerMonth(Date from, Date to) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "SELECT SUM(total) total, MONTH(date_order) m,YEAR(date_order) y FROM bills where "
                + "(date_order between ? AND ?)"
                + "and status=?"
                + " GROUP BY m,y; ";

        String m, y;
        Date d;
        double total;
        List<TotalPerMonth> l = new ArrayList<>();
        try {
            PreparedStatement ps = connect.prepareCall(sql);
            ps.setString(1, df.format(from));
            ps.setString(2, df.format(to));
            ps.setInt(3, 3);
            ResultSet rs = ps.executeQuery();
            df = new SimpleDateFormat("MM/yyyy");
            while (rs.next()) {
                total = rs.getDouble("total");
                d = df.parse(rs.getObject("m") + "/" + rs.getObject("y"));
                l.add(new TotalPerMonth(total, d));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connect.close();
        }
        return l;
    }

    public static int countBillOfUser(int userId) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        try {
            String sql = "select count(*) from bills where id_customer=" + userId;
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

    public static List<Bill> getPageBillOfUser(int userId, int start, int rowtotal) throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM `bills` where id_customer=" + userId + " order by id desc limit " + start + "," + rowtotal;
        PreparedStatement ps = conect.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Bill> list = new ArrayList<>();
        while (rs.next()) {
            Bill b = new Bill();
            b.setBillID(rs.getInt("id"));
            b.setAddress(rs.getString("address"));
            String d = rs.getString("date_order");
            b.setDate(Timestamp.valueOf(d));
            b.setPayment(rs.getInt("payment_method"));
            b.setTotal(Double.valueOf(rs.getString("total")));
            b.setUserID(rs.getInt("id_customer"));
            b.setNote(rs.getString("note"));
            b.setStatus(rs.getInt("status"));
            b.setPayment_id(rs.getString("payment_id"));
            list.add(b);
        }
        return list;
    }

    public static boolean deleteBill(int billId) {
        try {
            Connection connect = DBConnect.getConnecttion();
            String sql = "delete from bill_detail where id_bill=" + billId;
            String sql1 = "delete from bills where id=" + billId;
            connect.setAutoCommit(false);
            Statement s = connect.createStatement();
            s.executeUpdate(sql);
            s.executeUpdate(sql1);
            connect.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BillDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static int count() throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        try {
            String sql = "select count(*) from bills";
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

    public static boolean updateBill(Bill b) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        try {
            String sql = "UPDATE `product`.`bills` SET `id_customer` = ?, `date_order` = ?, `total`=?, `payment_method` = ?, `note` = ?, `status` = ?, `address` = ?, `phone` = ?,`payment_id`=? WHERE (`id` = ?);";
            PreparedStatement ps = connect.prepareCall(sql);
            ps.setInt(1, b.getUserID());
            ps.setTimestamp(2, b.getDate());
            ps.setDouble(3, b.getTotal());
            ps.setInt(4, b.getPayment());
            ps.setString(5, b.getNote());
            ps.setInt(6, b.getStatus());
            ps.setString(7, b.getAddress());
            ps.setString(8, b.getPhone());
            ps.setString(9, b.getPayment_id());
            ps.setInt(10, b.getBillID());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static Bill loadBillById(int id) throws SQLException {
        Connection con = DBConnect.getConnecttion();
        String sql = "SELECT * FROM bills WHERE id='" + id + "'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Bill b = new Bill();
            b.setBillID(rs.getInt("id"));
            b.setAddress(rs.getString("address"));
            b.setDate(Timestamp.valueOf(rs.getString("date_order")));
            b.setPayment(rs.getInt("payment_method"));
            b.setTotal(Double.valueOf(rs.getString("total")));
            b.setUserID(rs.getInt("id_customer"));
            b.setNote(rs.getString("note"));
            b.setStatus(rs.getInt("status"));
            b.setPhone(rs.getString("phone"));
            b.setPayment_id(rs.getString("payment_id"));
            con.close();
            return b;
        }
        return null;
    }

    public static ArrayList<Bill> getListBillByPage(int start, int rowtotal) throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM `bills` order by id desc limit " + start + "," + rowtotal;
        PreparedStatement ps = conect.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Bill> list = new ArrayList<>();
        while (rs.next()) {
            Bill b = new Bill();
            b.setBillID(rs.getInt("id"));
            b.setAddress(rs.getString("address"));
            String d = rs.getString("date_order");
            b.setDate(Timestamp.valueOf(d));
            b.setPayment(rs.getInt("payment_method"));
            b.setTotal(Double.valueOf(rs.getString("total")));
            b.setUserID(rs.getInt("id_customer"));
            b.setNote(rs.getString("note"));
            b.setStatus(rs.getInt("status"));
            b.setPayment_id(rs.getString("payment_id"));
            list.add(b);
        }
        return list;
    }

    public static boolean updateStatus(int billId, int status) {
        Connection connect = DBConnect.getConnecttion();
        String sql = "UPDATE `product`.`bills` SET `status` = " + status + " WHERE (`id` = " + billId + ");";
        try {
            Statement st = connect.createStatement();
            st.executeUpdate(sql);
            connect.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BillDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void insertBill(Bill b) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        String sql = "INSERT INTO `product`.`bills` (`id_customer`, `date_order`, `total`, `payment_method`, `note`, `status`, `address`, `phone`,`payment_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?);";
        PreparedStatement ps = connect.prepareCall(sql);
        ps.setInt(1, b.getUserID());
        ps.setTimestamp(2, b.getDate());
        ps.setDouble(3, b.getTotal());
        ps.setInt(4, b.getPayment());
        ps.setString(5, b.getNote());
        ps.setInt(6, b.getStatus());
        ps.setString(7, b.getAddress());
        ps.setString(8, b.getPhone());
        ps.setString(9, b.getPayment_id());
        ps.executeUpdate();
    }

    public static int insertBillGetId(Bill b, List<BillDetail> l) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        String sql = "INSERT INTO `product`.`bills` (`id_customer`, `date_order`, `total`, `payment_method`, `note`, `status`, `address`, `phone`,`payment_id`) VALUES (?, ?, ?,?, ?, ?, ?, ?, ?);";
        connect.setAutoCommit(false);
        PreparedStatement ps = connect.prepareCall(sql);
        ps.setInt(1, b.getUserID());
        ps.setTimestamp(2, b.getDate());
        ps.setDouble(3, b.getTotal());
        ps.setInt(4, b.getPayment());
        ps.setString(5, b.getNote());
        ps.setInt(6, b.getStatus());
        ps.setString(7, b.getAddress());
        ps.setString(8, b.getPhone());
        ps.setString(9, b.getPayment_id());
        ps.executeUpdate();

        int id = -1;
        Statement s = connect.createStatement();
        s.execute("SELECT LAST_INSERT_ID();");
        ResultSet rs = s.getResultSet();
        while (rs.next()) {
            id = rs.getInt(1);
        }
        if (id == -1) {
            return id;
        }
        for (BillDetail bd : l) {
            try {
                String sql1 = "INSERT INTO `bill_detail` (`id_bill`, `id_product`, `quantity`, `price`) VALUES (?, ?, ?, ?);";
                PreparedStatement ps1 = connect.prepareCall(sql1);
                ps1.setInt(1, id);
                ps1.setInt(2, bd.getProductID());
                ps1.setInt(3, bd.getQuantity());
                ps1.setDouble(4, bd.getPrice());
                ps1.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(BillDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        connect.commit();
        return id;
    }

    public static void main(String[] args) throws SQLException {
        DateFormat df = new SimpleDateFormat("MM/yyyy");
        try {
            List<TotalPerMonth> l = totalPerMonth(df.parse("10/2016"), df.parse("10/2020"));
            l.stream().forEach(n -> System.out.println(df.format(n.getMonth()) + ">>" + String.format("%.2f", n.getTotal())));
        } catch (ParseException ex) {
            Logger.getLogger(BillDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
