<%-- 
    Document   : biillsOfUser
    Created on : Apr 25, 2019, 10:39:59 PM
    Author     : DO TAN TRUNG
--%>

<%@page import="model.BillStatus"%>
<%@page import="dao.ProductDao"%>
<%@page import="dao.BillDetailDao"%>
<%@page import="model.BillDetail"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.List"%>
<%@page import="model.Bill"%>
<%@page import="dao.BillDao"%>
<%@page import="model.User"%>
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
        <%
            User users = (User) session.getAttribute("users");
            final int MAX_ITEM = 10;
//            UserDao userDao = new UserDao();
            int currentPage;

            if (request.getParameter("page") == null || Integer.valueOf(request.getParameter("page")) < 1) {
                currentPage = 1;
            } else {
                currentPage = Integer.valueOf(request.getParameter("page"));
            }
            int count = BillDao.countBillOfUser(users.getId());

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
            List<Bill> lBill;
            lBill = BillDao.getPageBillOfUser(users.getId(), (currentPage - 1) * MAX_ITEM, MAX_ITEM);

        %>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container">
                <div class="row">                   
                    <div id="mainContent" >
                        <div >
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Id</th>
                                        <th scope="col">Date order</th>
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
                                        <span><%=b.getBillID()%></span>
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
                                        <a href="billdetail.jsp?billId=<%=b.getBillID()%>">View</a>
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
                                       href="biillsOfUser.jsp?page=1"
                                       >First</a>
                                </li>
                                <%}%>

                                <%for (int i = pageStart; i <= pageEnd; i++) {%>
                                <li class="page-item <%if (i == currentPage) {%>active<%}%>">
                                    <a class="page-link" 
                                       href="biillsOfUser.jsp?page=<%=i%>"><%=i%></a>
                                </li>
                                <%}%>
                                
                                <%if (currentPage < lastPage) {%>
                                <li class="page-item ">
                                    <a class="page-link" 
                                       href="biillsOfUser.jsp?page=<%=lastPage%>">Last</a>
                                </li>
                                <%}%>
                            </ul>
                        </nav>
                    </div>

                </div>
            </div>
        </div>
    </script>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
