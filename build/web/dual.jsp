<%-- 
    Document   : search
    Created on : Sep 6, 2018, 11:07:42 PM
    Author     : phuong nam
--%>

<%@page import="model.Categories"%>
<%@page import="dao.CategoriesDao"%>
<%@page import="model.Product"%>
<%@page import="dao.ProductDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>
    <body>
        <%
            ProductDao pr = new ProductDao();
            CategoriesDao cr = new CategoriesDao();
            //     Product p = new Product();
            String keyword="" ;
//            if (request.getParameter("keyword") != null) { 
//                p = pr.getListProductBySearch(keyword);
//            }
        %>
        <jsp:include page="header.jsp"></jsp:include>

            <!-- SECTION -->
            <div class="section">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <!-- ASIDE -->
                        <div id="aside" class="col-md-3">
                            <!-- aside Widget -->
                            <div class="aside">
                                <h3 class="aside-title">Categories</h3>
                                <div class="checkbox-filter">
                                    <%
                                        for (Categories c : cr.getMenu()) {

                                    %>
                                    <div>
                                        <li class="active"><a href="dual.jsp?keyword=<%=String.valueOf(c.getId())%>"><%=c.getName()%></a></li>
                                    </div>
                                    <%}%>
                                </div>
                            </div>
                    </div>
                    <!-- /ASIDE -->

                    <!-- STORE -->
                    <div id="store" class="col-md-9">
                        <!-- store products -->
                        <div class="row">
                            <%
                                for (Product p : pr.getListProductByIdParent(Integer.parseInt(request.getParameter("keyword")))) {
                            %>
                            <!-- product -->
                            <div class="col-md-4 col-xs-6">
                                <div class="product">
                                    <div class="product-img">
                                        <img src="./img/products/<%=p.getImage()%>" alt="">
                                        
                                        <div class="product-label">
                                            <%if (p.getPromotion_price() != 0) {%>
                                            <span class="sale">SALE</span>
                                            <%}%>
                                            <%if (p.getNew_product()== 1) {%>
                                            <span class="new">NEW</span>
                                            <%}%>
                                        </div>
                                    </div>
                                    <div class="product-body">
                                        <p class="product-category">Category</p>
                                        <h3 class="product-name"><a href="singgle.jsp?productID=<%=p.getId()%>"><%=p.getName()%></a></h3>
                                            <%if (p.getPromotion_price() != 0) {%>
                                        <h4 class="product-price">$<%=p.getPromotion_price()%> <del class="product-old-price">$<%=p.getValue()%></del></h4>
                                        <%}%>
                                        <%if (p.getPromotion_price() == 0) {%>
                                        <h4 class="product-price">$<%=p.getValue()%></h4>
                                        <%}%>
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
                            </div>
                            <%}%>
                            <!-- /product -->
                        </div>
                        <!-- /store products -->

                        <!-- store bottom filter -->
                        <!--<div class="store-filter clearfix">
                            <span class="store-qty">Showing 20-100 products</span>
                            <ul class="store-pagination">
                                <li class="active">1</li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#"><i class="fa fa-angle-right"></i></a></li>
                            </ul>
                        </div>
                        <!-- /store bottom filter -->
                    </div>
                    <!-- /STORE -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->

        <!-- NEWSLETTER -->
        <div id="newsletter" class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
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
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /NEWSLETTER 
        <jsp:include page="footer.jsp"></jsp:include>
        <!-- jQuery Plugins -->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/slick.min.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/jquery.zoom.min.js"></script>
        <script src="js/main.js"></script>
   </body>
</html>
