<%-- 
    Document   : productdetail
    Created on : Mar 29, 2019, 12:04:53 AM
    Author     : DO TAN TRUNG
--%>

<%@page import="dao.UserDao"%>
<%@page import="model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dao.ProductDao"%>
<%@page import="model.Product"%>
<%@page import="dao.CategoriesDao"%>
<%@page import="model.Categories"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Page Title</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" media="screen" href="main.css">
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
    <body >
        <jsp:include page="header.jsp"></jsp:include>
        <%
            User users = null;
            users = (User) session.getAttribute("users");
            int productId;
            Object o = request.getParameter("productId");
            ProductDao productDao = new ProductDao();
            CategoriesDao categoriesDao = new CategoriesDao();
            Product product = new Product();
            product.setName("");
            product.setId(-1);
            product.setDetail("");
            product.setPromotion("");
            List<Categories> categorieses = new ArrayList<>();
            categorieses = categoriesDao.getAll();
            if (o != null) {
                productId = Integer.valueOf((String) o);
                product = productDao.getProduct(productId);
            }
        %>
        <div class="container" style="padding: 1em;">
            <h2><%=product.getName()%></h2>
            <form method="POST" action="UpdateProduct" enctype='multipart/form-data'>
                <div class="form-group" style="visibility: hidden;">
                    <label for="id">Id:</label>
                    <input type="text" class="form-control" id="id"
                           name="id"
                           value="<%=product.getId()%>">
                </div>
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" class="form-control" id="name"
                           placeholder="Enter name" name="name"
                           value="<%=product.getName()%>">
                </div>
                <div class="form-group">
                    <label for="image">Image:</label>
                    <img class="img-responsive" id="product-image"
                         src="./img/products/<%=product.getImage()%>"
                         alt="Product image" width="200" height="200">
                    <label class="btn btn-default btn-file" style="margin-top:
                           5px;">
                        Browse <input name="image" type="file" style="display: none;" onchange="readURL(this);"  accept="image/*">
                    </label>
                </div>
                <div class="form-group">
                    <label for="type">Select type:</label>
                    <select name="type" class="form-control" id="type">
                        <%for (Categories c : categorieses) {
                                if (c.getId_parent() > 0) {
                                    continue;
                                }
                                if (c.getId() == product.getId_type()) {%>
                        <option value="<%=c.getId()%>" selected><%=c.getName()%></option>
                        <%} else {%>
                        <option value="<%=c.getId()%>" ><%=c.getName()%></option>
                        <%}
                            }%>
                    </select>
                </div>
                <div class="form-group">
                    <label for="amount">Amount:</label>
                    <input name="amount" type="number" class="form-control" id="amount"
                           placeholder="Enter value" 
                           value="<%=product.getAmount()%>">
                </div>
                <div class="form-group">
                    <label for="value">Value:</label>
                    <input name="value" type="number" class="form-control" id="value"
                           placeholder="Enter value" 
                           value="<%=String.format("%.2f", product.getValue())%>">
                </div>
                <div class="form-group">
                    <label  for="detail">Detail:</label>
                    <textarea name="detail" class="form-control" rows="5" id="detail"><%=product.getDetail()%></textarea>
                </div>
                <div class="form-group">
                    <label for="promotion">Promotion value:</label>
                    <input name="promotionValue" type="number" class="form-control" id="promotion"
                           placeholder="Enter promotion value" 
                           value="<%=String.format("%.2f", product.getPromotion_price())%>">
                </div>
                <div class="form-group">
                    <label  for="promotion">Promotion:</label>
                    <textarea name="promotion" class="form-control" rows="5" id="promotion"><%=product.getPromotion()%></textarea>
                </div>
                <button type="submit" class="btn btn-default">Save</button>
            </form>
        </div>
        <script>
            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        $('#product-image')
                                .attr('src', e.target.result);
                    };

                    reader.readAsDataURL(input.files[0]);
                }
            }
        </script>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
