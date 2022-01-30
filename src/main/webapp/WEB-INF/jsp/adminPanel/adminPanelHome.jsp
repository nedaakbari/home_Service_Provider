<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>adminPanelHome</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous"/>
    <link rel="stylesheet" type="text/css" href="/css/panelStyle.css"/>

</head>
<body>

<div class="panel-container">
    <h1 class="large-heading"> hello ${name}</h1>
    <h1 class="small-heading">Which one do you want to do?</h1>

    <button class="coursesBtn">
        <a href="<c:url value="/admin/mangeCategory"/>" class="btn btn-outline-primary ">Manage Category</a>
    </button>
    <button class="coursesBtn">
        <a href="<c:url value="/admin/mangeSubCategory"/>" class="btn btn-outline-primary ">Manage SubCategory</a>
    </button>

    <button class="coursesBtn">
        <a href="<c:url value="/admin/mangeUser"/>" class="btn btn-outline-primary ">Manage Users</a>
    </button>
</div>

</body>
</html>
