<%-- 
    Document   : checkout
    Created on : Sep 16, 2018, 3:34:23 PM
    Author     : phuong nam
--%>

<%@page import="dao.ProductDao"%>
<%@page import="model.Product"%>
<%@page import="java.util.Map"%>
<%@page import="model.Item"%>
<%@page import="java.util.List"%>
<%@page import="model.BillDetail"%>
<%@page import="model.BillDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Cart"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        User u = (User) session.getAttribute("users");
        Cart cart = (Cart) session.getAttribute("cart");
        List<BillDetail> lb = new ArrayList<>();
        for (Map.Entry<Integer, Item> list : cart.getCartItems().entrySet()) {
            lb.add(new BillDetail(-1, -1,
                    list.getValue().getProduct().getId(),
                    list.getValue().getProduct().getValue(),
                    list.getValue().getQty()));
        }
        if (cart == null || cart.getCartItems().size() < 1) {
            response.sendRedirect("home.jsp");
            return;
        }
    %>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Electro - HTML Ecommerce Template</title>

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

        <!-- Slick -->
        <link type="text/css" rel="stylesheet" href="css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>

        <!-- nouislider -->
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>

        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="css/font-awesome.min.css">

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style.css"/>

    </head>
    <body>

        <jsp:include page="header.jsp"></jsp:include>
            <!-- BREADCRUMB -->
            <div id="breadcrumb" class="section">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <div class="col-md-12">
                            <h3 class="breadcrumb-header">Checkout</h3>
                            <ul class="breadcrumb-tree">
                                <li><a href="home.jsp">Home</a></li>
                                <li class="active">Checkout</li>
                            </ul>
                        </div>
                    </div>
                    <!-- /row -->
                </div>
                <!-- /container -->
            </div>
            <!-- /BREADCRUMB -->

            <!-- SECTION -->
            <div class="section">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">

                        <div >
                            <!-- Billing Details -->
                            <div class="billing-details">
                                <div class="section-title">
                                    <h3 class="title">Billing address</h3>
                                </div>

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
                                        for (BillDetail bd : lb) {
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
                            </div>
                            <form action="CheckOutServlet" method="POST" >
                                <div class="form-group">
                                    <input class="input" type="text" name="address" placeholder="Address" value="<%=u.getAddress() == null ? "" : u.getAddress()%>">
                                </div>
                                <div class="form-group">
                                    <input class="input" type="text" name="phone" placeholder="phone" value="<%=u.getPhone() == null ? "" : u.getPhone()%>">
                                </div>
                                <div class="form-group">
                                    <label for="payment">Payment method</label>
                                    <div class="radio">
                                        <label><input type="radio" name="payment" value="0" checked>Direct</label>
                                    </div>
                                    <div class="radio">
                                        <label><input type="radio" name="payment" value="1">Online</label>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-success ">Checkout</button>
                            </form>
                        </div>
                        <!-- /Billing Details -->
                    </div>


                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>

        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
