<%-- 
    Document   : categori
    Created on : May 24, 2019, 2:24:36 AM
    Author     : dttr2
--%>

<%@page import="dao.CategoriesDao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Categories"%>
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
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%
            int id;
            Object o = request.getParameter("id");
            Categories c = new Categories();
            c.setName("");
            c.setId(-1);
            c.setId_parent(-1);
            List<Categories> parent = new ArrayList<>();
            parent = CategoriesDao.getMenu();
            if (o != null) {
                id = Integer.valueOf((String) o);
                c = CategoriesDao.getCate(id);
            }
        %>
        <div class="container" style="padding: 1em;">
            <h2><%=c.getName()%></h2>
            <form method="POST" action="UpdateCategories" enctype='multipart/form-data'>
                <div class="form-group" style="visibility: hidden;">
                    <label for="id">Id:</label>
                    <input type="text" class="form-control" id="id"
                           name="id"
                           value="<%=c.getId()%>">
                </div>
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" class="form-control" id="name"
                           placeholder="Enter name" name="name"
                           value="<%=c.getName()%>">
                </div>
                <div class="form-group">
                    <label for="icon">Icon</label>
                    <img class="img-responsive" id="icon"
                         src="./img/products/<%=c.getIcon()%>"
                         alt="Product image" width="200" height="200">
                    <label class="btn btn-default btn-file" style="margin-top:
                           5px;">
                        Browse <input name="icon" type="file" style="display: none;" onchange="readURL(this);"  accept="image/*">
                    </label>
                </div>
                <div class="form-group">
                    <label for="parent">Parent:</label>
                    <select name="parent" class="form-control" id="parent">
                        <option value="-1" >None</option>
                        <%for (Categories ca : parent) {
                                if (c.getId_parent() == ca.getId()) {%>
                        <option value="<%=ca.getId()%>" selected><%=ca.getName()%></option>
                        <%} else {%>
                        <option value="<%=ca.getId()%>" ><%=ca.getName()%></option>
                        <%}
                            }%>
                    </select>
                </div>
                <button type="submit" class="btn btn-default">Save</button>
            </form>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
        <script>
            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        $('#icon')
                                .attr('src', e.target.result);
                    };

                    reader.readAsDataURL(input.files[0]);
                }
            }
        </script>
    </body>
</html>
