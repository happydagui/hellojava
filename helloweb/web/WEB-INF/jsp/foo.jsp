<%--
  Created by IntelliJ IDEA.
  User: min
  Date: 16-12-31
  Time: 下午1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>hello jsp</title>
</head>
<body>
<h1>Hello Springmvc With jstl view</h1>
<ul>
<c:forEach var="user" items="${users}">
    <li>${user.name}</li>
</c:forEach>
</ul>
</body>
</html>
