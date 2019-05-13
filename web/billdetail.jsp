<%-- 
    Document   : billdetail
    Created on : Apr 22, 2019, 12:12:40 AM
    Author     : DO TAN TRUNG
--%>

<%@page import="model.BillStatus"%>
<%@page import="model.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="dao.UserDao"%>
<%@page import="dao.ProductDao"%>
<%@page import="model.Product"%>
<%@page import="model.BillDetail"%>
<%@page import="dao.BillDetailDao"%>
<%@page import="java.util.List"%>
<%@page import="model.Bill"%>
<%@page import="dao.BillDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <script src="js/messagebox_script.js"></script>
        <link type="text/css" rel="stylesheet" href="css/messagebox_style.css"/>

    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%
            String billId = request.getParameter("billId");
            if (billId == null) {
                response.getWriter().print("bill not found");
                return;
            }
            String role = "";
            User u;
            if (session.getAttribute("users") != null) {
                u = (User) session.getAttribute("users");
                role = UserDao.getRole(u.getId());
            } else {
                response.getWriter().print("access denied");
                return;
            }
            if ("customer".equalsIgnoreCase(role) && !BillDao.checkUserHaveBill(u.getId(), Integer.valueOf(billId))) {
                response.getWriter().print("access denied");
                return;
            }

            Bill b = BillDao.loadBillById(Integer.valueOf(billId));
            if (b == null) {
                response.getWriter().print("bill not found");
                return;
            }
            String status[] = BillStatus.status;
            List<BillDetail> lBillDetail = BillDetailDao.getBillDetails(b.getBillID());

        %>
        <div class="container"  style="padding-top: 2rem;">
            <h3>#<%=b.getBillID()%> - <i><%=status[b.getStatus()]%></i></h3>
            <%
                Timestamp ts = b.getDate();
                Date date = new Date();
                date.setTime(ts.getTime());
                String formattedDate = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(date);

            %>
            <span style="float:  right;"><%=formattedDate%></span>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col" style="width: 15em;"></th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><b>Customer:</b></td>
                        <td><%=UserDao.loadUserById(b.getUserID()).getUsername()%></td>
                    </tr>
                    <tr>
                        <td><b>Address:</b></td>
                        <td><%=b.getAddress()%></td>
                    </tr>
                    <tr>
                        <td><b>Phone:</b></td>
                        <td><%=b.getPhone()%></td>
                    </tr>

                    <tr>
                        <%
                            String payment = "";
                            String paymentStatus = "";
                            switch (b.getPayment()) {
                                case 0:
                                    payment = "Direct";
                                    if (b.getStatus() != 3) {
                                        paymentStatus = "unpaid";
                                    } else {
                                        paymentStatus = "paid";
                                    }
                                    break;
                                case 1:
                                    payment = "Online";
                                    if (b.getPayment_id() != null && !b.getPayment_id().isEmpty()) {
                                        paymentStatus = "paid";
                                    } else {
                                        paymentStatus = "unpaid";
                                    }
                                    break;
                                default:
                                    break;
                            }
                        %>
                        <td><b>Payment method:</b></td>
                        <td><%=payment%></td>
                    </tr>
                    <tr>
                        <td><b>Payment status:</b></td>
                        <td><%=paymentStatus%></td>
                    </tr>
                    <tr class="info">
                        <td><b>Status:</b></td>
                        <td><%=status[b.getStatus()]%></td>
                    </tr>
                </tbody>
            </table>

            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Product</th>
                        <th scope="col" style="width: 10em;">Price</th>
                        <th scope="col" style="width: 10em;">Quantity</th>
                        <th scope="col" style="width: 10em;">Total</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        double total = 0;
                        for (BillDetail bd : lBillDetail) {
                            total += bd.getQuantity() * bd.getPrice();
                    %>
                    <tr>

                        <td>
                            <%Product product = ProductDao.getProduct(bd.getProductID());%>
                            <image class="img-thumbnail" style="width: 50px" src="./img/products/<%=product.getImage()%>" >
                            <a href="singgle.jsp?productID=<%=bd.getProductID()%>"><%=product.getName()%></a>
                        </td>
                        <td>
                            <span><%=String.format("%.2f", bd.getPrice())%></span>
                        </td>
                        <td>
                            <span><%=bd.getQuantity()%></span>
                        </td>
                        <td>
                            <span><%=String.format("%.2f", bd.getQuantity() * bd.getPrice())%></span>
                        </td>

                    </tr>
                    <%}%>
                </tbody>

            </table>
            <div style="float: right">
                <div style="text-align: left;" class="float-right">
                    <p>
                        <b>Transport fee: </b>
                        <span><%=5%></span>
                    </p>
                    <p>
                        <b>Total: </b>
                        <span><%=String.format("%.2f", total + 5)%></span>
                    </p>
                </div>
                <script>
                    function updateStatus(b, s, ms) {
                        doMessageBox("The bill will " + ms, function () {
                            window.location.href = "UpdateBillStatus?billId=" + b + "&status=" + s;
                        });
                    }
                    function pay(b) {
                        doMessageBox("Would you like to pay?", function () {
                            window.location.href = "nl_pay_bill?billId=" + b;
                        });
                    }
                </script>
                <div style="padding-top: 2rem;padding-bottom: 2rem;">
                    <%if (!"customer".equalsIgnoreCase(role)) {%>
                    <%if (b.getStatus() == 0) {%>
                    <button type="button" class="btn btn-info" onclick="updateStatus(<%=billId%>, 1, 'Confirm')">Confirm</button>
                    <%}%>
                    <%if (b.getStatus() == 1) {%>
                    <button type="button" class="btn btn-primary" onclick="updateStatus(<%=billId%>, 2, 'Transport')">Transport</button>
                    <%}%>
                    <%if (b.getStatus() == 2) {%>
                    <button type="button" class="btn btn-success" onclick="updateStatus(<%=billId%>, 3, 'Success')">Success</button>
                    <%}%>
                    <%if (b.getStatus() == 5) {%>
                    <button type="button" class="btn btn-success" onclick="pay(<%=billId%>)">Pay</button>
                    <%}%>
                    <%if (b.getStatus() < 3 || b.getStatus() == 5) {%>
                    <button type="button" class="btn btn-danger" onclick="updateStatus(<%=billId%>, 4, 'Cancel')">Cancel</button>
                    <%}%>
                    <%} else {%>
                    <%if (b.getStatus() == 5) {%>
                    <button type="button" class="btn btn-success" onclick="pay(<%=billId%>)">Pay</button>
                    <%}
                        if (b.getStatus() < 2 || b.getStatus() == 5) {%>
                    <button type="button" class="btn btn-danger" onclick="updateStatus(<%=billId%>, 4, 'Cancel')">Cancel</button>
                    <%}
                        }%>
                </div>
            </div>

        </div>

    </body>
    <jsp:include page="footer.jsp"></jsp:include>
</html>
