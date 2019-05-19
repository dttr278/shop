<%-- 
    Document   : header
    Created on : Aug 30, 2018, 10:30:25 AM
    Author     : phuong nam
--%>

<%@page import="dao.UserDao"%>
<%@page import="java.util.Map"%>
<%@page import="model.Item"%>
<%@page import="model.Cart"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Apple Store</title>

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
        <script src="js/jquery.min.js"></script>
    </head>
    <body>
        <%
            User users = null;
            UserDao userDao = new UserDao();
            if (session.getAttribute("users") != null) {
                users = (User) session.getAttribute("users");
            }
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
        %>
        <!-- HEADER -->
        <header>
            <!-- TOP HEADER -->
            <div id="top-header" class="navbar navbar-expand-sm" style="margin-bottom: 0">
                <div class="container">
                    <ul class="header-links pull-left">
                        <li><a href="#"><i class="fa fa-phone"></i> +0123456789</a></li>
                        <li><a href="#"><i class="fa fa-envelope-o"></i> electro@gmail.com</a></li>
                        <li><a href="#"><i class="fa fa-map-marker"></i> 92, Man Thien Street District 9, Ho Chi Minh City</a></li>
                    </ul>
                    <ul class="header-links pull-right navbar-nav mr-auto">
                        <%if (users != null) {%>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="myaccount.jsp"
                               id="navbarDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true"
                               aria-expanded="false">
                                <i class="fa fa-user"></i>
                                <%=users.getUsername()%>
                            </a>
                            <div class="dropdown-menu " style="background-color: #1E1F29;"
                                 aria-labelledby="navbarDropdown" >
                                <div style="padding-left: 1rem ">
                                    <a style="display: block;width: 100%;" class="dropdown-item" href="myaccount.jsp"> My account </a>
                                    <%if (("customer").equals(userDao.getRole(users.getId()))) {%>
                                    <a style="display: block;width: 100%;" class="dropdown-item" href="biillsOfUser.jsp">My bills </a>
                                    <%}%>
                                    <a style="display: block;width: 100%;" class="dropdown-item" href="changepassword.jsp">Change password </a>
                                </div>
                                <hr style="margin: 3px"/>
                                <%if (("admin").equals(userDao.getRole(users.getId()))) {%>
                                <div style="padding-left: 1rem ">
                                    <a style="display: block;width: 100%;" class="dropdown-item" href="tk.jsp">Statistical </a>
                                    <a style="display: block;width: 100%;" class="dropdown-item" href="usermanagement.jsp">Users </a>
                                </div>
                                <%}%>
                                <%if (("employee").equals(userDao.getRole(users.getId())) || ("admin").equals(userDao.getRole(users.getId()))) {%>
                                <div style="padding-left: 1rem ">
                                    <a style="display: block;width: 100%;" class="dropdown-item" href="productmanagement.jsp">Products </a>
                                    <a  style="display: block;width: 100%;" class="dropdown-item" href="billmanagement.jsp">Bills </a>
                                </div>
                                <%}%>

                            </div>
                        </li>
                        <li ><a style="color: #269abc" href="UserServlet?logout">Logout</a></li>
                            <%}%>
                            <%if (users == null) {%>
                        <li><a href="login.jsp"><i class="fa fa-user-o"></i> Login</a></li>
                        <li><a href="register.jsp"><i class="fa fa-user-plus"></i> Register</a></li>
                            <%}%>
                    </ul>
                </div>
            </div>
            <!-- /TOP HEADER -->

            <!-- MAIN HEADER -->
            <div id="header">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <!-- LOGO -->
                        <div class="col-md-3">
                            <div class="header-logo">
                                <a href="home.jsp" class="logo">
                                    <img src="./img/logo.png" alt="">
                                </a>
                            </div>
                        </div>
                        <!-- /LOGO -->

                        <!-- SEARCH BAR -->
                        <div class="col-md-6">
                            <div class="header-search">
                                <form method="get" action="search.jsp">
                                    <select class="input-select">
                                        <option value="">All Categories</option>
                                        <option value="IPHONE">IPHONE</option>
                                        <option value="MACBOOK">MACBOOK</option>
                                        <option value="IPAD">IPAD</option>
                                        <option value="">PHỤ KIỆN</option>
                                        <option value="IMAC">IMAC</option>
                                    </select>
                                    <input class="input" placeholder="Search here" name="keyword" value ="">
                                    <button class="search-btn" style="padding-top: 0.5px;" value =>Search</button>
                                </form>
                            </div>
                        </div>
                        <!-- /SEARCH BAR -->

                        <!-- ACCOUNT -->
                        <div class="col-md-3 clearfix">
                            <div class="header-ctn">
                                <!--                                 Wishlist 
                                                                <div>
                                                                    <a href="#">
                                                                        <i class="fa fa-heart-o"></i>
                                                                        <span>WishList</span>
                                                                    </a>
                                                                </div>
                                                                 /Wishlist -->

                                <!-- Cart -->
                                <div class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true" style="cursor: pointer">
                                        <i class="fa fa-shopping-cart"></i>
                                        <span>Cart</span>        
                                    </a>
                                    <div class="cart-dropdown">
                                        <%for (Map.Entry<Integer, Item> list : cart.getCartItems().entrySet()) {%>
                                        <div class="cart-list">
                                            <div class="product-widget">
                                                <div class="product-img">
                                                    <img src="./img/products/<%=list.getValue().getProduct().getImage()%>" alt="">
                                                </div>
                                                <div class="product-body">
                                                    <h3 class="product-name"><a href="singgle.jsp?productID=<%=list.getValue().getProduct().getId()%>"><%=list.getValue().getProduct().getName()%></a></h3>
                                                    <h4 class="product-price"><span class="qty"><%=list.getValue().getQty()%></span><%=list.getValue().getProduct().getValue()%></h4>
                                                </div>
                                                <a class="delete" href="CartServlet?command=remove&productID=<%=list.getValue().getProduct().getId()%>"><i class="fa fa-close"></i></a>
                                                <a class="plus" href="CartServlet?command=plus&productID=<%=list.getValue().getProduct().getId()%>"><i class="fa fa-plus-square"></i></a>
                                                <a class="minus" href="CartServlet?command=sub&productID=<%=list.getValue().getProduct().getId()%>"><i class="fa fa-minus-square"></i></a>
                                            </div>
                                        </div>

                                        <%}%>

                                        <div class="cart-summary">
                                            <!--<small>3 Item(s) selected</small>-->
                                            <h5>SUBTOTAL: $<%=cart.totalCart()%></h5>
                                        </div>
                                        <div class="cart-btns">

                                            <a href="checkout.jsp">Checkout  <i class="fa fa-arrow-circle-right"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <!-- /Cart -->

                                <!-- Menu Toogle -->
                                <div class="menu-toggle">
                                    <a href="#">
                                        <i class="fa fa-bars"></i>
                                        <span>Menu</span>
                                    </a>
                                </div>
                                <!-- /Menu Toogle -->
                            </div>
                        </div>
                        <!-- /ACCOUNT -->
                    </div>
                    <!-- row -->
                </div>
                <!-- container -->
            </div>
            <!-- /MAIN HEADER -->

        </header>
        <!-- /HEADER -->





