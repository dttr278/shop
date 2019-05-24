package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import dao.UserDao;
import model.User;
import java.util.ArrayList;
import java.util.List;
import dao.ProductDao;
import model.Product;
import dao.CategoriesDao;
import model.Categories;

public final class productdetail_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <title>Page Title</title>\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"main.css\">\n");
      out.write("        <!-- Latest compiled and minified CSS -->\n");
      out.write("        <link rel=\"stylesheet\"\n");
      out.write("              href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css\">\n");
      out.write("        <!-- jQuery library -->\n");
      out.write("        <script\n");
      out.write("        src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n");
      out.write("        <!-- Latest compiled JavaScript -->\n");
      out.write("        <script\n");
      out.write("        src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body >\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("\n");
      out.write("        ");

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
        
      out.write("\n");
      out.write("        <div class=\"container\" style=\"padding: 1em;\">\n");
      out.write("            <h2>");
      out.print(product.getName());
      out.write("</h2>\n");
      out.write("            <form method=\"POST\" action=\"UpdateProduct\" enctype='multipart/form-data'>\n");
      out.write("                <div class=\"form-group\" style=\"visibility: hidden;\">\n");
      out.write("                    <label for=\"id\">Id:</label>\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"id\"\n");
      out.write("                           name=\"id\"\n");
      out.write("                           value=\"");
      out.print(product.getId());
      out.write("\">\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"name\">Name:</label>\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"name\"\n");
      out.write("                           placeholder=\"Enter name\" name=\"name\"\n");
      out.write("                           value=\"");
      out.print(product.getName());
      out.write("\">\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"image\">Image:</label>\n");
      out.write("                    <img class=\"img-responsive\" id=\"product-image\"\n");
      out.write("                         src=\"./img/products/");
      out.print(product.getImage());
      out.write("\"\n");
      out.write("                         alt=\"Product image\" width=\"200\" height=\"200\">\n");
      out.write("                    <label class=\"btn btn-default btn-file\" style=\"margin-top:\n");
      out.write("                           5px;\">\n");
      out.write("                        Browse <input name=\"image\" type=\"file\" style=\"display: none;\" onchange=\"readURL(this);\"  accept=\"image/*\">\n");
      out.write("                    </label>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"type\">Select type:</label>\n");
      out.write("                    <select name=\"type\" class=\"form-control\" id=\"type\">\n");
      out.write("                        ");
for (Categories c : categorieses) {
                                if (c.getId_parent() > 0) {
                                    continue;
                                }
                                if (c.getId() == product.getId_type()) {
      out.write("\n");
      out.write("                        <option value=\"");
      out.print(c.getId());
      out.write("\" selected>");
      out.print(c.getName());
      out.write("</option>\n");
      out.write("                        ");
} else {
      out.write("\n");
      out.write("                        <option value=\"");
      out.print(c.getId());
      out.write("\" >");
      out.print(c.getName());
      out.write("</option>\n");
      out.write("                        ");
}
                            }
      out.write("\n");
      out.write("                    </select>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"amount\">Amount:</label>\n");
      out.write("                    <input name=\"amount\" type=\"number\" class=\"form-control\" id=\"amount\"\n");
      out.write("                           placeholder=\"Enter value\" \n");
      out.write("                           value=\"");
      out.print(product.getAmount());
      out.write("\">\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"value\">Value:</label>\n");
      out.write("                    <input name=\"value\" type=\"number\" class=\"form-control\" id=\"value\"\n");
      out.write("                           placeholder=\"Enter value\" \n");
      out.write("                           value=\"");
      out.print(String.format("%.2f", product.getValue()));
      out.write("\">\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label  for=\"detail\">Detail:</label>\n");
      out.write("                    <textarea name=\"detail\" class=\"form-control\" rows=\"5\" id=\"detail\">");
      out.print(product.getDetail());
      out.write("</textarea>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"promotion\">Promotion value:</label>\n");
      out.write("                    <input name=\"promotionValue\" type=\"number\" class=\"form-control\" id=\"promotion\"\n");
      out.write("                           placeholder=\"Enter promotion value\" \n");
      out.write("                           value=\"");
      out.print(String.format("%.2f", product.getPromotion_price()));
      out.write("\">\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label  for=\"promotion\">Promotion:</label>\n");
      out.write("                    <textarea name=\"promotion\" class=\"form-control\" rows=\"5\" id=\"promotion\">");
      out.print(product.getPromotion());
      out.write("</textarea>\n");
      out.write("                </div>\n");
      out.write("                <button type=\"submit\" class=\"btn btn-default\">Save</button>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("        <script>\n");
      out.write("            function readURL(input) {\n");
      out.write("                if (input.files && input.files[0]) {\n");
      out.write("                    var reader = new FileReader();\n");
      out.write("\n");
      out.write("                    reader.onload = function (e) {\n");
      out.write("                        $('#product-image')\n");
      out.write("                                .attr('src', e.target.result);\n");
      out.write("                    };\n");
      out.write("\n");
      out.write("                    reader.readAsDataURL(input.files[0]);\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
