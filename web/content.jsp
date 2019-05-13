<%-- 
    Document   : content
    Created on : Aug 30, 2018, 10:49:24 AM
    Author     : phuong nam
--%>
<%@page import="model.Categories"%>
<%@page import="dao.CategoriesDao"%>
<%@page import="model.Cart"%>
<%@page import="model.Product"%>
<%@page import="model.User"%>
<%@page import="dao.ProductDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Electro - COW Ecommerce</title>

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
        <%ProductDao pr = new ProductDao();
          CategoriesDao cr = new CategoriesDao();
          User users = null;
            if (session.getAttribute("users") != null) {
                users = (User) session.getAttribute("users");
            }
          Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
        %>

        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <!-- shop -->
                    <%
                        for (Categories c : cr.getMenu()) {

                        %>
                    <div class="col-sm-4 ">
                        <div class="shop">
                            <div class="shop-img">
                                <img src="./img/products/<%=c.getIcon()%>" alt="">
                            </div>
                            <div class="shop-body">
                                <h3><%=c.getName()%><br>Collection</h3>
                                <a href="dual.jsp?keyword=<%=String.valueOf(c.getId())%>" class="cta-btn">Shop now <i class="fa fa-arrow-circle-right"></i></a>
                            </div>
                        </div>
                    </div>
                    <%}%>
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->

        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">

                    <!-- section title -->
                    <div class="col-md-12">
                        <div class="section-title">
                            <h3 class="title">NEW PRODUCTS</h3>  
                        </div>
                    </div>
                    <!-- /section title -->

                    <!-- Products tab & slick -->
                    <div class="col-md-12">
                        <div class="row">
                            <div class="products-tabs">
                                <div id="tab1" class="tab-pane active">
                                    <div class="products-slick" data-nav="#slick-nav-1">
                                        <%
                                            for (Product p : pr.getListProductByNew()) {


                                        %>
                                        <!-- product -->
                                        <div class="product">
                                            <div class="product-img">
                                                <img src="./img/products/<%=p.getImage()%>" alt="">
                                                <div class="product-label">
                                                    <span class="new">NEW</span>
                                                </div>
                                            </div>
                                            <div class="product-body">
                                                <p class="product-category">Category</p>
                                                <h3 class="product-name"><a href="singgle.jsp?productID=<%=p.getId()%>"><%=p.getName()%></a></h3>
                                                <h4 class="product-price">$<%=p.getValue()%></h4>
                                                <div class="product-rating">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </div>
                                                <div class="product-btns">
                                                    <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                    <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
                                                    <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                                </div>
                                            </div>
                                            <div class="add-to-cart">
                                                <a href="CartServlet?command=plus&productID=<%=p.getId()%>" style="color: white"><i class="fa fa-shopping-cart"></i> add to cart</a>
                                            </div>
                                        </div>
                                        <!-- /product -->
                                        <% }
                                        %>
                                    </div>
                                    <div id="slick-nav-1" class="products-slick-nav"></div>
                                </div>
                                <!-- /tab -->
                            </div>
                        </div>
                    </div>
                    <!-- Products tab & slick -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->

        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">

                    <!-- section title -->
                    <div class="col-md-12">
                        <div class="section-title">
                            <h3 class="title">SPECIAL PRODUCTS</h3>
                        </div>
                    </div>
                    <!-- /section title -->

                    <!-- Products tab & slick -->
                    <div class="col-md-12">
                        <div class="row">
                            <div class="products-tabs">
                                <!-- tab -->
                                <div id="tab2" class="tab-pane fade in active">
                                    <div class="products-slick" data-nav="#slick-nav-2">
                                        <%for (Product p : pr.getListProductBySpecial()) {
                                        %>
                                        <!-- product -->
                                        <div class="product">
                                            <div class="product-img">
                                                <img src="./img/products/<%=p.getImage()%>" alt="">
                                                <div class="product-label">
                                                    <span class="sale">SALE</span>
                                                    <span class="new">NEW</span>
                                                </div>
                                            </div>
                                            <div class="product-body">
                                                <p class="product-category">Category</p>
                                                <h3 class="product-name"><a href="singgle.jsp?productID=<%=p.getId()%>"><%=p.getName()%></a></h3>
                                                <h4 class="product-price">$<%=p.getPromotion_price()%> <del class="product-old-price">$<%=p.getValue()%></del></h4>
                                                <div class="product-rating">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </div>
                                                <div class="product-btns">
                                                    <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                    <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
                                                    <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                                </div>
                                            </div>
                                            <div class="add-to-cart">
                                                <a href="CartServlet?command=plus&productID=<%=p.getId()%>"><i class="fa fa-shopping-cart"></i> add to cart</a>

                                            </div>
                                        </div>
                                        <%}%>
                                        <!-- /product -->                                  
                                    </div>
                                    <div id="slick-nav-2" class="products-slick-nav"></div>
                                </div>
                                <!-- /tab -->
                            </div>
                        </div>
                    </div>
                    <!-- /Products tab & slick -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->

        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">

                    <!-- section title -->
                    <div class="col-md-12">
                        <div class="section-title">
                            <h3 class="title">DISCOUNT PRODUCTS</h3>
                        </div>
                    </div>
                    <!-- /section title -->

                    <!-- Products tab & slick -->
                    <div class="col-md-12">
                        <div class="row">
                            <div class="products-tabs">
                                <!-- tab -->
                                <div id="tab2" class="tab-pane fade in active">
                                    <div class="products-slick" data-nav="#slick-nav-3">
                                        <%for (Product p : pr.getListProductByPromotionPrice()) {
                                        %>
                                        <!-- product -->
                                        <div class="product">
                                            <div class="product-img">
                                                <img src="./img/products/<%=p.getImage()%>" alt="">
                                                <div class="product-label">
                                                    <span class="sale">SALE</span>                                               
                                                </div>
                                            </div>
                                            <div class="product-body">
                                                <p class="product-category">Category</p>
                                                <h3 class="product-name"><a href="singgle.jsp?productID=<%=p.getId()%>"><%=p.getName()%></a></h3>
                                                <h4 class="product-price">$<%=p.getPromotion_price()%> <del class="product-old-price">$<%=p.getValue()%></del></h4>
                                                <div class="product-rating">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </div>
                                                <div class="product-btns">
                                                    <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                    <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
                                                    <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                                </div>
                                            </div>
                                            <div class="add-to-cart">
                                                <a href="CartServlet?command=plus&productID=<%=p.getId()%>"><i class="fa fa-shopping-cart"></i> add to cart</a>

                                            </div>
                                        </div>
                                        <!-- /product -->
                                        <%}
                                        %>
                                    </div>
                                    <div id="slick-nav-3" class="products-slick-nav"></div>
                                </div>
                                <!-- /tab -->
                            </div>
                        </div>
                    </div>
                    <!-- /Products tab & slick -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->
        <!-- HOT DEAL SECTION -->
<!--        <div id="hot-deal" class="section">
             container 
            <div class="container">
                 row 
                <div class="row">
                    <div class="col-md-12">
                        <div class="hot-deal">
                            <ul class="hot-deal-countdown">
                                <li>
                                    <div>
                                        <h3>02</h3>
                                        <span>Days</span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        <h3>10</h3>
                                        <span>Hours</span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        <h3>34</h3>
                                        <span>Mins</span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        <h3>60</h3>
                                        <span>Secs</span>
                                    </div>
                                </li>
                            </ul>
                            <h2 class="text-uppercase">hot deal this week</h2>
                            <p>New Collection Up to 30% OFF</p>
                            <a class="primary-btn cta-btn" href="#">Shop now</a>
                        </div>
                    </div>
                </div>
                 /row 
            </div>
             /container 
        </div>-->
        <!-- /HOT DEAL SECTION -->
        <!-- NEWSLETTER -->
<!--        <div id="newsletter" class="section">
             container 
            <div class="container">
                 row 
                <div class="row">
                    <div class="col-md-12">
                        <div class="newsletter">
                            <p>Sign Up for the <strong>NEWSLETTER</strong></p>
                            <form>
                                <input class="input" type="email" placeholder="Enter Your Email">
                                <button class="newsletter-btn"><i class="fa fa-envelope"></i> Subscribe</button>
                            </form>
                            <ul class="newsletter-follow">
                                <li>
                                    <a href="https://www.facebook.com/tientrung.lam.7" target="_blank"><i class="fa fa-facebook"></i></a>
                                </li>
                                <li>
                                    <a href="https://twitter.com/TrungLa99812708" target="_blank"><i class="fa fa-twitter"></i></a>
                                </li>
                                <li>
                                    <a href="https://www.instagram.com/trung.cow/" target="_blank"><i class="fa fa-instagram"></i></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                 /row 
            </div>
             /container 
        </div>-->
        <!-- /NEWSLETTER -->

        <!-- FOOTER -->

        <!-- /FOOTER -->

        <!-- jQuery Plugins -->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/slick.min.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/jquery.zoom.min.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>
