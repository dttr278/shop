
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

            .container {
                padding: 16px;
            }

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
                padding: 50px 20px;
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
        <div id="test"> 
            <h1 class="text-center title-test">Terms and Conditions</h1>
            <div class="item"> 
                <label class="title">1.</label>
                <p class="content">
                    The <b>Intellectual Property</b> disclosure will inform users that the contents, logo and other visual media you created is your property and is protected by copyright laws.
                </p>
            </div>
            <div class="item"> 
                <label class="title">2.</label>
                <p class="content">
                    A <b>Termination clause</b> will inform that users’ accounts on your website and mobile app or users’ access to your website and mobile (if users can’t have an account with you) can be terminated in case of abuses or at your sole discretion.
                </p>
            </div>
            <div class="item"> 
                <label class="title">3.</label>
                <p class="content">
                    A <b>Governing Law</b> will inform users which laws govern the agreement. This should the country in which your company is headquartered or the country from which you operate your website and mobile app.
                </p>
            </div>
            <div class="item"> 
                <label class="title">4.</label>
                <p class="content">
                    A <b>Links To Other Web Sites</b> clause will inform users that you are not responsible for any third party websites that you link to. This kind of clause will generally inform users that they are responsible for reading and agreeing (or disagreeing) with the Terms and Conditions or Privacy Policies of these third parties.
                </p>
            </div>
        </div>
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

