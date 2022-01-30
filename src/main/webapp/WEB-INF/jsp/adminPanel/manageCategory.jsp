<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mange Category</title>
</head>
<body>
<h3>which one?</h3>
<a href="<c:url value="/admin/addCategory"/>" class="btn btn-outline-primary">Add</a>
<a href="<c:url value="/login"/>" class="btn btn-outline-primary ">Delete</a>
<a href="<c:url value="/login"/>" class="btn btn-outline-primary ">Edit</a>
<a href="<c:url value="/admin/showAll"/>" class="btn btn-outline-primary ">Show All Category</a>
</body>
</html>
