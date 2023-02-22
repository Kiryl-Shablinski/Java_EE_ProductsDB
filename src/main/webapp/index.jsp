<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Products</title>
</head>
<body>
<h2>Products List</h2>
<br/>
<a href="<c:url value="/create.jsp"/>">Create new</a>
<table>
    <tr><th>Name</th><th>Price</th></tr>
       <c:forEach var="product" items="${products}">
    <tr><td>${product.name}</td><td>${product.price}</td>
    <td>
        <a href="<c:url value="/edit?id=${product.id}"/>">Edit</a>
        <form method="post" action="<c:url value="/delete"/>" style="display:inline">
            <input type="hidden" name="id" value="${product.id}">
            <input type="submit" value="Delete">
        </form>
    </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>