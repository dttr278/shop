/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BillDao;
import dao.BillDetailDao;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Bill;
import model.BillDetail;
import model.Cart;
import model.Item;
import model.User;
import payment.NganLuong;

/**
 *
 * @author phuong nam
 */
public class CheckOutServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    BillDao billDAO = new BillDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        int payment = Integer.valueOf(request.getParameter("payment"));
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User users = (User) session.getAttribute("users");
        int billId = -1;
        try {
            Bill bill = new Bill();
            bill.setUserID(users.getId());
            bill.setDate(new Timestamp(new Date().getTime()));
            bill.setTotal(cart.totalCart());
            bill.setPayment(payment);
            bill.setAddress(address);
            bill.setPhone(phone);
            if (payment == 0) {
                bill.setStatus(0);
            } else {
                bill.setStatus(5);
            }
            List<BillDetail> lb = new ArrayList<>();
            for (Map.Entry<Integer, Item> list : cart.getCartItems().entrySet()) {
                lb.add(new BillDetail(-1, -1,
                        list.getValue().getProduct().getId(),
                        list.getValue().getProduct().getValue(),
                        list.getValue().getQty()));
            }
            billId = BillDao.insertBillGetId(bill, lb);
            session.removeAttribute("cart");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (payment == 0) {
            response.sendRedirect("billdetail.jsp?billId=" + billId);
        } else {
            if (billId != -1) {
                NganLuong nl = new NganLuong();
                String p = request.getRequestURL().toString();
                String p1 = request.getRequestURI();
                String path = p.replace(p1, "") + "/nl_return";
                String url = nl.setPrice(String.valueOf(cart.totalCart()))
                        .setOrder_code(String.valueOf(billId))
                        .setCurrency("usd")
                        .setReturn_url(path)
                        .buildCheckoutUrlNew();
                response.sendRedirect(url);
            }

        }

    }

}
