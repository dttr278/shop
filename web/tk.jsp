<%-- 
    Document   : tk
    Created on : Apr 26, 2019, 1:25:23 PM
    Author     : DO TAN TRUNG
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="dao.TotalPerMonth"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="dao.BillDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <script src="js/messagebox_script.js"></script>
        <link type="text/css" rel="stylesheet" href="css/messagebox_style.css"/>
    </head>
    <body>
        <%
            String sfrom = request.getParameter("from");
            String sto = request.getParameter("to");
            Date from = null, to = null;
            DateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
            if (sfrom == null || sto == null) {
                from = new Date();
                from.setYear(from.getYear() - 1);
                to = new Date();
            } else {

                from = dformat.parse(sfrom);
                to = dformat.parse(sto);
            }
            List<TotalPerMonth> l = BillDao.totalPerMonth(from, to);

        %>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container">
                <div style="padding: 5rem;margin: auto;max-width: 660px;">
                    <input id="datefrom" type="date" value="<%=dformat.format(from)%>" > - 
                <input id="dateto" type="date" value="<%=dformat.format(to)%>">
                <script>
                    function setDayOnClick() {
                    var datefrom = document.getElementById("datefrom");
                    var dateto = document.getElementById("dateto");
                    var f = new Date(datefrom.value);
                    var t = new Date(dateto.value);
                    window.location.href = 'tk.jsp?from=' + f.getFullYear() + '-' + (f.getMonth() + 1) + '-' + f.getDate() + '&to=' + t.getFullYear() + '-' + (t.getMonth() + 1) + '-' + t.getDate();
                    }

                </script>
                <button class="btn btn-default" onclick="setDayOnClick()">Ok</button>
            </div>
            <canvas id="myChart" style="margin: auto;max-width: 660px;"></canvas>
            <div style="margin: auto;max-width: 660px;text-align: right;">
                <script>
                    var ctx = document.getElementById('myChart').getContext('2d');
                    var chart = new Chart(ctx, {
                    type: 'line',
                            data: {
                    <%DateFormat df = new SimpleDateFormat("MM/yyyy");
                        double total = 0;%>
                            labels: [<%for (TotalPerMonth n : l) {%>"<%=df.format(n.getMonth()) + "\","%><%}%>],
                                    datasets: [{
                                    label: 'Money',
                                            backgroundColor: 'rgba(255, 99, 132,0.2)',
                                            borderColor: 'rgb(255, 99, 132)',
                                            data: [<%for (TotalPerMonth n : l) {
                                                    total += n.getTotal();%><%=n.getTotal() + ","%><%}%>]
                                    }],
                                    borderWidth: 2
                            },
                                    // Configuration options go here
                                    options: {
                                    responsive: true,
                                            hoverMode: 'index',
                                            stacked: false,
                                            title: {
                                            display: true,
                                                    text: 'Money per month'
                                            }
                                    }
                            });
                </script>
                <b>Total: </b><%=total%>
            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
