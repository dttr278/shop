<%-- 
    Document   : login
    Created on : Sep 10, 2018, 8:01:23 PM
    Author     :
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
                    
                    <div id="mainContent" >
                        <div id="test"> 
                            <h1 class="text-center title-test">My Account</h1>
                            <div class="item"> 
                                <label class="title"> username :</label>
                                <p class="content">
                                <%=users.getUsername()%>
                            </p>
                        </div>
                        <div class="item"> 
                            <label class="title">email :</label>
                            <p class="content">
                                <%=users.getEmail()%>
                            </p>
                        </div>
                            <form action="ChangeInfo" method="post">
                            <div class="item"> 
                                <label class="title">address :</label>
                                <p class="content">
                                    <input id="address" name="address" disabled="true" value="<%=users.getAddress() == null ? "" : users.getAddress()%>" required>
                                    <input class="btnText" onclick="enableEditAddress(this)" type="button" value="change address" name="address_change" />
                                </p>
                            </div>
                            <div class="item"> 
                                <label class="title">phone :</label>
                                <p class="content">
                                    <input  id="phone" name="phone" disabled="true" value="<%=users.getPhone() == null ? "" : users.getPhone()%>" required>
                                    <input class="btnText" onclick="enableEditPhone(this)" type="button" value="change phone" name="phone_change" />
                                </p>
                            </div>
                            <div class="item"> 
                                <button id="sbbtn" type="submit" style="display: none;width: auto;margin: auto;" class="content btn-success">Save</button>
                            </div>

                        </form>
                    </div>

                </div>

            </div>
        </div>

        <!-- jQuery Plugins -->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/slick.min.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/jquery.zoom.min.js"></script>
        <script src="js/main.js"></script>
        <script>
                                        function enableEditAddress(event) {
                                            document.getElementById("address").disabled = false;
                                            event.style.display = "none";
                                            document.getElementById("sbbtn").style.display = "block";
                                        }
                                        function enableEditPhone(event) {
                                            document.getElementById("phone").disabled = false;
                                            event.style.display = "none";
                                            document.getElementById("sbbtn").style.display = "block";
                                        }
        </script>
    </body>
    <jsp:include page="footer.jsp"></jsp:include>
</html>

