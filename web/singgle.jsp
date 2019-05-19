<%-- 
Document   : singgle
Created on : Sep 6, 2018, 9:08:08 AM
Author     : phuong nam
--%>
<%@page import="dao.CategoriesDao"%>
<%@page import="model.Categories"%>
<%@page import="model.Product"%>
<%@page import="dao.ProductDao"%>
<%@page import="model.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta property="fb:app_id" content="356830451834317" />
        <meta property="fb:admins" content="100004320510236"/>
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

        <style>
            .abab:hover {
                -ms-transform: scale(4.5); /* IE 9 */
                -webkit-transform: scale(4.5); /* Safari 3-8 */
                transform: scale(4.5); 
            }
        </style>
    </head>
    <body>
        <%
            CategoriesDao cr = new CategoriesDao();
            ProductDao pr = new ProductDao();
            Product p = new Product();
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
            String productID = "";
            if (request.getParameter("productID") != null) {
                productID = request.getParameter("productID");
                p = pr.getProduct(Integer.parseInt(productID));
            }
        %>
        <jsp:include page="header.jsp"></jsp:include>
            <!-- BREADCRUMB -->
            <div id="navbar" class="navbar-collapse collapse" aria-expanded="false" style="height: 1px;">
                <ul class="nav navbar-nav" style=";color: red">
                <%
                    for (Categories c : cr.getMenu()) {
                %>
                <li class="active"><a href="dual.jsp?keyword=<%=String.valueOf(c.getId())%>"><%=c.getName()%></a></li>
                    <%}%>
            </ul>
        </div><!--/.nav-collapse -->
        <div id="breadcrumb" class="section"></div>      
        <!-- /BREADCRUMB -->

        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <!--Product main img--> 
                    <div class="col-md-5 col-md-push-2">    
                        <div id="product-main-img">
                            <div class="product-preview">
                                <img class="abab" src="./img/products/<%=p.getImage()%>" alt="">
                            </div>                        
                        </div>
                    </div>
                    <!--Product main img--> 

                    <!-- Product thumb imgs -->
                    <div class="col-md-2  col-md-pull-5"></div>                    
                    <!-- /Product thumb imgs -->

                    <!-- Product details -->
                    <div class="col-md-5">
                        <div class="product-details">
                            <h2 class="product-name"><%=p.getName()%></h2>
                            <div>
                                <div class="product-rating">
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star-o"></i>
                                </div>
                                <!--<a class="review-link" href="#">10 Review(s) | Add your review</a>-->
                            </div>
                            <div>
                                <%if (p.getPromotion_price() != 0) {%>
                                <h3 class="product-price">$<%=p.getPromotion_price()%><del class="product-old-price">$<%=p.getValue()%></del></h3>
                                <%}%>
                                <%if (p.getPromotion_price() == 0) {%>

                                <h3 class="product-price">$<%=p.getValue()%></h3>

                                <%}%>

                            </div>
                            <p><%=p.getDetail()%></p>

                            <%if (p.getAmount() > 0) {%>
                            <div class="add-to-cart">
                                <a href="CartServlet?command=plus&productID=<%=p.getId()%>"><i class="fa fa-shopping-cart"></i> add to cart</a>
                            </div>
                            <%} else {%>
                            <div class="add-to-cart">
                                Out of stock
                            </div>
                            <%}%>
                            <ul class="product-btns">
                                <!--<li><a href="#"><i class="fa fa-heart-o"></i> add to wishlist</a></li>
                                <li><a href="#"><i class="fa fa-exchange"></i> add to compare</a></li>-->
                            </ul>

                            <ul class="product-links">
                                <li>Category:</li>
                                    <% Categories c = cr.getCate(cr.getIdParent(p.getId()));%>
                                <li><a href="dual.jsp?keyword=<%=String.valueOf(c.getId())%>"><%=c.getName()%></a></li>
                            </ul>

                            <ul class="product-links">
                                <li>Share:</li>
                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                <li><a href="#"><i class="fa fa-envelope"></i></a></li>
                            </ul>

                        </div>
                    </div>
                    <!-- /Product details -->

                    <!-- Product tab -->
                    <div class="col-md-12">
                        <div id="product-tab">
                            <!-- product tab nav -->
                            <ul class="tab-nav">
                                <li class="active"><a data-toggle="tab" href="#tab1">DETAIL</a></li>
                            </ul>
                            <!-- /product tab nav -->

                            <!-- product tab content -->
                            <div class="tab-content">
                                <!-- tab1  -->  
                                <div id="tab1" class="tab-pane fade in active">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <pre><%='\t' + p.getDetail()%></pre>
                                        </div>
                                    </div>
                                </div>
                                <!-- /tab1  -->                      
                            </div>
                            <!-- /product tab content  -->
                        </div>
                    </div>
                    <!-- /product tab -->
                    <!-- Comment tab -->
                    <div class="col-md-12">
                        <div id="product-tab">
                            <ul class="tab-nav">
                                <li class="active"><a data-toggle="tab" href="#tab1">Comment</a></li>
                            </ul>
                            <div class="tab-content">
                                <!--                                <div class="form-group row">
                                                                    <div class="col-sm-10 ">
                                                                        <textarea class="form-control" rows="2" id="comment" ></textarea> 
                                                                    </div>
                                                                    <div class="col-sm-2"> 
                                                                        <button type="submit" class="btn btn-read btn-lg btn-block" name="btnComment" id="btnComment">
                                                                            Send
                                                                        </button>  
                                                                    </div>
                                                                </div>  
                                <div id="fb-root"></div>-->
                                <div id="fb-root"></div>
                                <script async defer crossorigin="anonymous" src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v3.3&appId=356830451834317&autoLogAppEvents=1"></script>
                                <div class="fb-comments" data-href="https://developers.facebook.com/docs/plugins/comments#configurator" data-width="100%" data-numposts="5"></div>
                            </div>
                            <!-- /product tab content  -->
                        </div>
                    </div>
                    <!-- /Comment tab -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->

        <!-- Section -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">

                    <div class="col-md-12">
                        <div class="section-title text-center">
                            <h3 class="title">RELATION PRODUCT</h3>
                        </div>
                    </div>
                    <%
                        for (Product product : pr.getListProductByRelated(p.getId_type())) {
                    %>
                    <!-- product -->
                    <div class="col-md-3 col-xs-6">
                        <div class="product">
                            <div class="product-img">
                                <img src="./img/products/<%=product.getImage()%>" alt="">
                                <div class="product-label">
                                    <%if (product.getNew_product() == 1) {%>
                                    <span class="new">NEW</span>
                                    <%}%>
                                    <%if (product.getPromotion_price() != 0) {%>
                                    <span class="new">SALE</span>
                                    <%}%>
                                </div>
                            </div>
                            <div class="product-body">
                                <p class="product-category">Category</p>
                                <h3 class="product-name"><a href="singgle.jsp?productID=<%=product.getId()%>"><%=product.getName()%></a></h3>
                                    <%if (product.getPromotion_price() != 0) {%>
                                <h4 class="product-price">$<%=product.getPromotion_price()%><del class="product-old-price">$<%=product.getValue()%></del></h4>
                                <%}%>
                                <%if (product.getPromotion_price() == 0) {%>
                                <h4 class="product-price">$<%=product.getValue()%></h4>
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
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /Section -->

        <jsp:include page="footer.jsp"></jsp:include>

        <!-- jQuery Plugins -->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/slick.min.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/jquery.zoom.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/jquery.pan.js"></script>

        <script>
            $(".abab").pan();
        </script>
    </body>

</html>

