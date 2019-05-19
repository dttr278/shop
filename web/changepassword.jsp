<%-- 
    Document   : changepassword
    Created on : May 19, 2019, 3:32:33 PM
    Author     : dttr2
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="utf-8">
        <style>
            body {font-family: Arial, Helvetica, sans-serif;}
            /*            form {border: 3px solid #f1f1f1;}*/

            input[type=text], input[type=password] {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }

            button {
                background-color: #f72c36;
                color: white;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
            }

            button:hover {
                opacity: 0.8;
            }

            .cancelbtn {
                width: auto;
                padding: 10px 18px;
                background-color: #f44336;
            }

            .bt{
                padding: 14px 20px;
            }

            img.avatar {
                width: 40%;
                border-radius: 50%;
            }

            /*            .container {
                            padding: 16px;
                        }*/

            span.psw {
                float: right;
                padding-top: 16px;
            }

            /* Change styles for span and cancel button on extra small screens */
            @media screen and (max-width: 300px) {
                span.psw {
                    display: block;
                    float: none;
                }
                .cancelbtn {
                    width: 100%;
                }
            }
            /*my*/
            #test{
                min-height: 500px;
                padding: 20px 20px;
                /*text-align: center;*/
                width: 50%;
                margin: auto;
            }
            #test .title-test{
                padding-bottom: 10px;
            }
            #test .item{
                padding: 7px 0 0;
                display: flex;
            }
            #test .title{
                display: inline-block;
                width: 130px;
                text-align: right;
                text-transform: uppercase;
                margin-right: 7px;
            }
            #test .content{
                width: calc(100% - 130px);
            }

            .btnText{
                background-color: rgba(0,0,0,0);
                border-width: 0;
                color: blue;
                text-decoration: underline;
            }

        </style>
    </head>
    <body>
        <%
            User users = null;
            if (session.getAttribute("users") != null) {
                users = (User) session.getAttribute("users");
            }
        %>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container">
                <div class="row">

                    <div id="mainContent" style="padding:20px;">
                        <form action="ChangePassword" method="post">
                            <div class="item"> 
                            <%if (request.getParameter("error") != null) {%>
                            <p style="color:red"><%="Old password is not correct!"%></p>
                            <%}%>
                            <%if (request.getParameter("suscess") != null) {%>
                            <p style="color:greenyellow"><%="Suscess!"%></p>
                            <%}%>
                            </div>

                        <div class="item"> 
                            <label class="title">Old password :</label>
                            <p class="content">
                                <input type="password" id="old-pass" name="old-pass" required>
                            </p>
                        </div>
                        <div class="item"> 
                            <label class="title">New password :</label>
                            <p class="content">
                                <input type="password" id="new-pass" name="new-pass" required>
                            </p>
                        </div>
                        <div class="item"> 
                            <label class="title">Confirm new password :</label>
                            <p class="content">
                                <input type="password" id="confirm-new-pass" name="confirm-new-pass" required>
                            </p>
                        </div>
                        <div class="item"> 
                            <button id="sbbtn" type="submit" style="width: auto;margin: auto;" class="content btn-success">Update password</button>
                        </div>

                    </form>
                </div>

            </div>

        </div>
    </div>
    <script>
        var password = document.getElementById("new-pass")
                , confirm_password = document.getElementById("confirm-new-pass");

        function validatePassword() {
            if (password.value != confirm_password.value) {
                confirm_password.setCustomValidity("Passwords Don't Match");
            } else {
                confirm_password.setCustomValidity('');
            }
        }

        password.onchange = validatePassword;
        confirm_password.onkeyup = validatePassword;
    </script>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>
