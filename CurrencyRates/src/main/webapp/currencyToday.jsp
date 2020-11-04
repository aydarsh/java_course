<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Today's rate</title>
</head>
<body>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<div class="container">
    <!-- header -->
    <div class="row">
        <div class="col">
            <h1>Rate for today</h1>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <h3>1 USD is <% out.print(request.getAttribute("rate")); %> RUB</h3>
        </div>
    </div>
</div>

</body>
</html>
