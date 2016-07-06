<%--
  Created by IntelliJ IDEA.
  User: justinkang
  Date: 7/3/16
  Time: 1:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Controls</title>
</head>
<body>
<h1>Here is the admin page</h1>
<p>
<form class="form-control" action="AdminController" method="post" id="adminPage">
    <div class="form-group">
        <input type="text" class="form-control" name="eid" placeholder="EID">
    </div>
    <button type="submit" class="btn btn-default">Remove</button>
</form>
<p>${eid}</p>
<p>To sign up for a lions lunch this week please click <a href="LunchForm.jsp">here</a></p>
</p>
</body>
</html>
