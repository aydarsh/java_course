<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exchange Rate</title>
</head>
<body>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<div class="container">
    <%
        Double rate = (Double) request.getAttribute("rate");
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedRate = df.format(rate);
        LocalDate date = (LocalDate) request.getAttribute("date");
        if (rate != null) {
            out.println("<div class=\"row\">");
            out.println("<div class=\"col\">");
            out.println(String.format("<h1>Rate for %s </h1>", date));
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"row\">");
            out.println("<div class=\"col\">");
            out.println(String.format("<h3>1 USD is %s RUB</h3>", formattedRate));
            out.println("</div>");
            out.println("</div>");
        }
    %>
</div>

</body>
</html>
