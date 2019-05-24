
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
            <h1 class="text-center title-test">Orders & Returns</h1>
            <div class="item"> 
                <label class="title"> Standard Return Policy:</label>
                <p class="content">
                    We have a 30-day return/replacement policy, if you are unhappy with your product or your product is defective, Please contact us at returns@dailysale.com for a return merchandise authorization (RMA) within 30 days of the delivery date. Your return needs to be received by our warehouse within 2 weeks after the RMA was issued. 
                    <br>Please avoid refusing any package should you wish to return your order. These often get lost in transit or can sometimes be "disposed" by the carrier.
                    <br>DailySale will not be held responsible if the package is "REFUSED" and has not been received by our returns department. 
                    <br>When contacting Customer Service, please be sure to include the following:
                    <br>Order number
                    <br>The product you wish to return
                    <br>Reason for return (Please specify)
                    <br><b>Please note that return shipping is the buyers' responsibility.</b>
                    
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

