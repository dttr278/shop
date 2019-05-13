/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BillDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bill;
import payment.NganLuong;

/**
 *
 * @author dttr2
 */
@WebServlet(name = "nl_pay_bill", urlPatterns = {"/nl_pay_bill"})
public class nl_pay_bill extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String billId = request.getParameter("billId");
        if (billId != null) {
            try {
                Bill b = BillDao.loadBillById(Integer.valueOf(billId));
                if (b != null && b.getPayment() == 1 && b.getPayment_id() == null) {
                    NganLuong nl = new NganLuong();
                    String p = request.getRequestURL().toString();
                    String p1 = request.getRequestURI();
                    String path = p.replace(p1, "") + "/nl_return";
                    String url = nl.setPrice(String.valueOf(b.getTotal()))
                            .setOrder_code(String.valueOf(billId))
                            .setCurrency("usd")
                            .setReturn_url(path)
                            .buildCheckoutUrlNew();
                    response.sendRedirect(url);
                }
            } catch (SQLException ex) {
                Logger.getLogger(nl_pay_bill.class.getName()).log(Level.SEVERE, null, ex);
                response.getWriter().print("error");
            } catch (Exception e) {
                Logger.getLogger(nl_pay_bill.class.getName()).log(Level.SEVERE, null, e);
                response.getWriter().print("error");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
