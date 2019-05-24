<%-- 
    Document   : categories
    Created on : May 24, 2019, 1:39:09 AM
    Author     : dttr2
--%>

<%@page import="model.Categories"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.CategoriesDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            final int MAX_ITEM = 10;
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
            int count = CategoriesDao.count();
            if (!keyword.isEmpty()) {
                count = CategoriesDao.countOfSearch(keyword);
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
            List<Categories> lc = new ArrayList<>();

            if (!keyword.isEmpty()) {
                lc = CategoriesDao.getListBySearchLimit(keyword, (currentPage - 1) * MAX_ITEM, MAX_ITEM);
            } else {
                lc = CategoriesDao.getListByPage((currentPage - 1) * MAX_ITEM, MAX_ITEM);
            }
//            List<Categories> lparent = CategoriesDao.getMenu();
        %>
        <div class="container" style="padding: 1em;">
            <div class="row">
                <button type="button" class="btn btn-default" style="margin-bottom: 10px;">
                    <a href="categori.jsp"><span class="glyphicon glyphicon-plus"> new</span></a>
                </button>
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
                                <th scope="col">Name</th>
                                <th scope="col">Parent</th>
                                <th scope="col" style="width: 10em;">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Categories c : lc) {%>
                            <tr>
                                <td>
                                    <span>#<%=c.getId()%></span>
                                </td>
                                <td>
                                    <span><%=c.getName()%></span>
                                </td>
                                <td>
                                    <%
                                        String parent = "";
                                        if (c.getId_parent() != null) {
                                            Categories ct = CategoriesDao.getCate(c.getId_parent());
                                            parent = ct.getName();
                                        }
                                    %>
                                    <span><%=parent%></span>
                                </td>


                                <td>
                                    <a href="categori.jsp?id=<%=c.getId()%>">View</a> 
                                    <!--<a href="#" onclick='onDeleteClick("<b><%="#" + c.getName()%></b>will detete",<%=c.getId()%>)'>Delete</a>-->
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
                                   href="categories.jsp?page=1&keyword=<%=keyword.trim()%>"
                                   <%} else {%>
                                   href="categories.jsp?page=1"
                                   <%}%>
                                   >First</a>
                            </li>
                            <%}%>
                            <%for (int i = pageStart; i <= pageEnd; i++) {%>
                            <li class="page-item <%if (i == currentPage) {%>active<%}%>">
                                <a class="page-link" 
                                   <%if (!keyword.isEmpty()) {%>
                                   href="categories.jsp?page=<%=i%>&keyword=<%=keyword.trim()%>"
                                   <%} else {%>
                                   href="categories.jsp?page=<%=i%>"
                                   <%}%>><%=i%></a>
                            </li>
                            <%}%>

                            <%if (currentPage < lastPage) {%>
                            <li class="page-item ">
                                <a class="page-link" 
                                   <%if (!keyword.isEmpty()) {%>
                                   href="categories.jsp?page=<%=lastPage%>&keyword=<%=keyword.trim()%>"
                                   <%} else {%>
                                   href="categories.jsp?page=<%=lastPage%>"
                                   <%}%>
                                   >Last</a>
                            </li>
                            <%}%>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
        <script>
            $("#btnSearch").click(function () {
                window.location.href = "categories.jsp?keyword=" + $("#inputSearch").val();
            });
            function onDeleteClick(ms, id) {
                doMessageBox(ms, function () {
                    $.post("DeleteCategories?id=" + id)
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
    </body>
</html>
