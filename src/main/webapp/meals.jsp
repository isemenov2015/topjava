<%--
  Created by IntelliJ IDEA.
  User: Ilya
  Date: 17.07.2017
  Time: 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<head>
    <title>Meals</title>
</head>
<h3><a href="index.html">Home</a></h3>
<body>
<h2>Meals list</h2>
<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach var = "lst" items = "${mealslist}">
        <c:choose>
            <c:when test="${lst.isExceed()}">
                <tr style=color:red>
            </c:when>
            <c:otherwise>
                <tr style=color:green>
            </c:otherwise>
        </c:choose>
            <td>${lst.getDate()}</td>
            <td>${lst.getDescription()}</td>
            <td>${lst.getCalories()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
