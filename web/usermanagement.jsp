<%-- 
    Document   : productmanagement
    Created on : Mar 29, 2019, 20:51:59 AM
    Author     : DO TAN TRUNG
--%>

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
        <link rel="stylesheet" type="text/css" media="screen" href="main.css">
        <script src="main.js"></script>
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
            UserDao userDao = new UserDao();
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
            int count = userDao.count();
            if (!keyword.isEmpty()) {
                count = userDao.getCountSearch(keyword);
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
            List<User> lUser;
            if (!keyword.isEmpty()) {
                lUser = userDao.getListUserBySearchLimit(keyword, (currentPage - 1) * MAX_ITEM, MAX_ITEM);
            } else {
                lUser = userDao.getListUserByPage((currentPage - 1) * MAX_ITEM, MAX_ITEM);
            }
        %>
        <div class="container" style="padding: 1em;">
            <div class="row">
                <div >
                    <button type="button" class="btn btn-default" style="margin-bottom: 10px;">
                        <a href="userdetailadm.jsp"><span class="glyphicon glyphicon-plus"> new</span></a>
                    </button>
                    <div style="float: right">
                        <input id="inputSearch" type="text" placeholder="search" 
                               <%if (!keyword.isEmpty())%>
                               value="<%=keyword%>">
                        <button id="btnSearch" type="button" class="btn btn-default" >
                            Search  
                        </button>
                    </div>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col" style="width: 10em;">Role</th>
                                <th scope="col" style="width: 10em;">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <%for (User u : lUser) {%>
                            <tr>
                                <td>
                                    <span><%=u.getUsername()%></span>
                                </td>
                                <td>
                                    <%
                                        String r = userDao.getRole(u.getId());
                                        if (r == null) {
                                            r = "customer";
                                        }%>
                                    <span><%=r%></span>
                                </td>
                                <td>
                                    <a href="userdetailadm.jsp?userId=<%=u.getId()%>">Edit</a> |
                                    <a href="#" onclick='onDeleteClick("<b><%=u.getUsername()%></b>will detete",<%=u.getId()%>)'>Delete</a>
                                </td>
                            </tr>
                            <%}%>
                            </tr>
                        </tbody>
                    </table>
                    <nav aria-label="Page navigation">
                        <ul class="pagination justify-content-center">
                            <%if (currentPage > 1) {%>
                            <li class="page-item ">
                                <a class="page-link" 
                                   <%if (!keyword.isEmpty()) {%>
                                   href="usermanagement.jsp?page=1&keyword=<%=keyword.trim()%>"
                                   <%} else {%>
                                   href="usermanagement.jsp?page=1"
                                   <%}%>
                                   >First</a>
                            </li>
                            <%}%>
                            <%for (int i = pageStart; i <= pageEnd; i++) {%>
                            <li class="page-item <%if (i == currentPage) {%>active<%}%>">
                                <a class="page-link" 
                                   <%if (!keyword.isEmpty()) {%>
                                   href="usermanagement.jsp?page=<%=i%>&keyword=<%=keyword.trim()%>"
                                   <%} else {%>
                                   href="usermanagement.jsp?page=<%=i%>"
                                   <%}%>><%=i%></a>
                            </li>
                            <%}%>
                            <%if (currentPage < lastPage) {%>
                            <li class="page-item ">
                                <a class="page-link" 
                                   <%if (!keyword.isEmpty()) {%>
                                   href="usermanagement.jsp?page=<%=lastPage%>&keyword=<%=keyword.trim()%>"
                                   <%} else {%>
                                   href="usermanagement.jsp?page=<%=lastPage%>"
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
                window.location.href = "usermanagement.jsp?keyword=" + $("#inputSearch").val();
            });
            function onDeleteClick(ms, userId) {
                doMessageBox(ms, function () {
                    $.post("DeleteUser?userId=" + userId)
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