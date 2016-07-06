<%--
  Created by IntelliJ IDEA.
  User: justinkang
  Date: 6/27/16
  Time: 3:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Lions Lunches Home</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">

    <%--<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>

  </head>
  <body>

  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="index.jsp">Lion's Lunches</a>
      </div>
      <ul class="nav navbar-nav">
        <li class="active"><a href="index.jsp">Home</a></li>
        <li><a href="LunchForm.jsp">Sign-Up Form</a></li>
      </ul>
    </div>
  </nav>

    <h1>Welcome to Lions Lunches</h1>
    <p>To sign up for a lions lunch this week please click <a href="LunchForm.jsp">here</a></p>
  </body>
</html>
