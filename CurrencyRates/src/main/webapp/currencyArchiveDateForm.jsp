<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Archived rate</title>
</head>
<body>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<div class="container">
    <!-- header -->
    <div class="row">
        <div class="col">
            <h1>Select date</h1>
        </div>
    </div>
    <div class="row">
        <form method="post">
            <div class="form-group row">
                <div class="col">
                    <%
                        LocalDate localDate = LocalDate.now();
                    %>
                    <input type="date" class="form-control" min="1999-01-01" max="<%= localDate %>" value="<%= localDate %>" name="date">
                </div>
                <div>
                    <button type="submit" class="btn btn-secondary">Submit</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
