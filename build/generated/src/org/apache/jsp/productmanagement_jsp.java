package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import dao.UserDao;
import model.User;
import java.util.List;
import dao.ProductDao;
import model.Product;

public final class productmanagement_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <title>Page Title</title>\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"main.css\">\n");
      out.write("        <script src=\"main.js\"></script>\n");
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
      out.write("    <body style=\"padding: 1rem;\">\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("\n");
      out.write("        \n");
      out.write("        ");

            User users = null;
            if (session.getAttribute("users") != null) {
                users = (User) session.getAttribute("users");
                UserDao userDao=new UserDao();
                if(!("admin").equals(userDao.getRole(users.getId()))){
                    response.getWriter().print("access denied!");
                }
            }else{
                response.getWriter().print("access denied!");
            }
            final int MAX_ITEM = 10;
            ProductDao pr = new ProductDao();
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
            int count = pr.count();
            if (!keyword.isEmpty()) {
                count = pr.getCountSearch(keyword);
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
            List<Product> lProduct;
            if (!keyword.isEmpty()) {
                lProduct = pr.getListProductBySearchLimit(keyword, (currentPage - 1) * MAX_ITEM, MAX_ITEM);
            } else {
                lProduct = pr.getListProductByPage((currentPage - 1) * MAX_ITEM, MAX_ITEM);
            }
        
      out.write("\n");
      out.write("        <!--        <nav class=\"navbar navbar-default\">\n");
      out.write("                    <div class=\"container-fluid\">\n");
      out.write("                        <div class=\"navbar-header\">\n");
      out.write("                            <a class=\"navbar-brand\" href=\"#\">WebSiteName</a>\n");
      out.write("                        </div>\n");
      out.write("                        <ul class=\"nav navbar-nav\">\n");
      out.write("                            <li class=\"active\"><a href=\"#\">Home</a></li>\n");
      out.write("                            <li><a href=\"#\">Page 1</a></li>\n");
      out.write("                            <li><a href=\"#\">Page 2</a></li>\n");
      out.write("                            <li><a href=\"#\">Page 3</a></li>\n");
      out.write("                        </ul>\n");
      out.write("                    </div>\n");
      out.write("                </nav>-->\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <!--                <div class=\"col-sm-4\">\n");
      out.write("                                    <ul class=\"list-group\">\n");
      out.write("                \n");
      out.write("                                        <li class=\"list-group-item\">First item</li>\n");
      out.write("                                        <li class=\"list-group-item\">Second item</li>\n");
      out.write("                                        <li class=\"list-group-item\">Third item</li>\n");
      out.write("                                    </ul>\n");
      out.write("                                </div>-->\n");
      out.write("                <div >\n");
      out.write("                    <button type=\"button\" class=\"btn btn-default\" style=\"margin-bottom: 10px;\">\n");
      out.write("                        <a href=\"productdetail.jsp\"><span class=\"glyphicon glyphicon-plus\"> new</span></a>\n");
      out.write("                    </button>\n");
      out.write("                    <div style=\"float: right\">\n");
      out.write("                        <input id=\"inputSearch\" type=\"text\" placeholder=\"search\" \n");
      out.write("                               ");
if (!keyword.isEmpty())
      out.write("\n");
      out.write("                               value=\"");
      out.print(keyword);
      out.write("\">\n");
      out.write("                        <button id=\"btnSearch\" type=\"button\" class=\"btn btn-default\" >\n");
      out.write("                            Search  \n");
      out.write("                        </button>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <ul class=\"list-group\">\n");
      out.write("                        ");
for (Product product : lProduct) {
      out.write("\n");
      out.write("                        <li class=\"list-group-item\">\n");
      out.write("                            <image class=\"img-thumbnail\" style=\"width: 50px\" src=\"./img/products/");
      out.print(product.getImage());
      out.write("\" >\n");
      out.write("                            <a href=\"productdetail.jsp?productId=");
      out.print(product.getId());
      out.write('"');
      out.write('>');
      out.print(product.getName());
      out.write("</a>\n");
      out.write("                        </li>\n");
      out.write("                        ");
}
      out.write("\n");
      out.write("                        <!--                        <li class=\"list-group-item\">First item</li>\n");
      out.write("                                                <li class=\"list-group-item\">Second item</li>\n");
      out.write("                                                <li class=\"list-group-item\">Third item</li>-->\n");
      out.write("                    </ul>\n");
      out.write("                    <nav aria-label=\"Page navigation example\">\n");
      out.write("                        <ul class=\"pagination justify-content-center\">\n");
      out.write("                            <li class=\"page-item \">\n");
      out.write("                                <a class=\"page-link\" \n");
      out.write("                                   ");
if (!keyword.isEmpty()) {
      out.write("\n");
      out.write("                                   href=\"productmanagement.jsp?page=1&keyword=");
      out.print(keyword.trim());
      out.write("\"\n");
      out.write("                                   ");
} else {
      out.write("\n");
      out.write("                                   href=\"productmanagement.jsp?page=1\"\n");
      out.write("                                   ");
}
      out.write("\n");
      out.write("                                   >First</a>\n");
      out.write("                            </li>\n");
      out.write("                            ");
for (int i = pageStart; i <= pageEnd; i++) {
      out.write("\n");
      out.write("                            <li class=\"page-item ");
if (i == currentPage) {
      out.write("active");
}
      out.write("\">\n");
      out.write("                                <a class=\"page-link\" \n");
      out.write("                                   ");
if (!keyword.isEmpty()) {
      out.write("\n");
      out.write("                                   href=\"productmanagement.jsp?page=");
      out.print(i);
      out.write("&keyword=");
      out.print(keyword.trim());
      out.write("\"\n");
      out.write("                                   ");
} else {
      out.write("\n");
      out.write("                                   href=\"productmanagement.jsp?page=");
      out.print(i);
      out.write("\"\n");
      out.write("                                   ");
}
      out.write('>');
      out.print(i);
      out.write("</a>\n");
      out.write("                            </li>\n");
      out.write("                            ");
}
      out.write("\n");
      out.write("                            <li class=\"page-item \">\n");
      out.write("                                <a class=\"page-link\" \n");
      out.write("                                   ");
if (!keyword.isEmpty()) {
      out.write("\n");
      out.write("                                   href=\"productmanagement.jsp?page=");
      out.print(lastPage);
      out.write("&keyword=");
      out.print(keyword.trim());
      out.write("\"\n");
      out.write("                                   ");
} else {
      out.write("\n");
      out.write("                                   href=\"productmanagement.jsp?page=");
      out.print(lastPage);
      out.write("\"\n");
      out.write("                                   ");
}
      out.write("\n");
      out.write("                                   >Last</a>\n");
      out.write("                            </li>\n");
      out.write("                            <!--                            <li class=\"page-item\"><a class=\"page-link\" href=\"#\">2</a></li>\n");
      out.write("                                                        <li class=\"page-item\"><a class=\"page-link\" href=\"#\">3</a></li>\n");
      out.write("                                                        <li class=\"page-item\">\n");
      out.write("                                                            <a class=\"page-link\" href=\"#\">Next</a>\n");
      out.write("                                                        </li>-->\n");
      out.write("                        </ul>\n");
      out.write("                    </nav>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <script>\n");
      out.write("            $(\"#btnSearch\").click(function () {\n");
      out.write("//                $(\"#linkSearch\").attr(\"href\", \"productmanagement.jsp?keyword=\" + $(\"#inputSearch\").val());\n");
      out.write("                if ($(\"#inputSearch\").val().trim() != '')\n");
      out.write("                    window.location.href = \"productmanagement.jsp?keyword=\" + $(\"#inputSearch\").val();\n");
      out.write("            });\n");
      out.write("        </script>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>");
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
