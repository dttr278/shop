<%-- 
    Document   : billmanagement
    Created on : Apr 21, 2019, 11:09:42 PM
    Author     : DO TAN TRUNG
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.BillStatus"%>
<%@page import="dao.ProductDao"%>
<%@page import="dao.BillDetailDao"%>
<%@page import="model.BillDetail"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="model.Bill"%>
<%@page import="dao.BillDao"%>
<%@page import="dao.UserDao"%>
<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Page Title</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
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
            User users = (User) session.getAttribute("users");
            final int MAX_ITEM = 10;
//            UserDao userDao = new UserDao();
            int currentPage;
            String keyword = "";
            if (request.getParameter("keyword") != null) {
                keyword = ((String) request.getParameter("keyword")).trim();
            }
            if (request.getParameter("page") == null || Integer.valueOf(request.getParameter("page")) < 1) {
                currentPage = 1;
            } else {
                currentPage = Integer.valueOf(request.getParameter("page"));
            }
            int count = BillDao.count();
            if (!keyword.isEmpty()) {
                count = BillDao.countBillOfSearch(keyword);
            }
            int pageStart, pageEnd;
            int lastPage = count / MAX_ITEM;
            if (count % MAX_ITEM > 0) {
                lastPage++;
            }

            if (currentPage > lastPage && lastPage > 0) {
                currentPage = lastPage;
            }
            if (currentPage < 3) {
                pageStart = 1;
                pageEnd = currentPage + 2 + (3 - currentPage);
            } else {
                pageStart = currentPage - 2;
                pageEnd = currentPage + 2;
            }
            if (pageEnd > lastPage) {
                pageStart = pageStart - (pageEnd - lastPage);
                if (pageStart < 1) {
                    pageStart = 1;
                }
                pageEnd = lastPage;
            }
            List<Bill> lBill = new ArrayList<>();

            if (!keyword.isEmpty()) {
                lBill = BillDao.getListBillBySearchLimit(keyword, (currentPage - 1) * MAX_ITEM, MAX_ITEM);
            } else {
                lBill = BillDao.getListBillByPage((currentPage - 1) * MAX_ITEM, MAX_ITEM);
            }

        %>
        <div class="container" style="padding: 1em;">
            <div class="row">
                <div style="float: right">
                    <input id="inputSearch" type="text" placeholder="search by username" 
                           <%if (!keyword.isEmpty())%>
                           value="<%=keyword%>">
                    <button id="btnSearch" type="button" class="btn btn-default" >Search</button>
                </div>
                <div >
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Date</th>
                                <th scope="col">Customer</th>
                                <th scope="col" >Products</th>
                                <th scope="col" style="width: 10em;">Total</th>
                                <th scope="col" style="width: 10em;">Status</th>
                                <th scope="col" style="width: 10em;">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Bill b : lBill) {%>
                            <tr>
                                <td>
                                    <span>#<%=b.getBillID()%></span>
                                </td>
                                <td>
                                    <%
                                        Timestamp ts = b.getDate();
                                        Date date = new Date();
                                        date.setTime(ts.getTime());
                                        String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
                                    %>
                                    <span><%=formattedDate%></span>
                                </td>
                                <td>
                                    <span><%=UserDao.loadUserById(b.getUserID()).getUsername()%></span>
                                </td>
                                <%
                                    List<BillDetail> lBillDetail = BillDetailDao.getBillDetails(b.getBillID());
                                    String name = "";
                                    if (lBillDetail.size() > 0) {
                                        name = ProductDao.getProduct(lBillDetail.get(0).getProductID()).getName();
                                    }
                                    if (lBillDetail.size() > 1) {
                                        name += " and " + (lBillDetail.size() - 1) + " other";
                                    }
                                %>
                                <td><span><%=name%></span></td>
                                <td><span><%=String.format("%.2f", b.getTotal())%></span></td>
                                <td>
                                    <%
                                        String status[] = BillStatus.status;
                                    %>
                                    <span><%=status[b.getStatus()]%></span>
                                </td>
                                <td>
                                    <a href="billdetail.jsp?billId=<%=b.getBillID()%>">View</a> |
                                    <a href="#" onclick='onDeleteClick("<b><%="#" + b.getBillID()%></b>will detete",<%=b.getBillID()%>)'>Delete</a>
                                </td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                    <nav aria-label="Page navigation">
                        <ul class="pagination justify-content-center">
                            <%if (currentPage > 1) {%>
                            <li class="page-item ">
                                <a class="page-link" 
                                   <%if (!keyword.isEmpty()) {%>
                                   href="billmanagement.jsp?page=1&keyword=<%=keyword.trim()%>"
                                   <%} else {%>
                                   href="billmanagement.jsp?page=1"
                                   <%}%>
                                   >First</a>
                            </li>
                            <%}%>
                            <%for (int i = pageStart; i <= pageEnd; i++) {%>
                            <li class="page-item <%if (i == currentPage) {%>active<%}%>">
                                <a class="page-link" 
                                   <%if (!keyword.isEmpty()) {%>
                                   href="billmanagement.jsp?page=<%=i%>&keyword=<%=keyword.trim()%>"
                                   <%} else {%>
                                   href="billmanagement.jsp?page=<%=i%>"
                                   <%}%>><%=i%></a>
                            </li>
                            <%}%>
                            
                            <%if (currentPage < lastPage) {%>
                            <li class="page-item ">
                                <a class="page-link" 
                                   <%if (!keyword.isEmpty()) {%>
                                   href="billmanagement.jsp?page=<%=lastPage%>&keyword=<%=keyword.trim()%>"
                                   <%} else {%>
                                   href="billmanagement.jsp?page=<%=lastPage%>"
                                   <%}%>
                                   >Last</a>
                            </li>
                            <%}%>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>

        <script>
            $("#btnSearch").click(function () {
                window.location.href = "billmanagement.jsp?keyword=" + $("#inputSearch").val();
            });
            function onDeleteClick(ms, id) {
                doMessageBox(ms, function () {
                    $.post("DeleteBill?billId=" + id)
                            .done(function () {
                                doMessageBox("Deleted!", function () {
                                    window.location.reload();
                                });
                            })
                            .fail(function () {
                                doMessageBox("Error!");
                            });
                });
            }
        </script>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
