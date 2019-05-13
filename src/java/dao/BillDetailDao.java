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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.BillDetail;

class QuantityPerMonth {

    int idProduct;
    int quantity;
    Date date;
    double price;

    public QuantityPerMonth(int idProduct, int quantity, Date date, double price) {
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.date = date;
        this.price = price;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}

public class BillDetailDao {

    public static List<QuantityPerMonth> billPerMonth(Date from, Date to,int id) throws SQLException {
        Connection connect = DBConnect.getConnecttion();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "SELECT id_product,SUM(quantity) quantity, price, MONTH(date_order) m,YEAR(date_order) y FROM bills,bill_detail where "
                + "(date_order between ? AND ?) "
                + "and bills.id=bill_detail.id_bill "
                + "and id_product=? "
                + "GROUP BY id_product,m,y;";

        String m, y;
        Date d;
        int quantity;
        double price;
        List<QuantityPerMonth> l = new ArrayList<>();
        try {
            PreparedStatement ps = connect.prepareCall(sql);
            ps.setString(1, df.format(from));
            ps.setString(2, df.format(to));
            ps.setInt(3, id);
            ResultSet rs = ps.executeQuery();
            df = new SimpleDateFormat("MM/yyyy"); 
            while (rs.next()) {
                quantity = rs.getInt("quantity");
                price=rs.getDouble("price");
                d = df.parse(rs.getObject("m") + "/" + rs.getObject("y"));
                l.add(new QuantityPerMonth(id, quantity, d, price));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connect.close();
        }
        return l;
    }

    public static List<BillDetail> getBillDetails(int billId) throws SQLException {
        Connection conect = DBConnect.getConnecttion();
        String sql = "SELECT * FROM `bill_detail` where id_bill=" + billId;
        PreparedStatement ps = conect.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<BillDetail> list = new ArrayList<>();
        while (rs.next()) {
            BillDetail b = new BillDetail();
            b.setBillID(rs.getInt("id_bill"));
            b.setPrice(rs.getDouble("price"));
            b.setProductID(rs.getInt("id_product"));
            b.setQuantity(rs.getInt("quantity"));
            list.add(b);
        }
        return list;
    }

    public void insertBillDetail(BillDetail bd) throws SQLException {
        Connection connection = DBConnect.getConnecttion();
        String sql = "INSERT INTO bill_detail VALUES(?,?,?,?,?)";
        PreparedStatement ps = connection.prepareCall(sql);
        ps.setInt(1, bd.getBillDetailID());
        ps.setInt(2, bd.getBillID());
        ps.setInt(3, bd.getProductID());
        ps.setInt(5, bd.getQuantity());
        ps.setDouble(4, bd.getPrice());
        ps.executeUpdate();
    }

    public static void main(String[] args) throws SQLException {
        new BillDetailDao().insertBillDetail(new BillDetail(0, 1, 1, 2, 300));
    }
}
