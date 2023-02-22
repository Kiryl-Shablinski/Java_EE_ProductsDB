<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kirya
  Date: 22.02.2023
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Product</title>
</head>
<body>
<h2>New product</h2>
<form method="post">
  <label for="name">Name</label>
  <input id="name"><br><br>
  <label for="price">Price</label>
  <input id="price" type="number" placeholder="1.0" step="0.01"><br><br>
  <input type="submit" value="Save" style="background-color: cadetblue">
  <a href="<c:url value="/index.jsp"/>">Back</a>
</form>
</body>
</html>
