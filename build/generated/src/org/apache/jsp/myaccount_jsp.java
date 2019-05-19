package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.User;

public final class myaccount_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <style>\n");
      out.write("            body {font-family: Arial, Helvetica, sans-serif;}\n");
      out.write("            /*            form {border: 3px solid #f1f1f1;}*/\n");
      out.write("\n");
      out.write("            input[type=text], input[type=password] {\n");
      out.write("                width: 100%;\n");
      out.write("                padding: 12px 20px;\n");
      out.write("                margin: 8px 0;\n");
      out.write("                display: inline-block;\n");
      out.write("                border: 1px solid #ccc;\n");
      out.write("                box-sizing: border-box;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            button {\n");
      out.write("                background-color: #f72c36;\n");
      out.write("                color: white;\n");
      out.write("                margin: 8px 0;\n");
      out.write("                border: none;\n");
      out.write("                cursor: pointer;\n");
      out.write("                width: 100%;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            button:hover {\n");
      out.write("                opacity: 0.8;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .cancelbtn {\n");
      out.write("                width: auto;\n");
      out.write("                padding: 10px 18px;\n");
      out.write("                background-color: #f44336;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .bt{\n");
      out.write("                padding: 14px 20px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            img.avatar {\n");
      out.write("                width: 40%;\n");
      out.write("                border-radius: 50%;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("/*            .container {\n");
      out.write("                padding: 16px;\n");
      out.write("            }*/\n");
      out.write("\n");
      out.write("            span.psw {\n");
      out.write("                float: right;\n");
      out.write("                padding-top: 16px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            /* Change styles for span and cancel button on extra small screens */\n");
      out.write("            @media screen and (max-width: 300px) {\n");
      out.write("                span.psw {\n");
      out.write("                    display: block;\n");
      out.write("                    float: none;\n");
      out.write("                }\n");
      out.write("                .cancelbtn {\n");
      out.write("                    width: 100%;\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("            /*my*/\n");
      out.write("            #test{\n");
      out.write("                min-height: 500px;\n");
      out.write("                padding: 20px 20px;\n");
      out.write("                /*text-align: center;*/\n");
      out.write("                width: 50%;\n");
      out.write("                margin: auto;\n");
      out.write("            }\n");
      out.write("            #test .title-test{\n");
      out.write("                padding-bottom: 10px;\n");
      out.write("            }\n");
      out.write("            #test .item{\n");
      out.write("                padding: 7px 0 0;\n");
      out.write("                display: flex;\n");
      out.write("            }\n");
      out.write("            #test .title{\n");
      out.write("                display: inline-block;\n");
      out.write("                width: 130px;\n");
      out.write("                text-align: right;\n");
      out.write("                text-transform: uppercase;\n");
      out.write("                margin-right: 7px;\n");
      out.write("            }\n");
      out.write("            #test .content{\n");
      out.write("                width: calc(100% - 130px);\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .btnText{\n");
      out.write("                background-color: rgba(0,0,0,0);\n");
      out.write("                border-width: 0;\n");
      out.write("                color: blue;\n");
      out.write("                text-decoration: underline;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            User users = null;
            if (session.getAttribute("users") != null) {
                users = (User) session.getAttribute("users");
            }
        
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    \n");
      out.write("                    <div id=\"mainContent\" >\n");
      out.write("                        <div id=\"test\"> \n");
      out.write("                            <h1 class=\"text-center title-test\">My Account</h1>\n");
      out.write("                            <div class=\"item\"> \n");
      out.write("                                <label class=\"title\"> username :</label>\n");
      out.write("                                <p class=\"content\">\n");
      out.write("                                ");
      out.print(users.getUsername());
      out.write("\n");
      out.write("                            </p>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"item\"> \n");
      out.write("                            <label class=\"title\">email :</label>\n");
      out.write("                            <p class=\"content\">\n");
      out.write("                                ");
      out.print(users.getEmail());
      out.write("\n");
      out.write("                            </p>\n");
      out.write("                        </div>\n");
      out.write("                        <form>\n");
      out.write("                            <div class=\"item\"> \n");
      out.write("                                <label class=\"title\">address :</label>\n");
      out.write("                                <p class=\"content\">\n");
      out.write("                                    <input id=\"address\" name=\"address\" disabled=\"true\" value=\"");
      out.print(users.getAddress() == null ? "" : users.getAddress());
      out.write("\" required>\n");
      out.write("                                    <input class=\"btnText\" onclick=\"enableEditAddress(this)\" type=\"button\" value=\"change address\" name=\"address_change\" />\n");
      out.write("                                </p>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"item\"> \n");
      out.write("                                <label class=\"title\">phone :</label>\n");
      out.write("                                <p class=\"content\">\n");
      out.write("                                    <input  id=\"phone\" name=\"phone\" disabled=\"true\" value=\"");
      out.print(users.getPhone() == null ? "" : users.getPhone());
      out.write("\" required>\n");
      out.write("                                    <input class=\"btnText\" onclick=\"enableEditPhone(this)\" type=\"button\" value=\"change phone\" name=\"phone_change\" />\n");
      out.write("                                </p>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"item\"> \n");
      out.write("                                <button id=\"sbbtn\" type=\"submit\" style=\"display: none;width: auto;margin: auto;\" class=\"content btn-success\">Save</button>\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- jQuery Plugins -->\n");
      out.write("        <script src=\"js/jquery.min.js\"></script>\n");
      out.write("        <script src=\"js/bootstrap.min.js\"></script>\n");
      out.write("        <script src=\"js/slick.min.js\"></script>\n");
      out.write("        <script src=\"js/nouislider.min.js\"></script>\n");
      out.write("        <script src=\"js/jquery.zoom.min.js\"></script>\n");
      out.write("        <script src=\"js/main.js\"></script>\n");
      out.write("        <script>\n");
      out.write("                                        function enableEditAddress(event) {\n");
      out.write("                                            document.getElementById(\"address\").disabled = false;\n");
      out.write("                                            event.style.display = \"none\";\n");
      out.write("                                            document.getElementById(\"sbbtn\").style.display = \"block\";\n");
      out.write("                                        }\n");
      out.write("                                        function enableEditPhone(event) {\n");
      out.write("                                            document.getElementById(\"phone\").disabled = false;\n");
      out.write("                                            event.style.display = \"none\";\n");
      out.write("                                            document.getElementById(\"sbbtn\").style.display = \"block\";\n");
      out.write("                                        }\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
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
