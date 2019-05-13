/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;

/**
 *
 * @author DO TAN TRUNG
 */
public class TotalPerMonth {

    double total;
    Date month;

    public double getTotal() {
        return total;
    }

    public TotalPerMonth(double total, Date month) {
        this.total = total;
        this.month = month;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }
}
