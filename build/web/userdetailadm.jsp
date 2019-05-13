<%-- 
    Document   : userdetail
    Created on : Apr 18, 2019, 1:20:21 PM
    Author     : DO TAN TRUNG
--%>

<%@page import="java.util.List"%>
<%@page import="model.Role"%>
<%@page import="dao.RoleDao"%>
<%@page import="dao.UserDao"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <style>
            .btnText{
                background-color: rgba(0,0,0,0);
                border-width: 0;
                color: blue;
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%
            String userId = request.getParameter("userId");
            User u = null;
            if (userId == null) {
                u=new User(-1, "", "", "", "", "");
            } else {
                u = UserDao.loadUserById(Integer.valueOf(userId));
                if (u == null) {
                    response.getWriter().print("user not found");
                    return;
                }
            }
            List<Role> lRole=RoleDao.getAllRole();
        %>
        <div class="container" style="padding: 1em;">
            <h2><%=u.getUsername()%></h2>
            <form method="POST" action="UpdateUser" >
                <div class="form-group" style="visibility: hidden;">
                    <label for="id">Id:</label>
                    <input type="text" class="form-control" id="id"
                           name="id"
                           value="<%=u.getId()%>">
                </div>
                <div class="form-group">
                    <label for="username">UserName: </label>
                    <input type="text" class="form-control" id="username" onchange="enableSubmit()"
                           placeholder="Enter name" name="username"
                           value="<%=u.getUsername()%>">
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="text" class="form-control" id="email" onchange="enableSubmit()"
                           placeholder="Email: " name="email"
                           value="<%=u.getEmail()%>">
                </div>
                <div class="form-group">
                    <label for="password">Password: </label>
                    <input type="password" class="form-control" id="password" onchange="enableSubmit()"
                           placeholder="Enter name" name="password"
                           value="<%=u.getPassword()%>">
                    <button class="btnText" id="btnviewpassword" type="button"onclick="showPassword()" >view</button>
                </div>
                <div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" class="form-control" id="address" onchange="enableSubmit()"
                           placeholder="address " name="address"
                           value="<%=(u.getAddress() == null ? "" : u.getAddress())%>">
                </div>
                <div class="form-group">
                    <label for="phone">Phone:</label>
                    <input type="text" class="form-control" id="phone" onchange="enableSubmit()"
                           placeholder="phone " name="phone"
                           value="<%=(u.getPhone() == null ? "" : u.getPhone())%>">
                </div>
                <div class="form-group">
                    <label for="role">Account type (admin, user, customer):</label>
                    <select name="role" class="form-control" id="role" onchange="enableSubmit()">
                        <%
                            for (Role r : lRole) {%>
                        <option value="<%=r.getId()%>"
                                <%if (r.getId()==u.getRole() || (u.getRole() == 0 &&"customer".equalsIgnoreCase(r.getRole()))) {%>
                                selected
                                <%}%>
                                ><%=r.getRole()%></option>
                        <%}%>
                    </select>
                </div>
                <button id="submitbtn" type="submit" class="btn btn-default" disabled="true">Save</button>
            </form>
        </div>

        <script>
            function enableSubmit() {
                document.getElementById("submitbtn").disabled = false;
            }

            function showPassword() {
                if (document.getElementById("password").type == "password") {
                    document.getElementById("password").type = "text";
                    document.getElementById("btnviewpassword").innerHTML = "hide";
                } else {
                    document.getElementById("password").type = "password";
                    document.getElementById("btnviewpassword").innerHTML = "view";
                }
            }
        </script>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
