package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import dao.CategoriesDao;
import model.Categories;
import model.Product;
import dao.ProductDao;
import model.Cart;

public final class singgle_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("<!--        <meta property=\"fb:app_id\" content=\"356830451834317\" />\n");
      out.write("        <meta property=\"fb:admins\" content=\"100004320510236\"/>-->\n");
      out.write("        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->\n");
      out.write("\n");
      out.write("        <title>Electro - HTML Ecommerce Template</title>\n");
      out.write("\n");
      out.write("        <!-- Google font -->\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,500,700\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        <!-- Bootstrap -->\n");
      out.write("        <link type=\"text/css\" rel=\"stylesheet\" href=\"css/bootstrap.min.css\"/>\n");
      out.write("\n");
      out.write("        <!-- Slick -->\n");
      out.write("        <link type=\"text/css\" rel=\"stylesheet\" href=\"css/slick.css\"/>\n");
      out.write("        <link type=\"text/css\" rel=\"stylesheet\" href=\"css/slick-theme.css\"/>\n");
      out.write("\n");
      out.write("        <!-- nouislider -->\n");
      out.write("        <link type=\"text/css\" rel=\"stylesheet\" href=\"css/nouislider.min.css\"/>\n");
      out.write("\n");
      out.write("        <!-- Font Awesome Icon -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/font-awesome.min.css\">\n");
      out.write("\n");
      out.write("        <!-- Custom stlylesheet -->\n");
      out.write("        <link type=\"text/css\" rel=\"stylesheet\" href=\"css/style.css\"/>\n");
      out.write("\n");
      out.write("        <style>\n");
      out.write("            .abab:hover {\n");
      out.write("                -ms-transform: scale(4.5); /* IE 9 */\n");
      out.write("                -webkit-transform: scale(4.5); /* Safari 3-8 */\n");
      out.write("                transform: scale(4.5); \n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

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
        
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("\n");
      out.write("            <!-- BREADCRUMB -->\n");
      out.write("            <div id=\"navbar\" class=\"navbar-collapse collapse\" aria-expanded=\"false\" style=\"height: 1px;\">\n");
      out.write("                <ul class=\"nav navbar-nav\" style=\";color: red\">\n");
      out.write("                ");

                    for (Categories c : cr.getMenu()) {
                
      out.write("\n");
      out.write("                <li class=\"active\"><a href=\"dual.jsp?keyword=");
      out.print(String.valueOf(c.getId()));
      out.write('"');
      out.write('>');
      out.print(c.getName());
      out.write("</a></li>\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("            </ul>\n");
      out.write("        </div><!--/.nav-collapse -->\n");
      out.write("        <div id=\"breadcrumb\" class=\"section\"></div>      \n");
      out.write("        <!-- /BREADCRUMB -->\n");
      out.write("\n");
      out.write("        <!-- SECTION -->\n");
      out.write("        <div class=\"section\">\n");
      out.write("            <!-- container -->\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <!-- row -->\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <!--Product main img--> \n");
      out.write("                    <div class=\"col-md-5 col-md-push-2\">    \n");
      out.write("                        <div id=\"product-main-img\">\n");
      out.write("                            <div class=\"product-preview\">\n");
      out.write("                                <img class=\"abab\" src=\"./img/products/");
      out.print(p.getImage());
      out.write("\" alt=\"\">\n");
      out.write("                            </div>                        \n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <!--Product main img--> \n");
      out.write("\n");
      out.write("                    <!-- Product thumb imgs -->\n");
      out.write("                    <div class=\"col-md-2  col-md-pull-5\"></div>                    \n");
      out.write("                    <!-- /Product thumb imgs -->\n");
      out.write("\n");
      out.write("                    <!-- Product details -->\n");
      out.write("                    <div class=\"col-md-5\">\n");
      out.write("                        <div class=\"product-details\">\n");
      out.write("                            <h2 class=\"product-name\">");
      out.print(p.getName());
      out.write("</h2>\n");
      out.write("                            <div>\n");
      out.write("                                <div class=\"product-rating\">\n");
      out.write("                                    <i class=\"fa fa-star\"></i>\n");
      out.write("                                    <i class=\"fa fa-star\"></i>\n");
      out.write("                                    <i class=\"fa fa-star\"></i>\n");
      out.write("                                    <i class=\"fa fa-star\"></i>\n");
      out.write("                                    <i class=\"fa fa-star-o\"></i>\n");
      out.write("                                </div>\n");
      out.write("                                <!--<a class=\"review-link\" href=\"#\">10 Review(s) | Add your review</a>-->\n");
      out.write("                            </div>\n");
      out.write("                            <div>\n");
      out.write("                                ");
if (p.getPromotion_price() != 0) {
      out.write("\n");
      out.write("                                <h3 class=\"product-price\">$");
      out.print(p.getPromotion_price());
      out.write("<del class=\"product-old-price\">$");
      out.print(p.getValue());
      out.write("</del></h3>\n");
      out.write("                                ");
}
      out.write("\n");
      out.write("                                ");
if (p.getPromotion_price() == 0) {
      out.write("\n");
      out.write("\n");
      out.write("                                <h3 class=\"product-price\">$");
      out.print(p.getValue());
      out.write("</h3>\n");
      out.write("\n");
      out.write("                                ");
}
      out.write("\n");
      out.write("\n");
      out.write("                            </div>\n");
      out.write("                            <p>");
      out.print(p.getDetail());
      out.write("</p>\n");
      out.write("\n");
      out.write("                            ");
if (p.getAmount() > 0) {
      out.write("\n");
      out.write("                            <div class=\"add-to-cart\">\n");
      out.write("                                <a href=\"CartServlet?command=plus&productID=");
      out.print(p.getId());
      out.write("\"><i class=\"fa fa-shopping-cart\"></i> add to cart</a>\n");
      out.write("                            </div>\n");
      out.write("                            ");
} else {
      out.write("\n");
      out.write("                            <div class=\"add-to-cart\">\n");
      out.write("                                Out of stock\n");
      out.write("                            </div>\n");
      out.write("                            ");
}
      out.write("\n");
      out.write("                            <ul class=\"product-btns\">\n");
      out.write("                                <!--<li><a href=\"#\"><i class=\"fa fa-heart-o\"></i> add to wishlist</a></li>\n");
      out.write("                                <li><a href=\"#\"><i class=\"fa fa-exchange\"></i> add to compare</a></li>-->\n");
      out.write("                            </ul>\n");
      out.write("\n");
      out.write("                            <ul class=\"product-links\">\n");
      out.write("                                <li>Category:</li>\n");
      out.write("                                    ");
 Categories c = cr.getCate(cr.getIdParent(p.getId()));
      out.write("\n");
      out.write("                                <li><a href=\"dual.jsp?keyword=");
      out.print(String.valueOf(c.getId()));
      out.write('"');
      out.write('>');
      out.print(c.getName());
      out.write("</a></li>\n");
      out.write("                            </ul>\n");
      out.write("\n");
      out.write("                            <ul class=\"product-links\">\n");
      out.write("                                <li>Share:</li>\n");
      out.write("                                <li><a href=\"#\"><i class=\"fa fa-facebook\"></i></a></li>\n");
      out.write("                                <li><a href=\"#\"><i class=\"fa fa-twitter\"></i></a></li>\n");
      out.write("                                <li><a href=\"#\"><i class=\"fa fa-google-plus\"></i></a></li>\n");
      out.write("                                <li><a href=\"#\"><i class=\"fa fa-envelope\"></i></a></li>\n");
      out.write("                            </ul>\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <!-- /Product details -->\n");
      out.write("\n");
      out.write("                    <!-- Product tab -->\n");
      out.write("                    <div class=\"col-md-12\">\n");
      out.write("                        <div id=\"product-tab\">\n");
      out.write("                            <!-- product tab nav -->\n");
      out.write("                            <ul class=\"tab-nav\">\n");
      out.write("                                <li class=\"active\"><a data-toggle=\"tab\" href=\"#tab1\">DETAIL</a></li>\n");
      out.write("                            </ul>\n");
      out.write("                            <!-- /product tab nav -->\n");
      out.write("\n");
      out.write("                            <!-- product tab content -->\n");
      out.write("                            <div class=\"tab-content\">\n");
      out.write("                                <!-- tab1  -->  \n");
      out.write("                                <div id=\"tab1\" class=\"tab-pane fade in active\">\n");
      out.write("                                    <div class=\"row\">\n");
      out.write("                                        <div class=\"col-md-12\">\n");
      out.write("                                            <pre>");
      out.print('\t' + p.getDetail());
      out.write("</pre>\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                                <!-- /tab1  -->                      \n");
      out.write("                            </div>\n");
      out.write("                            <!-- /product tab content  -->\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <!-- /product tab -->\n");
      out.write("                    <!-- Comment tab -->\n");
      out.write("                    <div class=\"col-md-12\">\n");
      out.write("                        <div id=\"product-tab\">\n");
      out.write("                            <ul class=\"tab-nav\">\n");
      out.write("                                <li class=\"active\"><a data-toggle=\"tab\" href=\"#tab1\">Comment</a></li>\n");
      out.write("                            </ul>\n");
      out.write("                            <div class=\"tab-content\">\n");
      out.write("                                <!--                                <div class=\"form-group row\">\n");
      out.write("                                                                    <div class=\"col-sm-10 \">\n");
      out.write("                                                                        <textarea class=\"form-control\" rows=\"2\" id=\"comment\" ></textarea> \n");
      out.write("                                                                    </div>\n");
      out.write("                                                                    <div class=\"col-sm-2\"> \n");
      out.write("                                                                        <button type=\"submit\" class=\"btn btn-read btn-lg btn-block\" name=\"btnComment\" id=\"btnComment\">\n");
      out.write("                                                                            Send\n");
      out.write("                                                                        </button>  \n");
      out.write("                                                                    </div>\n");
      out.write("                                                                </div>  \n");
      out.write("                                <div id=\"fb-root\"></div>-->\n");
      out.write("                                <div id=\"fb-root\"></div>\n");
      out.write("                                <script async defer crossorigin=\"anonymous\" src=\"https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v3.3&appId=356830451834317&autoLogAppEvents=1\"></script>\n");
      out.write("                                <div class=\"fb-comments\" data-href=\"https://developers.facebook.com/docs/plugins/comments#configurator\" data-width=\"100%\" data-numposts=\"5\"></div>\n");
      out.write("                            </div>\n");
      out.write("                            <!-- /product tab content  -->\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <!-- /Comment tab -->\n");
      out.write("                </div>\n");
      out.write("                <!-- /row -->\n");
      out.write("            </div>\n");
      out.write("            <!-- /container -->\n");
      out.write("        </div>\n");
      out.write("        <!-- /SECTION -->\n");
      out.write("\n");
      out.write("        <!-- Section -->\n");
      out.write("        <div class=\"section\">\n");
      out.write("            <!-- container -->\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <!-- row -->\n");
      out.write("                <div class=\"row\">\n");
      out.write("\n");
      out.write("                    <div class=\"col-md-12\">\n");
      out.write("                        <div class=\"section-title text-center\">\n");
      out.write("                            <h3 class=\"title\">RELATION PRODUCT</h3>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    ");

                        for (Product product : pr.getListProductByRelated(p.getId_type())) {
                    
      out.write("\n");
      out.write("                    <!-- product -->\n");
      out.write("                    <div class=\"col-md-3 col-xs-6\">\n");
      out.write("                        <div class=\"product\">\n");
      out.write("                            <div class=\"product-img\">\n");
      out.write("                                <img src=\"./img/products/");
      out.print(product.getImage());
      out.write("\" alt=\"\">\n");
      out.write("                                <div class=\"product-label\">\n");
      out.write("                                    ");
if (product.getNew_product() == 1) {
      out.write("\n");
      out.write("                                    <span class=\"new\">NEW</span>\n");
      out.write("                                    ");
}
      out.write("\n");
      out.write("                                    ");
if (product.getPromotion_price() != 0) {
      out.write("\n");
      out.write("                                    <span class=\"new\">SALE</span>\n");
      out.write("                                    ");
}
      out.write("\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"product-body\">\n");
      out.write("                                <p class=\"product-category\">Category</p>\n");
      out.write("                                <h3 class=\"product-name\"><a href=\"singgle.jsp?productID=");
      out.print(product.getId());
      out.write('"');
      out.write('>');
      out.print(product.getName());
      out.write("</a></h3>\n");
      out.write("                                    ");
if (product.getPromotion_price() != 0) {
      out.write("\n");
      out.write("                                <h4 class=\"product-price\">$");
      out.print(product.getPromotion_price());
      out.write("<del class=\"product-old-price\">$");
      out.print(product.getValue());
      out.write("</del></h4>\n");
      out.write("                                ");
}
      out.write("\n");
      out.write("                                ");
if (product.getPromotion_price() == 0) {
      out.write("\n");
      out.write("                                <h4 class=\"product-price\">$");
      out.print(product.getValue());
      out.write("</h4>\n");
      out.write("                                ");
}
      out.write("\n");
      out.write("                                <div class=\"product-rating\">\n");
      out.write("                                    <i class=\"fa fa-star\"></i>\n");
      out.write("                                    <i class=\"fa fa-star\"></i>\n");
      out.write("                                    <i class=\"fa fa-star\"></i>\n");
      out.write("                                    <i class=\"fa fa-star\"></i>\n");
      out.write("                                    <i class=\"fa fa-star\"></i>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"product-btns\">\n");
      out.write("                                    <button class=\"add-to-wishlist\"><i class=\"fa fa-heart-o\"></i><span class=\"tooltipp\">add to wishlist</span></button>\n");
      out.write("                                    <button class=\"add-to-compare\"><i class=\"fa fa-exchange\"></i><span class=\"tooltipp\">add to compare</span></button>\n");
      out.write("                                    <button class=\"quick-view\"><i class=\"fa fa-eye\"></i><span class=\"tooltipp\">quick view</span></button>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"add-to-cart\">\n");
      out.write("                                <a href=\"CartServlet?command=plus&productID=");
      out.print(p.getId());
      out.write("\"><i class=\"fa fa-shopping-cart\"></i> add to cart</a>\n");
      out.write("\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                    <!-- /product -->\n");
      out.write("                </div>\n");
      out.write("                <!-- /row -->\n");
      out.write("            </div>\n");
      out.write("            <!-- /container -->\n");
      out.write("        </div>\n");
      out.write("        <!-- /Section -->\n");
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("        <!-- jQuery Plugins -->\n");
      out.write("        <script src=\"js/jquery.min.js\"></script>\n");
      out.write("        <script src=\"js/bootstrap.min.js\"></script>\n");
      out.write("        <script src=\"js/slick.min.js\"></script>\n");
      out.write("        <script src=\"js/nouislider.min.js\"></script>\n");
      out.write("        <script src=\"js/jquery.zoom.min.js\"></script>\n");
      out.write("        <script src=\"js/main.js\"></script>\n");
      out.write("        <script src=\"js/jquery.pan.js\"></script>\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("            $(\".abab\").pan();\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
